package com.tcc.casamento.services.casamento;

import com.tcc.casamento.dtos.casamento.CasamentoDTO;
import com.tcc.casamento.dtos.tema.TemaDTO;
import com.tcc.casamento.entities.casamento.Casamento;
import com.tcc.casamento.entities.convidado.Convidado;
import com.tcc.casamento.entities.convite.Convite;
import com.tcc.casamento.entities.fornecedor.Fornecedor;
import com.tcc.casamento.entities.orcamento.Orcamento;
import com.tcc.casamento.entities.tema.Tema;
import com.tcc.casamento.repositories.casamento.CasamentoRepository;
import com.tcc.casamento.repositories.tema.TemaRepository;
import com.tcc.casamento.services.exception.DatabaseException;
import com.tcc.casamento.services.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class CasamentoService {

    @Autowired
    private CasamentoRepository casamentoRepository;

    @Autowired
    private TemaRepository temaRepository;

    @Transactional(readOnly = true)
    public Page<CasamentoDTO> findAllPaged(Pageable pageable) {
        Page<Casamento> list = casamentoRepository.findAll(pageable);
        return list.map(entity -> new CasamentoDTO(entity, entity.getFornecedores(),entity.getOrcamento() ,entity.getTema(), entity.getConvites()));
    }

    @Transactional(readOnly = true)
    public CasamentoDTO findById(Long id) {
        Optional<Casamento> obj = casamentoRepository.findById(id);
        Casamento entity = obj.orElseThrow(() -> new ResourceNotFoundException("Casamento não encontrado"));
        return new CasamentoDTO(entity, entity.getFornecedores(),entity.getOrcamento() ,entity.getTema(), entity.getConvites());
    }

    @Transactional
    public CasamentoDTO insert(CasamentoDTO dto) {
        Casamento entity = new Casamento();
        entity.setData(dto.getData());
        entity.setLocal(dto.getLocal());
        entity = casamentoRepository.save(entity);
        return new CasamentoDTO(entity);
    }

    @Transactional
    public CasamentoDTO update(Long id, CasamentoDTO dto) {
        try {
            Casamento entity = casamentoRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = casamentoRepository.save(entity);
            return new CasamentoDTO(entity, entity.getFornecedores(),entity.getOrcamento() ,entity.getTema(), entity.getConvites());
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id " + id + " não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!casamentoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Casamento não encontrado");
        }

        try {
            casamentoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }


    @Transactional
    public CasamentoDTO atualizarTema(Long id, TemaDTO temaDTO) {
        // Busca o casamento no banco de dados
        Casamento entity = casamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Casamento não encontrado com ID: " + id));

        // Valida e associa o tema
        if (temaDTO.getIdTema() != null) {
            Tema tema = temaRepository.findById(temaDTO.getIdTema())
                    .orElseThrow(() -> new ResourceNotFoundException("Tema não encontrado com ID: " + temaDTO.getIdTema()));
            entity.setTema(tema); // Atualiza o tema
        } else {
            entity.setTema(null); // Permite remover o tema
        }

        // Salva a atualização no banco de dados
        entity = casamentoRepository.save(entity);

        // Retorna o DTO atualizado
        return new CasamentoDTO(entity, entity.getFornecedores(),entity.getOrcamento() ,entity.getTema(), entity.getConvites());
    }

    private void copyDtoToEntity(CasamentoDTO dto, Casamento entity) {

        entity.setData(dto.getData());
        entity.setLocal(dto.getLocal());

        // Associa Tema (opcional)
        if (dto.getTema() != null && dto.getTema().getIdTema() != null) {
            Tema tema = temaRepository.findById(dto.getTema().getIdTema())
                    .orElseThrow(() -> new ResourceNotFoundException("Tema não encontrado com ID: " + dto.getTema().getIdTema()));
            entity.setTema(tema);
        } else {
            entity.setTema(null); // Permitir tema vazio
        }

        // Associa Fornecedores (opcional)
        if (dto.getFornecedores() != null) {
            entity.getFornecedores().clear();
            dto.getFornecedores().forEach(fornecedorDTO -> {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setNome(fornecedorDTO.getNome());
                fornecedor.setTipoServico(fornecedorDTO.getTipoServico());
                fornecedor.setCasamento(entity);
                entity.getFornecedores().add(fornecedor);
            });
        }

        // Associa Convites (opcional)
        if (dto.getConvites() != null) {
            entity.getConvites().clear();
            dto.getConvites().forEach(conviteDTO -> {
                Convite convite = new Convite();
                convite.setStatusEnvio(conviteDTO.getStatusEnvio());
                convite.setCasamento(entity);
                convite.getConvidados().clear();
                if (conviteDTO.getConvidados() != null) {
                    conviteDTO.getConvidados().forEach(convidadoDTO -> {
                        Convidado convidado = new Convidado();
                        convidado.setNome(convidadoDTO.getNome());
                        convidado.setStatusRSVP(convidadoDTO.getStatusRSVP());
                        convidado.setConvite(convite);
                        convite.getConvidados().add(convidado);
                    });
                }
                entity.getConvites().add(convite);
            });
        }

        // Associa Orçamento (opcional)
        if (dto.getOrcamento() != null) {
            Orcamento orcamento = new Orcamento();
            orcamento.setTotal(dto.getOrcamento().getTotal());
            orcamento.setCasamento(entity);
            entity.setOrcamento(orcamento);
        } else {
            entity.setOrcamento(null);
        }
    }
}
