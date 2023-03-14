package com.lucas.os.resource;

import com.lucas.os.domain.dtos.OrdemServicoDto;
import com.lucas.os.service.interfacesservice.OrdemServicoService;
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
@RequestMapping(value = "/api/os/v1")
@Tag(name = "Ordem De Serviço", description = "Gerenciador de OS")
public class OsResource {

    public static final String ID = "/{id}";
    @Autowired
    private OrdemServicoService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping(value = ID, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<OrdemServicoDto> findById(@PathVariable Integer id){
        Link link = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
        return ResponseEntity.ok().body(mapper.map(service.findById(id),OrdemServicoDto.class).add(
                linkTo(methodOn(OsResource.class).findById(id)).withSelfRel()));
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<OrdemServicoDto>> findAll() {
        List<OrdemServicoDto> ordemServicos = service.findAll().stream().map(x -> mapper.map(x, OrdemServicoDto.class)).collect(Collectors.toList());

        for (OrdemServicoDto ordemServico : ordemServicos){
            Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(ordemServico.getKey()).withSelfRel();
            ordemServico.add(selfLink);
        }

        return ResponseEntity.ok().body(ordemServicos);
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<OrdemServicoDto> create(@Valid @RequestBody OrdemServicoDto objDto){
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path(ID).buildAndExpand(service.create(objDto).getId()).toUri();
        Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(objDto.getKey()).withSelfRel();
        objDto.add(selfLink);
        return ResponseEntity.created(uri).body(objDto);
    }

    @PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<OrdemServicoDto> update(@Valid @RequestBody OrdemServicoDto objDto) {
        objDto = new OrdemServicoDto(service.update(objDto));
        Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(objDto.getKey()).withSelfRel();
        return ResponseEntity.ok().body(mapper.map(objDto, OrdemServicoDto.class).add(selfLink));
    }

}
