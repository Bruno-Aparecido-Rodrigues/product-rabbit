package io.github.fatec.controller.request;

import java.math.BigDecimal;
import java.util.List;

public record ProdutoUpdateRequest(
        String id,
        String nome,
        String descricao,
        List<String> imagens,
        BigDecimal preco,
        Integer estoque,
        Double avaliacao
) {
}
