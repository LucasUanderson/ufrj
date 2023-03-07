package com.lucas.os.service.impl;

import com.lucas.os.domain.dtos.AdministrativoDto;
import com.lucas.os.domain.people.Administrativo;
import com.lucas.os.repositories.AdministrativoRepository;
import com.lucas.os.service.interfacesservice.AdministrativoService;
import com.lucas.os.service.exception.DataIntegratyViolationException;
import com.lucas.os.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministrativoServiceImpl implements AdministrativoService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AdministrativoRepository repository;

    @Override
    public Administrativo findById(Integer id) {
        Optional<Administrativo> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + ", Tipo" + Administrativo.class.getName()));
    }

    @Override
    public List<Administrativo> findAll() {
        return repository.findAll();
    }

    @Override
    public Administrativo create(AdministrativoDto obj) {
        return repository.save(mapper.map(obj, Administrativo.class));
    }

    @Override
    public Administrativo update(AdministrativoDto obj) {
        return repository.save(mapper.map(obj, Administrativo.class));
    }

    @Override
    public void delete(Integer id) {
        Administrativo obj = findById(id);
        if(obj.getList().size() > 0){
            throw new DataIntegratyViolationException("Administrativo possui ordens de serviço, não pode ser deletado");
        }

        repository.deleteById(id);
    }

}
