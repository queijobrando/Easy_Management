package com.estoque.gerenciador.management.easy.easymanagement.dto.categoria;


public record CategoriaDtoRetorno(
        Long id,
        String nome,
        String descricao,
        Boolean ativo,
        String usuario_login
) {
}
