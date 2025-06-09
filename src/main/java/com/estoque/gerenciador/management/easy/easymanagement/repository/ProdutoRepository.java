package com.estoque.gerenciador.management.easy.easymanagement.repository;

import com.estoque.gerenciador.management.easy.easymanagement.model.Categorias;
import com.estoque.gerenciador.management.easy.easymanagement.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByNomeAndDescricao(String nome, String descricao);

    boolean existsByCategoriaAndAtivoTrue(Categorias categoria);

    @Query("SELECT COUNT(p) FROM Produto p")
    Integer totalProdutosCadastrados();

    @Query("SELECT COUNT(p) FROM Produto p WHERE p.ativo = true")
    Integer totalProdutosCadastradosAtivo();

    @Query("SELECT COUNT(p) FROM Produto p WHERE p.ativo = false")
    Integer totalProdutosCadastradosDesativado();
}
