package com.tcc.casamento.entities.casamento;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tcc.casamento.entities.convite.Convite;
import com.tcc.casamento.entities.fornecedor.Fornecedor;
import com.tcc.casamento.entities.orcamento.Orcamento;
import com.tcc.casamento.entities.tema.Tema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_casamento")
public class Casamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCasamento;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date data;

    private String local;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tema", referencedColumnName = "idTema")
    private Tema tema;

    @OneToMany(mappedBy = "casamento", cascade = CascadeType.ALL)
    Set<Fornecedor> fornecedores = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orcamento_id", referencedColumnName = "idOrcamento")
    private Orcamento orcamento;

    @OneToMany(mappedBy = "casamento", cascade = CascadeType.ALL)
    Set<Convite> convites = new HashSet<>();
}
