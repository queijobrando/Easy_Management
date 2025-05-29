package com.estoque.gerenciador.management.easy.easymanagement.dto.produto;

import com.estoque.gerenciador.management.easy.easymanagement.model.Categorias;
import com.estoque.gerenciador.management.easy.easymanagement.model.enuns.Unidade;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProdutoDtoRetorno(
        Long id,
        String nome,
        String descricao,
        String codigo_de_barras,
        Categorias categorias,
        BigDecimal preco,
        Unidade unidade,
        Boolean perecivel,
        Boolean ativo,
        LocalDateTime data_cadastro,
        LocalDateTime data_atualizacao
) {
}
