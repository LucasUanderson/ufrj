package com.lucas.os.service.interfacesservice;

import com.lucas.os.domain.OrdemServico;
import com.lucas.os.domain.dtos.OrdemServicoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrdemServicoService {

    OrdemServico findById(Integer id);
    List<OrdemServico> findAll();
    OrdemServico create (OrdemServicoDto obj);
    OrdemServico update(OrdemServicoDto obj);
    void delete(Integer id);
}
