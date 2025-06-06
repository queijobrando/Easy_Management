package com.estoque.gerenciador.management.easy.easymanagement.controller;

import com.estoque.gerenciador.management.easy.easymanagement.dto.produto.ProdutoDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("produtos")
public class ProdutoViewController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public String paginaInicialProdutos() {
        return "produtos/index";
    }

    @GetMapping("/buscar")
    public String buscarProdutos(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "descricao", required = false) String descricao,
            @RequestParam(value = "categoria_id", required = false) Long categoria,
            @RequestParam(value = "ativo", required = false) Boolean ativo,
            Model model
    ) {
        List<ProdutoDtoRetorno> produtos = produtoService.pesquisarPorExample(nome, descricao, categoria, ativo);
        model.addAttribute("produtos", produtos);
        return "produtos/buscar";
    }
}
