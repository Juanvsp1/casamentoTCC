package com.tcc.casamento.dtos.orcamento;

import com.tcc.casamento.entities.orcamento.Orcamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrcamentoDTO {

    private Long idOrcamento;
    private Float total;

    public OrcamentoDTO(Orcamento orcamento) {
        this.idOrcamento = orcamento.getIdOrcamento();
        this.total = orcamento.getTotal();
    }
}
