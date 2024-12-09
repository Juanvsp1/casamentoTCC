package com.tcc.casamento.entities.convidado;

import com.tcc.casamento.entities.convite.Convite;
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
@Table(name = "tb_convidado")
public class Convidado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConvidado;

    private String nome;

    private Boolean statusRSVP;

    @ManyToOne
    @JoinColumn(name = "convite_id", referencedColumnName = "idConvite")
    private Convite convite;

    public Convidado(String nome, Boolean statusRSVP, Convite convite) {
        this.nome = nome;
        this.statusRSVP = statusRSVP;
        this.convite = convite;
    }
}
