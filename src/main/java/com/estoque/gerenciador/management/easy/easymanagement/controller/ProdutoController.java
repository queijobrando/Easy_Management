package com.estoque.gerenciador.management.easy.easymanagement.controller;

import com.estoque.gerenciador.management.easy.easymanagement.dto.produto.ProdutoDto;
import com.estoque.gerenciador.management.easy.easymanagement.dto.produto.ProdutoDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.mapper.ProdutoMapper;
import com.estoque.gerenciador.management.easy.easymanagement.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
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
    @PreAuthorize("hasAuthority('PRODUTO_CADASTRAR')")
    public ResponseEntity<ProdutoDtoRetorno> cadastrarProduto(@RequestBody @Valid ProdutoDto dto){
        ProdutoDtoRetorno produto = produtoService.cadastrarProduto(dto);

        URI location = gerarHeaderLocation(produto.id());

        return ResponseEntity.created(location).body(produto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('PRODUTO_BUSCAR')")
    public ResponseEntity<ProdutoDtoRetorno> buscarPorId(@PathVariable Long id){
        ProdutoDtoRetorno produto = produtoService.buscarProdutoId(id);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/buscar")
    @PreAuthorize("hasAuthority('PRODUTO_BUSCAR')")
    public ResponseEntity<Page<ProdutoDtoRetorno>> buscarProdutos(@RequestParam(defaultValue = "1") int pagina,
                                 @RequestParam(name = "nome", required = false) String nome,
                                 Model model) {

        Page<ProdutoDtoRetorno> paginaProdutos = produtoService.buscarTodosPorNome(pagina, nome == null ? "" : nome);
        return ResponseEntity.ok(paginaProdutos);
    }


    @DeleteMapping("/desativar/{id}")
    @PreAuthorize("hasAuthority('PRODUTO_DESATIVAR')")
    @ResponseBody
    public ResponseEntity<String> desativarProduto(@PathVariable Long id){
        produtoService.desativarProduto(id);
        return ResponseEntity.ok("Produto desativado com Sucesso");
    }

    @DeleteMapping("/deletar/{id}")
    @PreAuthorize("hasAuthority('PRODUTO_DELETAR')")
    @ResponseBody
    public ResponseEntity<String> deletarProduto(@PathVariable Long id){
        produtoService.deletarProduto(id);
        return ResponseEntity.ok("Produto deletado com Sucesso");
    }
}
