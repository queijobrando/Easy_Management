package com.estoque.gerenciador.management.easy.easymanagement.controller;

import com.estoque.gerenciador.management.easy.easymanagement.dto.produto.ProdutoDto;
import com.estoque.gerenciador.management.easy.easymanagement.dto.produto.ProdutoDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.mapper.ProdutoMapper;
import com.estoque.gerenciador.management.easy.easymanagement.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("produtos")
public class ProdutoController implements GenericController{

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoMapper produtoMapper;

    @PostMapping
    public ResponseEntity<ProdutoDtoRetorno> cadastrarProduto(@RequestBody @Valid ProdutoDto dto){
        ProdutoDtoRetorno produto = produtoService.cadastrarProduto(dto);

        URI location = gerarHeaderLocation(produto.id());

        return ResponseEntity.created(location).body(produto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDtoRetorno> buscarPorId(@PathVariable Long id){
        ProdutoDtoRetorno produto = produtoService.buscarProdutoId(id);
        return ResponseEntity.ok(produto);
    }
}
