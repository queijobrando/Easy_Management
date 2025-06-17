package com.estoque.gerenciador.management.easy.easymanagement.service;

import com.estoque.gerenciador.management.easy.easymanagement.config.security.SecurityService;
import com.estoque.gerenciador.management.easy.easymanagement.dto.categoria.CategoriaDto;
import com.estoque.gerenciador.management.easy.easymanagement.dto.categoria.CategoriaDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.exceptions.EntidadeNaoEncontradaException;
import com.estoque.gerenciador.management.easy.easymanagement.mapper.CategoriaMapper;
import com.estoque.gerenciador.management.easy.easymanagement.model.Categorias;
import com.estoque.gerenciador.management.easy.easymanagement.model.Usuario;
import com.estoque.gerenciador.management.easy.easymanagement.repository.CategoriaRepository;
import com.estoque.gerenciador.management.easy.easymanagement.service.validator.CategoriaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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

    @Autowired
    private SecurityService securityService;

    @Transactional
    public CategoriaDtoRetorno cadastrarCategoria(CategoriaDto dto){
        Categorias categoria = categoriaMapper.toEntity(dto);
        categoriaValidator.validarDuplicidade(categoria);
        Usuario usuario = securityService.obterUsuarioLogado();
        categoria.setUsuario(usuario);
        categoriaRepository.save(categoria);

        return categoriaMapper.toDto(categoria);
    }

    public CategoriaDtoRetorno buscarCategoriaId(Long id){
        Categorias categorias = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Categoria não encontrada"));

        return categoriaMapper.toDto(categorias);
    }

    public List<CategoriaDtoRetorno> buscarTodas(){
        return categoriaRepository.findAll().stream().map(categoriaMapper::toDto).toList();
    }

    public Page<CategoriaDtoRetorno> buscarTodosPorNome(int numeroPagina, String nome) {
        Pageable pageable = PageRequest.of(numeroPagina - 1, 5);

        Page<Categorias> paginaCategorias;

        if (nome == null || nome.trim().isEmpty()) {
            paginaCategorias = categoriaRepository.findAll(pageable);
        } else {
            paginaCategorias = categoriaRepository.findByNomeContainingIgnoreCase(nome, pageable);
        }

        return paginaCategorias.map(categoriaMapper::toDto);
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
