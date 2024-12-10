package com.tcc.casamento.services.convidado;

import com.tcc.casamento.dtos.convidado.ConvidadoDTO;
import com.tcc.casamento.entities.convidado.Convidado;
import com.tcc.casamento.entities.convite.Convite;
import com.tcc.casamento.repositories.convidado.ConvidadoRepository;
import com.tcc.casamento.repositories.convite.ConviteRepository;
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
public class ConvidadoService {

    @Autowired
    private ConvidadoRepository convidadoRepository;

    @Autowired
    private ConviteRepository conviteRepository;

    @Transactional(readOnly = true)
    public Page<ConvidadoDTO> findAllPaged(Pageable pageable) {
        Page<Convidado> list = convidadoRepository.findAll(pageable);
        return list.map(ConvidadoDTO::new);
    }

    @Transactional(readOnly = true)
    public ConvidadoDTO findById(Long id) {
        Convidado entity = convidadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Convidado não encontrado"));
        return new ConvidadoDTO(entity);
    }

    @Transactional
    public ConvidadoDTO insert(ConvidadoDTO dto) {
        Convidado entity = new Convidado();
        copyDtoToEntity(dto, entity);
        entity = convidadoRepository.save(entity);
        return new ConvidadoDTO(entity);
    }

    @Transactional
    public ConvidadoDTO update(Long id, ConvidadoDTO dto) {
        try {
            Convidado entity = convidadoRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = convidadoRepository.save(entity);
            return new ConvidadoDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id " + id + " não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!convidadoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Convidado não encontrado");
        }

        try {
            convidadoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    @Transactional
    public ConvidadoDTO associarConvidadoAoConvite(Long idConvidado, Long idConvite) {
        // Busca o convidado no banco de dados
        Convidado convidado = convidadoRepository.findById(idConvidado)
                .orElseThrow(() -> new ResourceNotFoundException("Convidado não encontrado com ID: " + idConvidado));

        // Busca o convite no banco de dados
        Convite convite = conviteRepository.findById(idConvite)
                .orElseThrow(() -> new ResourceNotFoundException("Convite não encontrado com ID: " + idConvite));

        // Associa o convite ao convidado
        convidado.setConvite(convite);

        // Salva o convidado atualizado
        convidado = convidadoRepository.save(convidado);

        // Retorna o DTO atualizado
        return new ConvidadoDTO(convidado);
    }


    private void copyDtoToEntity(ConvidadoDTO dto, Convidado entity) {
        entity.setNome(dto.getNome());
        entity.setStatusRSVP(dto.getStatusRSVP());
    }
}
