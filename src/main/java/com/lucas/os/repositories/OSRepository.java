package com.lucas.os.repositories;

import com.lucas.os.domain.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OSRepository extends JpaRepository<OrdemServico,Integer> {
}
