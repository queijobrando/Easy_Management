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
import org.springframework.data.domain.Page;
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
    @PreAuthorize("hasAuthority('PRODUTO_BUSCAR')")
    public String buscarProdutos(@RequestParam(defaultValue = "1") int pagina,
                                 @RequestParam(name = "nome", required = false) String nome,
                                 Model model) {

        Page<ProdutoDtoRetorno> paginaProdutos = produtoService.buscarTodosPorNome(pagina, nome == null ? "" : nome);

        model.addAttribute("produtos", paginaProdutos.getContent());
        model.addAttribute("paginaAtual", pagina);
        model.addAttribute("totalPaginas", paginaProdutos.getTotalPages());
        model.addAttribute("totalProdutos", paginaProdutos.getTotalElements());
        model.addAttribute("nomeBuscado", nome);

        return "produtos/buscar";
    }

    @GetMapping("/cadastrar")
    @PreAuthorize("hasAuthority('PRODUTO_CADASTRAR')")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("produtoDto", new ProdutoDto());
        model.addAttribute("categorias", categoriaService.buscarTodas());
        return "produtos/cadastrar";
    }

    @PostMapping("/cadastrar")
    @PreAuthorize("hasAuthority('PRODUTO_CADASTRAR')")
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
    @PreAuthorize("hasAuthority('PRODUTO_DELETAR')")
    public String desativarProduto(@PathVariable Long id) {
        produtoService.desativarProduto(id);
        return "redirect:/produtos/buscar"; // ou para a URL que quiser retornar após ação
    }

}
