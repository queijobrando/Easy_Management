package com.estoque.gerenciador.management.easy.easymanagement.controller.views;

import com.estoque.gerenciador.management.easy.easymanagement.dto.categoria.CategoriaDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("categorias")
public class CategoriaViewController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/buscar")
    public String buscarCategorias(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "descricao", required = false) String descricao,
            @RequestParam(value = "ativo", required = false) Boolean ativo,
            Model model
    ){
        List<CategoriaDtoRetorno> categorias = categoriaService.pesquisarPorExample(nome, descricao, ativo);
        model.addAttribute("categorias", categorias);
        return "produtos/categorias";
    }
}
