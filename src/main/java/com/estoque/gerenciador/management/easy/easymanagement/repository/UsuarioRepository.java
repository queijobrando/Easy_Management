package com.estoque.gerenciador.management.easy.easymanagement.repository;

import com.estoque.gerenciador.management.easy.easymanagement.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String login);
}
