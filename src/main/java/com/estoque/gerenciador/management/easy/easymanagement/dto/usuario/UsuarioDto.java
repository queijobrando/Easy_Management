package com.estoque.gerenciador.management.easy.easymanagement.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

        @NotBlank
        String login;
        @NotBlank
        String nome;
        @Email
        @NotBlank
        String email;
        @NotBlank
        String senha;
        @NotNull
        Long grupo;
        Boolean enviarEmail;
}
