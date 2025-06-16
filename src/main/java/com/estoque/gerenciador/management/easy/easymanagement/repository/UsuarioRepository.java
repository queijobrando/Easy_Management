package com.estoque.gerenciador.management.easy.easymanagement.repository;

import com.estoque.gerenciador.management.easy.easymanagement.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByLogin(String login);
}
