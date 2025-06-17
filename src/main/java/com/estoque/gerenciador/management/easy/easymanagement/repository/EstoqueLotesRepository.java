package com.estoque.gerenciador.management.easy.easymanagement.repository;

import com.estoque.gerenciador.management.easy.easymanagement.model.EstoqueLotes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface EstoqueLotesRepository extends JpaRepository<EstoqueLotes, Long> {

    @Query("SELECT SUM(e.quantidade_lote) FROM EstoqueLotes e WHERE e.produto.id = :produtoId")
    Integer somarQuantidadeProduto(@Param("produtoId") Long produtoId);

    Page<EstoqueLotes> findAllByProduto_Id(Long id, Pageable pageable);

}
