package com.lucas.os.service.interfacesservice;

import com.lucas.os.domain.dtos.ClienteDto;
import com.lucas.os.domain.people.Cliente;

import java.util.List;

public interface ClienteService {

    Cliente findById(Integer id);
    List<Cliente> findAll();
    Cliente create (ClienteDto obj);
    Cliente update(ClienteDto obj);
    void delete(Integer id);

}
