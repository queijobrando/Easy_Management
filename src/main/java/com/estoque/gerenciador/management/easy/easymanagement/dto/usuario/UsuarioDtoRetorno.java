package com.estoque.gerenciador.management.easy.easymanagement.dto.usuario;


import java.util.List;

public record UsuarioDtoRetorno(
        Long id,
        String login,
        String nome,
        String email,
        String grupo_nome
) {
}
