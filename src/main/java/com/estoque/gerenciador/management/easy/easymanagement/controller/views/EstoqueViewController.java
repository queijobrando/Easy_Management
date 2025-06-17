package com.estoque.gerenciador.management.easy.easymanagement.controller.views;

import com.estoque.gerenciador.management.easy.easymanagement.dto.estoqueLote.EstoqueLoteDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.dto.estoqueLote.ProdutoEstoqueViewDto;
import com.estoque.gerenciador.management.easy.easymanagement.dto.movimentacao.MovimentacaoDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.dto.produto.ProdutoDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.model.enuns.TipoMovimentacao;
import com.estoque.gerenciador.management.easy.easymanagement.service.EstoqueLoteService;
import com.estoque.gerenciador.management.easy.easymanagement.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('ESTOQUE_BUSCAR')")
    public String exibirFormularioBusca(Model model){
        List<ProdutoDtoRetorno> produtos = produtoService.buscarTodos();

        List<ProdutoEstoqueViewDto> produtosEstoque = produtos.stream().map(produto -> {
            Long produtoId = produto.id();
            Integer total = estoqueLoteService.exibirQuantidadeTotalProdutoEstoque(produtoId);

            return new ProdutoEstoqueViewDto(
                    produto.id(),
                    produto.nome(),
                    produto.descricao(),
                    produto.codigo_de_barras(),
                    produto.categoria_nome(),
                    produto.usuario_login(),
                    produto.preco(),
                    produto.unidade(),
                    produto.perecivel(),
                    produto.ativo(),
                    total,
                    null // ← lotes agora são carregados via AJAX
            );
        }).toList();

        model.addAttribute("produtos", produtosEstoque);
        return "estoque/buscar";
    }

    @GetMapping("/buscar/resultados")
    @PreAuthorize("hasAuthority('ESTOQUE_BUSCAR')")
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
