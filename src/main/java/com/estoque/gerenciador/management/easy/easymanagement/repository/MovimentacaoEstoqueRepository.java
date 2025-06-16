package com.estoque.gerenciador.management.easy.easymanagement.repository;

import com.estoque.gerenciador.management.easy.easymanagement.model.MovimentacaoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long> {
}
