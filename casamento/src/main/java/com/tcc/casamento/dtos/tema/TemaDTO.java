package com.tcc.casamento.dtos.tema;

import com.tcc.casamento.entities.tema.Tema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TemaDTO {

    private Long idTema;
    private String nome;
    private String descricao;

    public TemaDTO(Tema entity){
        this.idTema = entity.getIdTema();
        this.nome = entity.getNome();
        this.descricao = entity.getDescricao();
    }
}
