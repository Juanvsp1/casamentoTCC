package com.tcc.casamento.repositories.convite;

import com.tcc.casamento.entities.convite.Convite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConviteRepository extends JpaRepository<Convite, Long> {
}
