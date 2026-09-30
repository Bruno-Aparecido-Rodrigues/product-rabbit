package io.github.fatec.repository.adapter;

import io.github.fatec.entity.Produto;
import io.github.fatec.repository.orm.ProdutoOrm;

public class ProdutoRepositoryImplAdapter {
    private ProdutoRepositoryImplAdapter() {
    }

    public static Produto cast(ProdutoOrm orm) {
        return new Produto(
                orm.id(),
                orm.nome(),
                orm.descricao(),
                orm.imagens(),
                orm.preco(),
                orm.estoque(),
                orm.avaliacao(),
                orm.ativo());
    }

    public static ProdutoOrm cast(Produto produto) {
        return new ProdutoOrm(
                produto.id(),
                produto.nome(),
                produto.descricao(),
                produto.imagens(),
                produto.preco(),
                produto.estoque(),
                produto.avaliacao(),
                produto.ativo());
    }
}
