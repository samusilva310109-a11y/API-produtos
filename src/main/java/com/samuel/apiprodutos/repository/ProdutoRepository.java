package com.samuel.apiprodutos.repository;

import com.samuel.apiprodutos.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, String> {
}
