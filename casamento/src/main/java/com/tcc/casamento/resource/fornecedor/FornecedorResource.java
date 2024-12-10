package com.tcc.casamento.resource.fornecedor;

import com.tcc.casamento.dtos.fornecedor.FornecedorDTO;
import com.tcc.casamento.services.fornecedor.FornecedorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorResource {

    @Autowired
    private FornecedorService service;

    @GetMapping
    public ResponseEntity<Page<FornecedorDTO>> getAllFornecedores(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<FornecedorDTO> list = service.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorDTO> getFornecedorById(@PathVariable Long id) {
        FornecedorDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<FornecedorDTO> insertFornecedor(@RequestBody FornecedorDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getIdFornecedor()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FornecedorDTO> updateFornecedor(@PathVariable Long id, @RequestBody FornecedorDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}/casamento/{idCasamento}")
    public ResponseEntity<FornecedorDTO> atualizarFornecedorCasamento(
            @PathVariable Long id,         // ID do fornecedor
            @PathVariable Long idCasamento // ID do casamento
    ) {
        FornecedorDTO dto = service.atualizarFornecedorCasamento(id, idCasamento);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFornecedor(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
