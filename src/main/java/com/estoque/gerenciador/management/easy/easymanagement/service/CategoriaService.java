package com.estoque.gerenciador.management.easy.easymanagement.service;

import com.estoque.gerenciador.management.easy.easymanagement.dto.categoria.CategoriaDto;
import com.estoque.gerenciador.management.easy.easymanagement.dto.categoria.CategoriaDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.exceptions.EntidadeNaoEncontradaException;
import com.estoque.gerenciador.management.easy.easymanagement.mapper.CategoriaMapper;
import com.estoque.gerenciador.management.easy.easymanagement.model.Categorias;
import com.estoque.gerenciador.management.easy.easymanagement.repository.CategoriaRepository;
import com.estoque.gerenciador.management.easy.easymanagement.service.validator.CategoriaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaMapper categoriaMapper;

    @Autowired
    private CategoriaValidator categoriaValidator;

    @Transactional
    public CategoriaDtoRetorno cadastrarCategoria(CategoriaDto dto){
        Categorias categoria = categoriaMapper.toEntity(dto);
        categoriaValidator.validarDuplicidade(categoria);
        categoriaRepository.save(categoria);

        return categoriaMapper.toDto(categoria);
    }

    public CategoriaDtoRetorno buscarCategoriaId(Long id){
        Categorias categorias = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Categoria não encontrada"));

        return categoriaMapper.toDto(categorias);
    }

    public List<CategoriaDtoRetorno> pesquisarPorExample(String nome, String descricao, Boolean ativo){
        var categoria = new Categorias();
        categoria.setNome(nome);
        categoria.setDescricao(descricao);
        categoria.setAtivo(ativo);

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return categoriaRepository
                .findAll(Example.of(categoria, matcher))
                .stream()
                .map(categoriaMapper::toDto)
                .toList();

    }

    @Transactional
    public void desativarCategoria(Long id){
        Categorias categorias = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Categoria não encontrada"));

        categoriaValidator.produtoAtivoNaCategoria(categorias);
        categorias.setAtivo(false);
        categoriaRepository.save(categorias);
    }

}
