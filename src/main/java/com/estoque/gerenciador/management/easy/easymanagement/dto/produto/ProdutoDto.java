package com.estoque.gerenciador.management.easy.easymanagement.dto.produto;

import com.estoque.gerenciador.management.easy.easymanagement.model.Categorias;
import com.estoque.gerenciador.management.easy.easymanagement.model.enuns.Unidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoDto(
        @NotBlank
        String nome,
        @NotBlank
        String descricao,
        @NotNull
        Categorias categorias,
        @NotNull
        BigDecimal preco,
        @NotBlank
        Unidade unidade,
        @NotNull
        Boolean perecivel
) {
}
