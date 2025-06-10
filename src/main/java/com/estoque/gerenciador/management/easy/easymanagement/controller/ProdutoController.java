package com.estoque.gerenciador.management.easy.easymanagement.controller;

import com.estoque.gerenciador.management.easy.easymanagement.dto.produto.ProdutoDto;
import com.estoque.gerenciador.management.easy.easymanagement.dto.produto.ProdutoDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.mapper.ProdutoMapper;
import com.estoque.gerenciador.management.easy.easymanagement.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/produtos")
public class ProdutoController implements GenericController{

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoMapper produtoMapper;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProdutoDtoRetorno> cadastrarProduto(@RequestBody @Valid ProdutoDto dto){
        ProdutoDtoRetorno produto = produtoService.cadastrarProduto(dto);

        URI location = gerarHeaderLocation(produto.id());

        return ResponseEntity.created(location).body(produto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERADOR')")
    public ResponseEntity<ProdutoDtoRetorno> buscarPorId(@PathVariable Long id){
        ProdutoDtoRetorno produto = produtoService.buscarProdutoId(id);
        return ResponseEntity.ok(produto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERADOR')")
    public ResponseEntity<List<ProdutoDtoRetorno>> buscarPorExemplo(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "descricao", required = false) String descricao,
            @RequestParam(value = "categoria_id", required = false)Long categoria,
            @RequestParam(value = "ativo", required = false) Boolean ativo
    ){
        List<ProdutoDtoRetorno> lista = produtoService.pesquisarPorExample(nome, descricao, categoria, ativo);
        return ResponseEntity.ok(lista);
    }


    @DeleteMapping("/desativar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<String> desativarProduto(@PathVariable Long id){
        produtoService.desativarProduto(id);
        return ResponseEntity.ok("Produto desativado com Sucesso");
    }

    @DeleteMapping("/deletar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<String> deletarProduto(@PathVariable Long id){
        produtoService.deletarProduto(id);
        return ResponseEntity.ok("Produto deletado com Sucesso");
    }
}
