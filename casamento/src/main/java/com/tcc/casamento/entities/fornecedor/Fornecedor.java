package com.tcc.casamento.entities.fornecedor;

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
@Table(name = "tb_fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFornecedor;

    private String nome;

    private String tipoServico;

    @ManyToOne
    @JoinColumn(name = "casamento_id", referencedColumnName = "idCasamento")
    private Casamento casamento;
}
