package com.estoque.gerenciador.management.easy.easymanagement.mapper;

import com.estoque.gerenciador.management.easy.easymanagement.dto.movimentacao.MovimentacaoDto;
import com.estoque.gerenciador.management.easy.easymanagement.dto.movimentacao.MovimentacaoDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.model.MovimentacaoEstoque;
import com.estoque.gerenciador.management.easy.easymanagement.repository.EstoqueLotesRepository;
import com.estoque.gerenciador.management.easy.easymanagement.repository.ProdutoRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class MovimentacaoMapper {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    EstoqueLotesRepository estoqueLotesRepository;

    @Mapping(target = "produto", expression = "java(produtoRepository.findById(movimentacaoDto.produto_id()).orElse(null))")
    @Mapping(target = "lote", expression = "java(estoqueLotesRepository.findById(movimentacaoDto.lote_id()).orElse(null))")
    public abstract MovimentacaoEstoque toEntity(MovimentacaoDto movimentacaoDto);

    @Mapping(target = "lote_id", source = "lote.id")
    @Mapping(target = "produto_id", source = "produto.id")
    @Mapping(target = "produto_nome", source = "produto.nome")
    public abstract MovimentacaoDtoRetorno toDto(MovimentacaoEstoque movimentacaoEstoque);
}
