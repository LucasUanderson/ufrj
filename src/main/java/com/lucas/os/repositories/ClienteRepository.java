package com.lucas.os.repositories;

import com.lucas.os.domain.people.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
