package com.tcc.casamento.repositories.casamento;

import com.tcc.casamento.entities.casamento.Casamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CasamentoRepository extends JpaRepository<Casamento, Long> {
}
