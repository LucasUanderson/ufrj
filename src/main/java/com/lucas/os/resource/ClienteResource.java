package com.lucas.os.resource;

import com.lucas.os.domain.dtos.ClienteDto;
import com.lucas.os.domain.people.Cliente;
import com.lucas.os.service.interfacesservice.ClienteService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {

    public static final String ID = "/{id}";

    @Autowired
    private ClienteService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping(value = ID)
    ResponseEntity<ClienteDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id),ClienteDto.class));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> findAll() {
        return ResponseEntity.ok().body(service.findAll()
                .stream().map(x -> mapper.map(x, ClienteDto.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<ClienteDto> create(@Valid @RequestBody ClienteDto objDto){
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path(ID).buildAndExpand(service.create(objDto).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PutMapping(value = ID)
    public ResponseEntity<ClienteDto> update(@PathVariable Integer id, @Valid @RequestBody ClienteDto obj){
        obj.setId(id);
        Cliente newObj = service.update(obj);
        return ResponseEntity.ok().body(mapper.map(newObj, ClienteDto.class));
    }


    @DeleteMapping(value = ID)
    public ResponseEntity<ClienteDto> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
