package com.estoque.gerenciador.management.easy.easymanagement.dto.usuario;

import com.estoque.gerenciador.management.easy.easymanagement.model.Grupo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UsuarioDto(
        @NotBlank
        String login,
        @NotBlank
        String nome,
        @Email
        @NotBlank
        String email,
        @NotBlank
        String senha,
        @NotNull
        @NotEmpty
        List<Long> gruposIds) {
}
