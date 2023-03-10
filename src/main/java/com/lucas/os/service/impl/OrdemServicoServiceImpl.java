package com.lucas.os.service.impl;

import com.lucas.os.domain.OrdemServico;
import com.lucas.os.domain.dtos.OrdemServicoDto;
import com.lucas.os.domain.enuns.Prioridade;
import com.lucas.os.domain.enuns.Status;
import com.lucas.os.domain.people.Administrativo;
import com.lucas.os.domain.people.Tarefa;
import com.lucas.os.repositories.OrdemServicoRepository;
import com.lucas.os.service.exception.ObjectNotFoundException;
import com.lucas.os.service.interfacesservice.OrdemServicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdemServicoServiceImpl implements OrdemServicoService {

    @Autowired
    private OrdemServicoRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AdministrativoServiceImpl service;

    @Autowired
    private TarefaServiceImpl serviceTarefa;


    @Override
    public OrdemServico findById(Integer id) {
        Optional<OrdemServico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo" + OrdemServico.class.getName()));

    }

    @Override
    public List<OrdemServico> findAll() {
        return repository.findAll();
    }

    @Override
    public OrdemServico create(OrdemServicoDto obj) {
        return fromDto(obj);
    }


    @Override
    public OrdemServico update(OrdemServicoDto obj) {
        findById(obj.getKey());
        return fromDto(obj);
    }


    private OrdemServico fromDto(OrdemServicoDto obj){
        OrdemServico newObj = new OrdemServico();
        newObj.setId(obj.getKey());
        newObj.setObservacoes(obj.getObservacoes());
        newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        newObj.setStatus(Status.toEnum(obj.getStatus()));

        Administrativo adm = service.findById(obj.getAdministrativo());
        Tarefa cliente = serviceTarefa.findById(obj.getTarefa());

        newObj.setAdministrativo(adm);
        newObj.setTarefa(cliente);
        return repository.save(newObj);

    }




    @Override
    public void delete(Integer id) {

    }
}
