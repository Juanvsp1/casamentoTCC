package com.tcc.casamento.dtos.casamento;

import com.tcc.casamento.dtos.convite.ConviteDTO;
import com.tcc.casamento.dtos.fornecedor.FornecedorDTO;
import com.tcc.casamento.dtos.orcamento.OrcamentoDTO;
import com.tcc.casamento.dtos.tema.TemaDTO;
import com.tcc.casamento.entities.casamento.Casamento;
import com.tcc.casamento.entities.convite.Convite;
import com.tcc.casamento.entities.fornecedor.Fornecedor;
import com.tcc.casamento.entities.orcamento.Orcamento;
import com.tcc.casamento.entities.tema.Tema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CasamentoDTO {

    private Long idCasamento;

    private Date data;

    private String local;

    private TemaDTO tema;

    private List<FornecedorDTO> fornecedores = new ArrayList<>();

    private OrcamentoDTO orcamento;

    private List<ConviteDTO> convites = new ArrayList<>();

    public CasamentoDTO(Casamento entity) {
        this.idCasamento = entity.getIdCasamento();
        this.data = entity.getData();
        this.local = entity.getLocal();
    }

    public CasamentoDTO(Casamento entity, Tema tema){
        this(entity);
        this.tema = getTema();
    }

    public CasamentoDTO(Casamento entity, Set<Fornecedor> fornecedores, Orcamento orcamento, Tema tema, Set<Convite> convites) {
        this(entity);
        this.orcamento = getOrcamento();
        this.tema = getTema();
        fornecedores.forEach(fornecedor -> this.fornecedores.add(new FornecedorDTO(fornecedor)));
        convites.forEach(convite -> this.convites.add(new ConviteDTO(convite)));
    }
}
