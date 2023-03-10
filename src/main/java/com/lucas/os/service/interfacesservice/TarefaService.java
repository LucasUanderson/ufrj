package com.lucas.os.service.interfacesservice;

import com.lucas.os.domain.dtos.TarefaDto;
import com.lucas.os.domain.people.Tarefa;

import java.util.List;

public interface TarefaService {

    Tarefa findById(Integer id);
    List<Tarefa> findAll();
    Tarefa create (TarefaDto obj);
    Tarefa update(TarefaDto obj);
    void delete(Integer id);

}
