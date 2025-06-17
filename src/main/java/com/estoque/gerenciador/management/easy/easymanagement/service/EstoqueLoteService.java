package com.estoque.gerenciador.management.easy.easymanagement.service;

import com.estoque.gerenciador.management.easy.easymanagement.dto.estoqueLote.EstoqueLoteDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.exceptions.EntidadeNaoEncontradaException;
import com.estoque.gerenciador.management.easy.easymanagement.mapper.EstoqueLoteMapper;
import com.estoque.gerenciador.management.easy.easymanagement.model.EstoqueLotes;
import com.estoque.gerenciador.management.easy.easymanagement.repository.EstoqueLotesRepository;
import com.estoque.gerenciador.management.easy.easymanagement.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueLoteService {

    @Autowired
    private EstoqueLotesRepository estoqueLotesRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstoqueLoteMapper estoqueLoteMapper;

    public EstoqueLoteDtoRetorno buscarEstoqueLoteId(Long id){
        EstoqueLotes estoqueLotes = estoqueLotesRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Lote não encontrado"));

        return estoqueLoteMapper.toDto(estoqueLotes);
    }

    public List<EstoqueLoteDtoRetorno> pesquisarPorExample(Long id ,Long produto, String codigoDeBarras){
        var lote = new EstoqueLotes();
        lote.setId(id);
        if (produto != null) {
            var produtoEntidade = produtoRepository.findById(produto).orElse(null);
            if (produtoEntidade == null) {
                return List.of();
            }
            lote.setProduto(produtoEntidade);
        }
        lote.setCodigo_de_barras(codigoDeBarras);

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return estoqueLotesRepository
                .findAll(Example.of(lote, matcher))
                .stream()
                .map(estoqueLoteMapper::toDto)
                .toList();

    }

    public int exibirQuantidadeTotalProdutoEstoque(Long id) {
        return Optional.ofNullable(estoqueLotesRepository.somarQuantidadeProduto(id)).orElse(0);
    }

    public Page<EstoqueLoteDtoRetorno> exibirLotesDoProduto(Long id, int numeroPagina, int tamanhoPagina) {
        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        Page<EstoqueLotes> paginaLotes = estoqueLotesRepository.findAllByProduto_Id(id, pageable);
        return paginaLotes.map(estoqueLoteMapper::toDto);
    }



}
