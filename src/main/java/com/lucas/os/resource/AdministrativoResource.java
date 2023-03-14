package com.lucas.os.resource;

import com.lucas.os.domain.dtos.AdministrativoDto;
import com.lucas.os.domain.people.Administrativo;
import com.lucas.os.service.interfacesservice.AdministrativoService;
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
@RequestMapping(value = "/api/admin/v1")
@Tag(name = "Administrativo", description = "Gerenciador de Administrativo")
public class AdministrativoResource {

    public static final String ID = "/{id}";

    @Autowired
    private AdministrativoService service;


    @Autowired
    private ModelMapper mapper;


    @GetMapping(value = ID, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<AdministrativoDto> findById(@PathVariable Integer id){
        Link link = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
        return ResponseEntity.ok().body(mapper.map(service.findById(id),AdministrativoDto.class).add(
                linkTo(methodOn(AdministrativoResource.class).findById(id)).withSelfRel()));
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<AdministrativoDto>> findAll() {
        List<AdministrativoDto> administrativos = service.findAll()
                .stream().map(x -> mapper.map(x, AdministrativoDto.class)).collect(Collectors.toList());

        for (AdministrativoDto administrativo : administrativos){
            Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(administrativo.getKey()).withSelfRel();
            administrativo.add(selfLink);
        }
        return ResponseEntity.ok().body(administrativos);
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<AdministrativoDto> create(@Valid @RequestBody AdministrativoDto objDto){
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path(ID).buildAndExpand(service.create(objDto).getId()).toUri();
        Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(objDto.getKey()).withSelfRel();
        objDto.add(selfLink);
        return ResponseEntity.created(uri).body(objDto);
    }


    @PutMapping(value = ID, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<AdministrativoDto> update(@PathVariable Integer id, @Valid @RequestBody AdministrativoDto obj){
        obj.setKey(id);
        Administrativo newObj = service.update(obj);
        Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(newObj.getId()).withSelfRel();
        return ResponseEntity.ok().body(mapper.map(newObj, AdministrativoDto.class).add(selfLink));
    }


    @DeleteMapping(value = ID)
    public ResponseEntity<AdministrativoDto> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
