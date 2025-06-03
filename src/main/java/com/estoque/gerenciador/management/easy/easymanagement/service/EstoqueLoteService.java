package com.estoque.gerenciador.management.easy.easymanagement.service;

import com.estoque.gerenciador.management.easy.easymanagement.dto.estoqueLote.EstoqueLoteDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.exceptions.EntidadeNaoEncontradaException;
import com.estoque.gerenciador.management.easy.easymanagement.mapper.EstoqueLoteMapper;
import com.estoque.gerenciador.management.easy.easymanagement.model.EstoqueLotes;
import com.estoque.gerenciador.management.easy.easymanagement.repository.EstoqueLotesRepository;
import com.estoque.gerenciador.management.easy.easymanagement.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<EstoqueLoteDtoRetorno> pesquisarPorExample(Long id ,Long produto, String codigoDeBarras, Integer quantidadeLote){
        var lote = new EstoqueLotes();
        lote.setId(id);
        lote.setProduto(produtoRepository.findById(produto)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O produto inserido não existe")));
        lote.setCodigo_de_barras(codigoDeBarras);
        lote.setQuantidade_lote(quantidadeLote);

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


}
