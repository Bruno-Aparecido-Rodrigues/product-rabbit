package io.github.fatec.controller.response;

import java.math.BigDecimal;
import java.util.List;

public record ProdutoResponse(
        String id,
        String nome,
        String descricao,
        List<String> imagens,
        BigDecimal preco,
        Integer estoque,
        Double avaliacao,
        boolean ativo
) {
}
