package io.github.fatec.entity;

import java.math.BigDecimal;
import java.util.List;

public record Produto(
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
