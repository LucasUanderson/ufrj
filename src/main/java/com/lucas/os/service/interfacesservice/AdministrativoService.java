package com.lucas.os.service.interfacesservice;

import com.lucas.os.domain.dtos.AdministrativoDto;
import com.lucas.os.domain.people.Administrativo;

import java.util.List;

public interface AdministrativoService {

    Administrativo findById(Integer id);
    List<Administrativo> findAll();
    Administrativo create (AdministrativoDto obj);
    Administrativo update(AdministrativoDto obj);
    void delete(Integer id);

}
