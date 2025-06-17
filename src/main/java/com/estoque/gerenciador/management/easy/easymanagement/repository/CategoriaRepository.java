package com.estoque.gerenciador.management.easy.easymanagement.repository;

import com.estoque.gerenciador.management.easy.easymanagement.model.Categorias;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categorias, Long> {
    Optional<Categorias> findByNomeAndDescricao(String nome, String descricao);

    Optional<Categorias> findByIdAndAtivoTrue(Long id);

    Page<Categorias> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
