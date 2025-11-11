package com.unilovers.louis_vittao.controllers;

import com.unilovers.louis_vittao.domain.Produto;
import com.unilovers.louis_vittao.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    @Autowired
    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    // ===============================
    // CRUD
    // ===============================

    @PostMapping
    public ResponseEntity<Produto> insert(@RequestBody Produto produto) {
        Produto obj = service.insert(produto);
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable UUID id, @RequestBody Produto newData) {
        Produto obj = service.update(id, newData);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable UUID id) {
        Produto obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        List<Produto> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
