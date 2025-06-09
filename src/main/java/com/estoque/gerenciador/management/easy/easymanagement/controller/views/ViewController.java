package com.estoque.gerenciador.management.easy.easymanagement.controller.views;

import com.estoque.gerenciador.management.easy.easymanagement.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // paginas web
public class ViewController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/login")
    public String paginaLogin(){
        return "login";
    }

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("totalprodutos", produtoService.totalProdutosCadastrados());
        model.addAttribute("produtosAtivos", produtoService.totalProdutosCadastradosAtivo());
        model.addAttribute("produtosDesativados", produtoService.totalProdutosCadastradosDesativado());

        return "home";
    }

}
