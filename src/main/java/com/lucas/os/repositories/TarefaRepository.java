package com.lucas.os.repositories;

import com.lucas.os.domain.people.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
}
