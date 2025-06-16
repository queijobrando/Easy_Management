package com.estoque.gerenciador.management.easy.easymanagement.service;

import com.estoque.gerenciador.management.easy.easymanagement.config.security.SecurityService;
import com.estoque.gerenciador.management.easy.easymanagement.dto.produto.ProdutoDto;
import com.estoque.gerenciador.management.easy.easymanagement.dto.produto.ProdutoDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.exceptions.EntidadeNaoEncontradaException;
import com.estoque.gerenciador.management.easy.easymanagement.mapper.ProdutoMapper;
import com.estoque.gerenciador.management.easy.easymanagement.model.Produto;
import com.estoque.gerenciador.management.easy.easymanagement.model.Usuario;
import com.estoque.gerenciador.management.easy.easymanagement.repository.CategoriaRepository;
import com.estoque.gerenciador.management.easy.easymanagement.repository.ProdutoRepository;
import com.estoque.gerenciador.management.easy.easymanagement.service.validator.ProdutoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoMapper produtoMapper;

    @Autowired
    private ProdutoValidator produtoValidator;

    @Autowired
    private SecurityService securityService;

    @Transactional
    public ProdutoDtoRetorno cadastrarProduto(ProdutoDto produtoDto){
        categoriaRepository.findById(produtoDto.getCategoria_id())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Categoria não encontrada com ID: " + produtoDto.getCategoria_id()));

        Produto produto = produtoMapper.toEntity(produtoDto);
        produtoValidator.validar(produto);
        Usuario usuario = securityService.obterUsuarioLogado();
        produto.setUsuario(usuario);
        produtoRepository.save(produto);

        return produtoMapper.toDto(produto);
    }

    public ProdutoDtoRetorno buscarProdutoId(Long id){
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado"));

        return produtoMapper.toDto(produto);
    }

    public List<ProdutoDtoRetorno> buscarTodos(){
        return produtoRepository.findAll().stream().map(produtoMapper::toDto).toList();
    }

    public List<ProdutoDtoRetorno> pesquisarPorExample(String nome, String descricao, Long categoria, Boolean ativo){
        var produto = new Produto();
        produto.setNome(nome);
        produto.setDescricao(descricao);
        if (categoria != null) {
            var categoriaEntidade = categoriaRepository.findById(categoria).orElse(null);
            if (categoriaEntidade == null) {
                return List.of();
            }
            produto.setCategoria(categoriaEntidade);
        }

        produto.setAtivo(ativo);
        produto.setAtivo(ativo);

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return produtoRepository
                .findAll(Example.of(produto, matcher))
                .stream()
                .map(produtoMapper::toDto)
                .toList();

    }


    @Transactional
    public void desativarProduto(Long id){
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado"));

        produto.setAtivo(false);
        produtoRepository.save(produto);
    }

    @Transactional
    public void deletarProduto(Long id){
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado"));

        produtoRepository.deleteById(id);
    }

    public Integer totalProdutosCadastrados(){
        return Optional.ofNullable(produtoRepository.totalProdutosCadastrados()).orElse(0);
    }

    public Integer totalProdutosCadastradosAtivo(){
        return Optional.ofNullable(produtoRepository.totalProdutosCadastradosAtivo()).orElse(0);
    }

    public Integer totalProdutosCadastradosDesativado(){
        return Optional.ofNullable(produtoRepository.totalProdutosCadastradosDesativado()).orElse(0);
    }
}
