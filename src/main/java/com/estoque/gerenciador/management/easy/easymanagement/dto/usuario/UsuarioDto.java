package com.estoque.gerenciador.management.easy.easymanagement.dto.usuario;

import java.util.List;

public record UsuarioDto(String login, String senha, List<String> roles) {
}
