package com.estoque.gerenciador.management.easy.easymanagement.service;

import com.estoque.gerenciador.management.easy.easymanagement.dto.produto.ProdutoDto;
import com.estoque.gerenciador.management.easy.easymanagement.mapper.ProdutoMapper;
import com.estoque.gerenciador.management.easy.easymanagement.model.Produto;
import com.estoque.gerenciador.management.easy.easymanagement.repository.CategoriaRepository;
import com.estoque.gerenciador.management.easy.easymanagement.repository.ProdutoRepository;
import com.estoque.gerenciador.management.easy.easymanagement.service.validator.ProdutoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoMapper produtoMapper;

    @Autowired
    private ProdutoValidator produtoValidator;

    @Transactional
    public Produto cadastrarProduto(ProdutoDto produtoDto){
        Produto produto = produtoMapper.toEntity(produtoDto);
        produtoValidator.validar(produto);
        produtoRepository.save(produto);

        return produto;
    }
}
