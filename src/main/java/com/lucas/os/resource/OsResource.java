package com.lucas.os.resource;

import com.lucas.os.domain.dtos.OrdemServicoDto;
import com.lucas.os.service.interfacesservice.OrdemServicoService;
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
@RequestMapping(value = "/os")
public class OsResource {

    public static final String ID = "/{id}";
    @Autowired
    private OrdemServicoService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping(value = ID)
    ResponseEntity<OrdemServicoDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id),OrdemServicoDto.class));
    }

    @GetMapping
    public ResponseEntity<List<OrdemServicoDto>> findAll() {
        List<OrdemServicoDto> list = service.findAll().stream().map(x -> new OrdemServicoDto(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<OrdemServicoDto> create(@Valid @RequestBody OrdemServicoDto objDto){
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path(ID).buildAndExpand(service.create(objDto).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<OrdemServicoDto> update(@Valid @RequestBody OrdemServicoDto objDto) {
        objDto = new OrdemServicoDto(service.update(objDto));
        return ResponseEntity.ok(objDto);
    }

}
