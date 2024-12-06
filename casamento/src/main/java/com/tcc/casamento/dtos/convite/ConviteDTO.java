package com.tcc.casamento.dtos.convite;

import com.tcc.casamento.dtos.convidado.ConvidadoDTO;
import com.tcc.casamento.entities.convidado.Convidado;
import com.tcc.casamento.entities.convite.Convite;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConviteDTO {

    private Long idConvite;
    private String statusEnvio;
    private List<ConvidadoDTO> convidados = new ArrayList<>();

    public ConviteDTO(Convite entity){
        this.idConvite = entity.getIdConvite();
        this.statusEnvio = entity.getStatusEnvio();
    }

    public ConviteDTO(Convite entity, Set<Convidado> convidados) {
        this(entity);
        convidados.forEach(convidado -> this.convidados.add(new ConvidadoDTO(convidado)));
    }
}
