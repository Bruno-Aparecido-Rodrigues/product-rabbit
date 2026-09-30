package io.github.fatec.repository.orm;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "produto")
public record ProdutoOrm(
        @Id
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
