package com.estoque.gerenciador.management.easy.easymanagement.mapper;

import com.estoque.gerenciador.management.easy.easymanagement.dto.movimentacao.MovimentacaoDto;
import com.estoque.gerenciador.management.easy.easymanagement.dto.movimentacao.MovimentacaoDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.model.Categorias;
import com.estoque.gerenciador.management.easy.easymanagement.model.EstoqueLotes;
import com.estoque.gerenciador.management.easy.easymanagement.model.MovimentacaoEstoque;
import com.estoque.gerenciador.management.easy.easymanagement.model.Produto;
import com.estoque.gerenciador.management.easy.easymanagement.repository.EstoqueLotesRepository;
import com.estoque.gerenciador.management.easy.easymanagement.repository.ProdutoRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class MovimentacaoMapper {

    @Autowired
    protected ProdutoRepository produtoRepository;

    @Autowired
    protected EstoqueLotesRepository estoqueLotesRepository;

    @Mapping(target = "produto", expression = "java(produtoRepository.findById(movimentacaoDto.getProduto_id()).orElse(null))")
    @Mapping(target = "lote", expression = "java(estoqueLotesRepository.findById(movimentacaoDto.getLote_id()).orElse(null))")
    public abstract MovimentacaoEstoque toEntity(MovimentacaoDto movimentacaoDto);

    @Mapping(target = "produto", expression = "java(produtoRepository.findById(movimentacaoDto.getProduto_id()).orElse(null))")
    @Mapping(target = "lote", source = "lote")
    public abstract MovimentacaoEstoque toEntity(MovimentacaoDto movimentacaoDto, EstoqueLotes lote);

    @Mapping(target = "lote_id", source = "lote.id")
    @Mapping(target = "produto_id", source = "produto.id")
    @Mapping(target = "produto_nome", source = "produto.nome")
    @Mapping(target = "usuario_login", source = "usuario.login")
    public abstract MovimentacaoDtoRetorno toDto(MovimentacaoEstoque movimentacaoEstoque);

    protected Produto mapProduto(Long id) {
        return id == null ? null : produtoRepository.findById(id).orElse(null);
    }

    protected EstoqueLotes mapEstoqueLotes(Long id) {
        return id == null ? null : estoqueLotesRepository.findById(id).orElse(null);
    }


}
