package com.unilovers.louis_vittao.services;

import com.unilovers.louis_vittao.domain.Produto;
import com.unilovers.louis_vittao.repositories.ProdutoRepository;
import com.unilovers.louis_vittao.services.exceptions.DatabaseException;
import com.unilovers.louis_vittao.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    @Autowired
    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    // ===============================
    // CRUD
    // ===============================

    public Produto insert(Produto produto) {
        return repository.save(produto);
    }

    public Produto update(UUID id, Produto newData) {
        try {
            Produto entity = repository.getReferenceById(id);
            updateData(entity, newData);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void delete(UUID id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Produto findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Produto> findAll() {
        return repository.findAll();
    }

    // ===============================
    // AUXILIARES
    // ===============================

    public void updateData(Produto entity, Produto newData) {
        entity.setNome(newData.getNome());
        entity.setCategoria(newData.getCategoria());
        entity.setCor(newData.getCor());
        entity.setPreco(newData.getPreco());
        entity.setAtivo(newData.getAtivo());
    }
}
