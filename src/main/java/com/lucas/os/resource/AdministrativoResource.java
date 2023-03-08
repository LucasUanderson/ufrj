package com.lucas.os.resource;

import com.lucas.os.domain.dtos.AdministrativoDto;
import com.lucas.os.domain.people.Administrativo;
import com.lucas.os.service.interfacesservice.AdministrativoService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/admin")
public class AdministrativoResource {

    public static final String ID = "/{id}";

    @Autowired
    private AdministrativoService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping(value = ID, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<AdministrativoDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id),AdministrativoDto.class));
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<AdministrativoDto>> findAll() {
        return ResponseEntity.ok().body(service.findAll()
                .stream().map(x -> mapper.map(x, AdministrativoDto.class)).collect(Collectors.toList()));
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<AdministrativoDto> create(@Valid @RequestBody AdministrativoDto objDto){
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path(ID).buildAndExpand(service.create(objDto).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PutMapping(value = ID, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<AdministrativoDto> update(@PathVariable Integer id, @Valid @RequestBody AdministrativoDto obj){
        obj.setId(id);
        Administrativo newObj = service.update(obj);
        return ResponseEntity.ok().body(mapper.map(newObj, AdministrativoDto.class));
    }


    @DeleteMapping(value = ID)
    public ResponseEntity<AdministrativoDto> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
