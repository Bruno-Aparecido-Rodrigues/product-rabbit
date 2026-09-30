package io.github.fatec.controller.request;

import java.math.BigDecimal;
import java.util.List;

public record ProdutoRequest(
        String nome,
        String descricao,
        List<String> imagens,
        BigDecimal preco,
        Integer estoque,
        Double avaliacao
) {
}
