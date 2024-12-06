package com.tcc.casamento.repositories.orcamento;

import com.tcc.casamento.entities.orcamento.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {
}
