package com.tcc.casamento.services.orcamento;

import com.tcc.casamento.dtos.orcamento.OrcamentoDTO;
import com.tcc.casamento.entities.orcamento.Orcamento;
import com.tcc.casamento.repositories.orcamento.OrcamentoRepository;
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

@Service
public class OrcamentoService {

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @Transactional(readOnly = true)
    public Page<OrcamentoDTO> findAllPaged(Pageable pageable) {
        Page<Orcamento> list = orcamentoRepository.findAll(pageable);
        return list.map(OrcamentoDTO::new);
    }

    @Transactional(readOnly = true)
    public OrcamentoDTO findById(Long id) {
        Orcamento entity = orcamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orçamento não encontrado"));
        return new OrcamentoDTO(entity);
    }

    @Transactional
    public OrcamentoDTO insert(OrcamentoDTO dto) {
        Orcamento entity = new Orcamento();
        copyDtoToEntity(dto, entity);
        entity = orcamentoRepository.save(entity);
        return new OrcamentoDTO(entity);
    }

    @Transactional
    public OrcamentoDTO update(Long id, OrcamentoDTO dto) {
        try {
            Orcamento entity = orcamentoRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = orcamentoRepository.save(entity);
            return new OrcamentoDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id " + id + " não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!orcamentoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Orçamento não encontrado");
        }

        try {
            orcamentoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(OrcamentoDTO dto, Orcamento entity) {
        entity.setTotal(dto.getTotal());
    }
}
