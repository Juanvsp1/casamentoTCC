package com.tcc.casamento.services.convite;

import com.tcc.casamento.dtos.convite.ConviteDTO;
import com.tcc.casamento.entities.convidado.Convidado;
import com.tcc.casamento.entities.convite.Convite;
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
public class ConviteService {

    @Autowired
    private ConviteRepository conviteRepository;

    @Transactional(readOnly = true)
    public Page<ConviteDTO> findAllPaged(Pageable pageable) {
        Page<Convite> list = conviteRepository.findAll(pageable);
        return list.map(entity -> new ConviteDTO(entity, entity.getConvidados()));
    }

    @Transactional(readOnly = true)
    public ConviteDTO findById(Long id) {
        Convite entity = conviteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Convite não encontrado"));
        return new ConviteDTO(entity, entity.getConvidados());
    }

    @Transactional
    public ConviteDTO insert(ConviteDTO dto) {
        Convite entity = new Convite();
        copyDtoToEntity(dto, entity);
        entity = conviteRepository.save(entity);
        return new ConviteDTO(entity, entity.getConvidados());
    }

    @Transactional
    public ConviteDTO update(Long id, ConviteDTO dto) {
        try {
            Convite entity = conviteRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = conviteRepository.save(entity);
            return new ConviteDTO(entity, entity.getConvidados());
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id " + id + " não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!conviteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Convite não encontrado");
        }

        try {
            conviteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(ConviteDTO dto, Convite entity) {
        entity.setStatusEnvio(dto.getStatusEnvio());

        entity.getConvidados().clear();
        if (dto.getConvidados() != null) {
            dto.getConvidados().forEach(convidadoDTO -> {
                Convidado convidado = new Convidado();
                convidado.setNome(convidadoDTO.getNome());
                convidado.setStatusRSVP(convidadoDTO.getStatusRSVP());
                convidado.setConvite(entity);
                entity.getConvidados().add(convidado);
            });
        }
    }
}