package com.estoque.gerenciador.management.easy.easymanagement.controller.views;

import com.estoque.gerenciador.management.easy.easymanagement.dto.produto.ProdutoDto;
import com.estoque.gerenciador.management.easy.easymanagement.dto.produto.ProdutoDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.exceptions.DesativarCategoriaException;
import com.estoque.gerenciador.management.easy.easymanagement.exceptions.EntidadeNaoEncontradaException;
import com.estoque.gerenciador.management.easy.easymanagement.exceptions.RegistroDuplicadoException;
import com.estoque.gerenciador.management.easy.easymanagement.model.Produto;
import com.estoque.gerenciador.management.easy.easymanagement.service.CategoriaService;
import com.estoque.gerenciador.management.easy.easymanagement.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("produtos")
public class ProdutoViewController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/buscar")
    public String exibirFormularioBusca(){
        return "produtos/buscar";
    }

    @GetMapping("/buscar/resultados")
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

    @GetMapping("/cadastrar")
    @PreAuthorize("hasRole('ADMIN')")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("produtoDto", new ProdutoDto());
        model.addAttribute("categorias", categoriaService.buscarTodas());
        return "produtos/cadastrar";
    }

    @PostMapping("/cadastrar")
    @PreAuthorize("hasRole('ADMIN')")
    public String cadastrarProduto(@Valid @ModelAttribute("produtoDto") ProdutoDto produtoDto,
                                   BindingResult result,
                                   Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.buscarTodas());
            return "produtos/cadastrar";
        }

        try {
            produtoService.cadastrarProduto(produtoDto);
            model.addAttribute("cadastroSucesso", true);
            model.addAttribute("produtoDto", new ProdutoDto()); // limpa formulário
        } catch (DesativarCategoriaException | RegistroDuplicadoException e) {
            model.addAttribute("erroMensagem", e.getMessage());
            model.addAttribute("produtoDto", produtoDto);         // mantém os dados preenchidos
            model.addAttribute("categorias", categoriaService.buscarTodas());
        }

        return "produtos/cadastrar";
    }

    @DeleteMapping("{id}")
    public String desativarProduto(@PathVariable Long id) {
        produtoService.desativarProduto(id);
        return "redirect:/produtos/buscar"; // ou para a URL que quiser retornar após ação
    }

}
