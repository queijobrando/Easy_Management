package com.estoque.gerenciador.management.easy.easymanagement.dto.categoria;

import jakarta.validation.constraints.NotBlank;

public record CategoriaDto(
        @NotBlank(message = "Campo Obrigatório")
        String nome,
        @NotBlank(message = "Campo Obrigatório")
        String descricao
) {
}
