package com.lucas.os.service.impl;

import com.lucas.os.domain.dtos.TarefaDto;
import com.lucas.os.domain.people.Tarefa;
import com.lucas.os.repositories.TarefaRepository;
import com.lucas.os.service.exception.DataIntegratyViolationException;
import com.lucas.os.service.exception.ObjectNotFoundException;
import com.lucas.os.service.interfacesservice.TarefaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaServiceImpl implements TarefaService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TarefaRepository repository;

    @Override
    public Tarefa findById(Integer id) {
        Optional<Tarefa> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + ", Tipo" + Tarefa.class.getName()));
    }

    @Override
    public List<Tarefa> findAll() {
        return repository.findAll();
    }

    @Override
    public Tarefa create(TarefaDto obj) {
        return repository.save(mapper.map(obj, Tarefa.class));
    }

    @Override
    public Tarefa update(TarefaDto obj) {
        return repository.save(mapper.map(obj, Tarefa.class));
    }

    @Override
    public void delete(Integer id) {
        Tarefa obj = findById(id);
        if(obj.getList().size() > 0){
            throw new DataIntegratyViolationException("Administrativo possui ordens de serviço, não pode ser deletado");
        }

        repository.deleteById(id);
    }

}
