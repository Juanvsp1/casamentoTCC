package com.tcc.casamento.services.fornecedor;

import com.tcc.casamento.dtos.fornecedor.FornecedorDTO;
import com.tcc.casamento.entities.fornecedor.Fornecedor;
import com.tcc.casamento.repositories.fornecedor.FornecedorRepository;
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
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Transactional(readOnly = true)
    public Page<FornecedorDTO> findAllPaged(Pageable pageable) {
        Page<Fornecedor> list = fornecedorRepository.findAll(pageable);
        return list.map(FornecedorDTO::new);
    }

    @Transactional(readOnly = true)
    public FornecedorDTO findById(Long id) {
        Fornecedor entity = fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado"));
        return new FornecedorDTO(entity);
    }

    @Transactional
    public FornecedorDTO insert(FornecedorDTO dto) {
        Fornecedor entity = new Fornecedor();
        copyDtoToEntity(dto, entity);
        entity = fornecedorRepository.save(entity);
        return new FornecedorDTO(entity);
    }

    @Transactional
    public FornecedorDTO update(Long id, FornecedorDTO dto) {
        try {
            Fornecedor entity = fornecedorRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = fornecedorRepository.save(entity);
            return new FornecedorDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id " + id + " não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!fornecedorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Fornecedor não encontrado");
        }

        try {
            fornecedorRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(FornecedorDTO dto, Fornecedor entity) {
        entity.setNome(dto.getNome());
        entity.setTipoServico(dto.getTipoServico());
    }
}