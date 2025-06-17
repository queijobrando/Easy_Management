package com.estoque.gerenciador.management.easy.easymanagement.controller.views;

import com.estoque.gerenciador.management.easy.easymanagement.dto.categoria.CategoriaDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('CATEGORIA_BUSCAR')")
    public String buscarProdutos(@RequestParam(defaultValue = "1") int pagina,
                                 @RequestParam(name = "nome", required = false) String nome,
                                 Model model) {

        Page<CategoriaDtoRetorno> paginaCategorias = categoriaService.buscarTodosPorNome(pagina, nome == null ? "" : nome);

        model.addAttribute("categorias", paginaCategorias.getContent());
        model.addAttribute("paginaAtual", pagina);
        model.addAttribute("totalPaginas", paginaCategorias.getTotalPages());
        model.addAttribute("totalProdutos", paginaCategorias.getTotalElements());
        model.addAttribute("nomeBuscado", nome);

        return "categorias/buscar";
    }
}
