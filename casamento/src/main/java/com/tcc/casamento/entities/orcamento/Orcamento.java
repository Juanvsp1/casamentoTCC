package com.tcc.casamento.entities.orcamento;

import com.tcc.casamento.entities.casamento.Casamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_orcamento")
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrcamento;

    private Float total;

    @OneToOne(mappedBy = "orcamento", cascade = CascadeType.ALL)
    private Casamento casamento;
}
