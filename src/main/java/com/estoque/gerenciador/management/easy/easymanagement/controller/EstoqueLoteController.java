package com.estoque.gerenciador.management.easy.easymanagement.controller;

import com.estoque.gerenciador.management.easy.easymanagement.dto.estoqueLote.EstoqueLoteDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.dto.movimentacao.MovimentacaoDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.model.enuns.TipoMovimentacao;
import com.estoque.gerenciador.management.easy.easymanagement.service.EstoqueLoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("lotes")
public class EstoqueLoteController implements GenericController{

    @Autowired
    private EstoqueLoteService estoqueLoteService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ESTOQUE_BUSCAR')")
    public ResponseEntity<EstoqueLoteDtoRetorno> buscarPorId(@PathVariable Long id){
        EstoqueLoteDtoRetorno lote = estoqueLoteService.buscarEstoqueLoteId(id);
        return ResponseEntity.ok(lote);
    }

    @GetMapping("/produto/{id}/lotes")
    @PreAuthorize("hasAuthority('ESTOQUE_BUSCAR')")
    public ResponseEntity<Page<EstoqueLoteDtoRetorno>> buscarLotesPorProduto(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "2") int tamanho) {

        Page<EstoqueLoteDtoRetorno> lotesPaginados = estoqueLoteService.exibirLotesDoProduto(id, pagina, tamanho);
        return ResponseEntity.ok(lotesPaginados);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ESTOQUE_BUSCAR')")
    public ResponseEntity<List<EstoqueLoteDtoRetorno>> buscarPorExemplo(
            @RequestParam(value = "lote_id", required = false) Long lote,
            @RequestParam(value = "produto_id", required = false) Long produto,
            @RequestParam(value = "codigo_de_barras", required = false) String codigoDeBarras
    ){
        List<EstoqueLoteDtoRetorno> lista = estoqueLoteService.pesquisarPorExample(lote, produto, codigoDeBarras);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/total/{id}")
    @PreAuthorize("hasAuthority('ESTOQUE_BUSCAR')")
    public ResponseEntity<String> exibirValorTotalProdutoEstoque(@PathVariable Long id){
        int total = estoqueLoteService.exibirQuantidadeTotalProdutoEstoque(id);

        return ResponseEntity.ok("O valor total do produto em estoque é de: " + total);
    }

}
