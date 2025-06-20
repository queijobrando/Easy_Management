package com.estoque.gerenciador.management.easy.easymanagement.mapper;

import com.estoque.gerenciador.management.easy.easymanagement.dto.estoqueLote.EstoqueLoteDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.dto.movimentacao.MovimentacaoDto;
import com.estoque.gerenciador.management.easy.easymanagement.model.EstoqueLotes;
import com.estoque.gerenciador.management.easy.easymanagement.repository.ProdutoRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class EstoqueLoteMapper {

    @Autowired
    ProdutoRepository produtoRepository;

    @Mapping(target = "produto", expression = "java(produtoRepository.findById(movimentacaoDto.getProduto_id()).orElse(null))")
    @Mapping(target = "quantidade_lote", source = "quantidade")
    public abstract EstoqueLotes toEntity(MovimentacaoDto movimentacaoDto);

    @Mapping(target = "produto_id", source = "produto.id")
    @Mapping(target = "produto_nome", source = "produto.nome")
    public abstract EstoqueLoteDtoRetorno toDto(EstoqueLotes estoqueLotes);
}
