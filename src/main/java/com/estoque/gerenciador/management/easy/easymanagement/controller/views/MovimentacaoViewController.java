package com.estoque.gerenciador.management.easy.easymanagement.controller.views;

import com.estoque.gerenciador.management.easy.easymanagement.dto.movimentacao.MovimentacaoDto;
import com.estoque.gerenciador.management.easy.easymanagement.dto.movimentacao.MovimentacaoDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.dto.produto.ProdutoDto;
import com.estoque.gerenciador.management.easy.easymanagement.dto.produto.ProdutoDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.exceptions.DesativarCategoriaException;
import com.estoque.gerenciador.management.easy.easymanagement.exceptions.EntidadeNaoEncontradaException;
import com.estoque.gerenciador.management.easy.easymanagement.exceptions.MovimentacaoInvalidaException;
import com.estoque.gerenciador.management.easy.easymanagement.exceptions.RegistroDuplicadoException;
import com.estoque.gerenciador.management.easy.easymanagement.model.enuns.TipoMovimentacao;
import com.estoque.gerenciador.management.easy.easymanagement.service.MovimentacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("movimentacao")
public class MovimentacaoViewController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @GetMapping
    public String paginaInicialMovimentacao() {
        return "movimentacao/index";
    }

    @GetMapping("/buscar")
    public String exibirFormularioBusca(){
        return "movimentacao/buscar";
    }

    @GetMapping("/buscar/resultados")
    public String buscarMovimentacoes(
            @RequestParam(value = "produto_id", required = false) Long produto,
            @RequestParam(value = "lote_id", required = false) Long lote,
            @RequestParam(value = "tipo_movimentacao", required = false) TipoMovimentacao tipoMovimentacao,
            @RequestParam(value = "quantidade", required = false) Integer quantidade,
            Model model
    ) {
        List<MovimentacaoDtoRetorno> movimentacoes = movimentacaoService.pesquisarPorExample(produto, lote, tipoMovimentacao, quantidade);
        model.addAttribute("movimentacoes", movimentacoes);
        return "movimentacao/buscar";
    }


    @GetMapping("/cadastrar")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("movimentacaoDto", new MovimentacaoDto());
        return "movimentacao/cadastrar";
    }

    @PostMapping("/cadastrar")
    public String cadastrarMovimentacao(@Valid @ModelAttribute("movimentacaoDto") MovimentacaoDto movimentacaoDto,
                                   BindingResult result,
                                   Model model) {
        if (result.hasErrors()) {
            return "movimentacao/cadastrar";
        }

        try {
            movimentacaoService.cadastrarMovimentacao(movimentacaoDto);
            model.addAttribute("cadastroSucesso", true);
            model.addAttribute("movimentacaoDto", new MovimentacaoDto()); // limpa formulário
        } catch (MovimentacaoInvalidaException | EntidadeNaoEncontradaException e) {
            model.addAttribute("erroMensagem", e.getMessage());
            model.addAttribute("movimentacaoDto", movimentacaoDto);         // mantém os dados preenchidos
        }

        return "movimentacao/cadastrar";
    }
}
