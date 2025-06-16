package com.estoque.gerenciador.management.easy.easymanagement.dto.categoria;

import java.time.LocalDateTime;

public record CategoriaDtoRetorno(
        Long id,
        String nome,
        String descricao,
        Boolean ativo,
        LocalDateTime data_cadastro,
        LocalDateTime data_atualizacao,
        String usuario_login
) {
}
