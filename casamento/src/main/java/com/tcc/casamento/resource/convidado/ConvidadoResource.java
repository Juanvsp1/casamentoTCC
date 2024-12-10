package com.tcc.casamento.resource.convidado;


import com.tcc.casamento.dtos.convidado.ConvidadoDTO;
import com.tcc.casamento.services.convidado.ConvidadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/convidados")
public class ConvidadoResource {

    @Autowired
    private ConvidadoService service;

    @GetMapping
    public ResponseEntity<Page<ConvidadoDTO>> getAllConvidados(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<ConvidadoDTO> list = service.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConvidadoDTO> getConvidadoById(@PathVariable Long id) {
        ConvidadoDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<ConvidadoDTO> insertConvidado(@RequestBody ConvidadoDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getIdConvidado()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConvidadoDTO> updateConvidado(@PathVariable Long id, @RequestBody ConvidadoDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}/convite/{idConvite}")
    public ResponseEntity<ConvidadoDTO> associarConvidadoAoConvite(
            @PathVariable Long id,
            @PathVariable Long idConvite) {
        ConvidadoDTO dto = service.associarConvidadoAoConvite(id, idConvite);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConvidado(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
