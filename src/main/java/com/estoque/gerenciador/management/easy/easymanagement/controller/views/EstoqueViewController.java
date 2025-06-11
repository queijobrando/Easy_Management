package com.estoque.gerenciador.management.easy.easymanagement.controller.views;

import com.estoque.gerenciador.management.easy.easymanagement.dto.estoqueLote.EstoqueLoteDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.dto.movimentacao.MovimentacaoDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.model.enuns.TipoMovimentacao;
import com.estoque.gerenciador.management.easy.easymanagement.service.EstoqueLoteService;
import com.estoque.gerenciador.management.easy.easymanagement.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("estoque")
public class EstoqueViewController {

    @Autowired
    private EstoqueLoteService estoqueLoteService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/buscar")
    public String exibirFormularioBusca(Model model){
        model.addAttribute("produtos", produtoService.buscarTodos());
        return "estoque/buscar";
    }

    @GetMapping("/buscar/resultados")
    public String buscarMovimentacoes(
            @RequestParam(value = "lote_id", required = false) Long lote,
            @RequestParam(value = "produto_id", required = false) Long produto,
            @RequestParam(value = "codigo_de_barras", required = false) String codigoDeBarras,
            Model model
    ) {
        List<EstoqueLoteDtoRetorno> lotes = estoqueLoteService.pesquisarPorExample(lote, produto, codigoDeBarras);
        model.addAttribute("lotes", lotes);
        model.addAttribute("produtos", produtoService.buscarTodos());
        return "estoque/buscar";
    }
}
