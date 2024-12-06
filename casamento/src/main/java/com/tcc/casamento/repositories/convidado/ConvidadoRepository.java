package com.tcc.casamento.repositories.convidado;

import com.tcc.casamento.entities.convidado.Convidado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvidadoRepository extends JpaRepository<Convidado, Long> {
}
