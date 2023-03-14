package com.lucas.os.resource;

import com.lucas.os.domain.dtos.TarefaDto;
import com.lucas.os.domain.people.Tarefa;
import com.lucas.os.service.interfacesservice.TarefaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/tarefa/v1")
@Tag(name = "Tarefas", description = "Gerenciador de tarefas")
public class TarefaResource {

    public static final String ID = "/{id}";

    @Autowired
    private TarefaService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping(value = ID, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<TarefaDto> findById(@PathVariable Integer id){
        Link link = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
        return ResponseEntity.ok().body(mapper.map(service.findById(id), TarefaDto.class).add(
                linkTo(methodOn(TarefaResource.class).findById(id)).withSelfRel()));

    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<TarefaDto>> findAll() {
        List<TarefaDto> tarefas = service.findAll()
                .stream().map(x -> mapper.map(x, TarefaDto.class)).collect(Collectors.toList());

        for (TarefaDto tarefa : tarefas){
            Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(tarefa.getKey()).withSelfRel();
            tarefa.add(selfLink);
        }
        return ResponseEntity.ok().body(tarefas);

    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<TarefaDto> create(@Valid @RequestBody TarefaDto objDto){
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path(ID).buildAndExpand(service.create(objDto).getId()).toUri();
        Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(objDto.getKey()).withSelfRel();
        objDto.add(selfLink);
        return ResponseEntity.created(uri).body(objDto);
    }


    @PutMapping(value = ID, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<TarefaDto> update(@PathVariable Integer id, @Valid @RequestBody TarefaDto obj){
        obj.setKey(id);
        Tarefa newObj = service.update(obj);
        Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(newObj.getId()).withSelfRel();
        return ResponseEntity.ok().body(mapper.map(newObj, TarefaDto.class).add(selfLink));
    }


    @DeleteMapping(value = ID)
    public ResponseEntity<TarefaDto> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
