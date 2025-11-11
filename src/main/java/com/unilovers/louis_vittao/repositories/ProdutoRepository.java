package com.unilovers.louis_vittao.repositories;

import com.unilovers.louis_vittao.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
}
