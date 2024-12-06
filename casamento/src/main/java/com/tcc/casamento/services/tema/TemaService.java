package com.tcc.casamento.services.tema;

import com.tcc.casamento.dtos.tema.TemaDTO;
import com.tcc.casamento.entities.tema.Tema;
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

@Service
public class TemaService {

    @Autowired
    private TemaRepository temaRepository;

    @Transactional(readOnly = true)
    public Page<TemaDTO> findAllPaged(Pageable pageable) {
        Page<Tema> list = temaRepository.findAll(pageable);
        return list.map(TemaDTO::new);
    }

    @Transactional(readOnly = true)
    public TemaDTO findById(Long id) {
        Tema entity = temaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tema não encontrado"));
        return new TemaDTO(entity);
    }

    @Transactional
    public TemaDTO insert(TemaDTO dto) {
        Tema entity = new Tema();
        copyDtoToEntity(dto, entity);
        entity = temaRepository.save(entity);
        return new TemaDTO(entity);
    }

    @Transactional
    public TemaDTO update(Long id, TemaDTO dto) {
        try {
            Tema entity = temaRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = temaRepository.save(entity);
            return new TemaDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id " + id + " não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!temaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tema não encontrado");
        }

        try {
            temaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(TemaDTO dto, Tema entity) {
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
    }
}
