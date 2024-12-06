package com.tcc.casamento.dtos.fornecedor;

import com.tcc.casamento.entities.fornecedor.Fornecedor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FornecedorDTO {

    private Long idFornecedor;
    private String nome;
    private String tipoServico;

    public FornecedorDTO(Fornecedor entity) {
        this.idFornecedor = entity.getIdFornecedor();
        this.nome = entity.getNome();
        this.tipoServico = entity.getTipoServico();
    }
}
