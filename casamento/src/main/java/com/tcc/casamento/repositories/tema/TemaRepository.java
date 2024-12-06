package com.tcc.casamento.repositories.tema;

import com.tcc.casamento.entities.tema.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long> {
}
