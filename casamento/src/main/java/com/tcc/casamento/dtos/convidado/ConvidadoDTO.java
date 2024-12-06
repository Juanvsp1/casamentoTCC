package com.tcc.casamento.dtos.convidado;

import com.tcc.casamento.entities.convidado.Convidado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConvidadoDTO {

    private Long idConvidado;
    private String nome;
    private Boolean statusRSVP;

    public ConvidadoDTO(Convidado convidado) {
        this.idConvidado = convidado.getIdConvidado();
        this.nome = convidado.getNome();
        this.statusRSVP = convidado.getStatusRSVP();
    }
}
