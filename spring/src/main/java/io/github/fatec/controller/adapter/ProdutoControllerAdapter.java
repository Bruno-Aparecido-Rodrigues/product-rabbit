package io.github.fatec.controller.adapter;

import io.github.fatec.controller.request.ProdutoRequest;
import io.github.fatec.controller.request.ProdutoUpdateRequest;
import io.github.fatec.entity.Produto;

import java.util.UUID;

public class ProdutoControllerAdapter {
    private ProdutoControllerAdapter() {
    }

    public static Produto cast(ProdutoRequest request) {
        return new Produto(
                UUID.randomUUID().toString(),
                request.nome(),
                request.descricao(),
                request.imagens(),
                request.preco(),
                request.estoque(),
                request.avaliacao(),
                true);
    }

    public static Produto cast(ProdutoUpdateRequest request) {
        return new Produto(
                request.id(),
                request.nome(),
                request.descricao(),
                request.imagens(),
                request.preco(),
                request.estoque(),
                request.avaliacao(),
                true);
    }
}
