package com.estoque.gerenciador.management.easy.easymanagement.controller;

import com.estoque.gerenciador.management.easy.easymanagement.dto.produto.ProdutoDto;
import com.estoque.gerenciador.management.easy.easymanagement.dto.produto.ProdutoDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.mapper.ProdutoMapper;
import com.estoque.gerenciador.management.easy.easymanagement.model.Produto;
import com.estoque.gerenciador.management.easy.easymanagement.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoMapper produtoMapper;

    @PostMapping
    public ResponseEntity<ProdutoDtoRetorno> cadastrarProduto(@RequestBody @Valid ProdutoDto dto){
        Produto produto = produtoService.cadastrarProduto(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produto.getId())
                .toUri();

        return ResponseEntity.created(location).body(produtoMapper.toDto(produto));
    }
}
