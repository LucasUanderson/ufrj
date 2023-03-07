package com.lucas.os.service.impl;

import com.lucas.os.domain.dtos.ClienteDto;
import com.lucas.os.domain.people.Cliente;
import com.lucas.os.repositories.ClienteRepository;
import com.lucas.os.service.interfacesservice.ClienteService;
import com.lucas.os.service.exception.DataIntegratyViolationException;
import com.lucas.os.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ClienteRepository repository;

    @Override
    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + ", Tipo" + Cliente.class.getName()));
    }

    @Override
    public List<Cliente> findAll() {
        return repository.findAll();
    }

    @Override
    public Cliente create(ClienteDto obj) {
        return repository.save(mapper.map(obj, Cliente.class));
    }

    @Override
    public Cliente update(ClienteDto obj) {
        return repository.save(mapper.map(obj, Cliente.class));
    }

    @Override
    public void delete(Integer id) {
        Cliente obj = findById(id);
        if(obj.getList().size() > 0){
            throw new DataIntegratyViolationException("Administrativo possui ordens de serviço, não pode ser deletado");
        }

        repository.deleteById(id);
    }

}
