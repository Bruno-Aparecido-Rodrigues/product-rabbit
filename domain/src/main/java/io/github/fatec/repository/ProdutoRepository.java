package io.github.fatec.repository;

import io.github.fatec.entity.Produto;

import java.util.List;

public interface ProdutoRepository {
    Produto save(Produto produto);
    Produto update(Produto produto);
    void delete(String id);
    List<Produto> findAll();
    Produto findById(String id);
}
