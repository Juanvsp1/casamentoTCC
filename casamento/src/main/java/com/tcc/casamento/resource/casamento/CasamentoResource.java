package com.tcc.casamento.resource.casamento;

import com.tcc.casamento.dtos.casamento.CasamentoDTO;
import com.tcc.casamento.dtos.tema.TemaDTO;
import com.tcc.casamento.services.casamento.CasamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/casamentos")
public class CasamentoResource {

    @Autowired
    private CasamentoService service;

    @GetMapping
    public ResponseEntity<Page<CasamentoDTO>> getAllCasamentos(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "local") String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<CasamentoDTO> list = service.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CasamentoDTO> getCasamentoById(@PathVariable Long id) {
        CasamentoDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<CasamentoDTO> insertCasamento(@RequestBody CasamentoDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getIdCasamento()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CasamentoDTO> updateCasamento(@PathVariable Long id, @RequestBody CasamentoDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}/tema")
    public ResponseEntity<CasamentoDTO> atualizarTema(
            @PathVariable Long id, @RequestBody TemaDTO temaDTO) {
        CasamentoDTO dto = service.atualizarTema(id, temaDTO);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCasamento(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
