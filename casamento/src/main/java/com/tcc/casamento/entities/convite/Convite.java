package com.tcc.casamento.entities.convite;

import com.tcc.casamento.entities.casamento.Casamento;
import com.tcc.casamento.entities.convidado.Convidado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_convite")
public class Convite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConvite;

    private String statusEnvio;

    @ManyToOne
    @JoinColumn(name = "casamento_id", referencedColumnName = "idCasamento")
    private Casamento casamento;

    @OneToMany(mappedBy = "convite", cascade = CascadeType.ALL)
    Set<Convidado> convidados = new HashSet<>();
}
