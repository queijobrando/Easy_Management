package com.estoque.gerenciador.management.easy.easymanagement.controller;

import com.estoque.gerenciador.management.easy.easymanagement.dto.estoqueLote.EstoqueLoteDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.dto.movimentacao.MovimentacaoDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.model.enuns.TipoMovimentacao;
import com.estoque.gerenciador.management.easy.easymanagement.service.EstoqueLoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("lotes")
public class EstoqueLoteController implements GenericController{

    @Autowired
    private EstoqueLoteService estoqueLoteService;

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueLoteDtoRetorno> buscarPorId(@PathVariable Long id){
        EstoqueLoteDtoRetorno lote = estoqueLoteService.buscarEstoqueLoteId(id);
        return ResponseEntity.ok(lote);
    }

    @GetMapping
    public ResponseEntity<List<EstoqueLoteDtoRetorno>> buscarPorExemplo(
            @RequestParam(value = "lote_id", required = false) Long lote,
            @RequestParam(value = "produto_id", required = false) Long produto,
            @RequestParam(value = "codigo_de_barras", required = false) String codigoDeBarras,
            @RequestParam(value = "quantidade", required = false) Integer quantidadeLote
    ){
        List<EstoqueLoteDtoRetorno> lista = estoqueLoteService.pesquisarPorExample(lote, produto, codigoDeBarras, quantidadeLote);
        return ResponseEntity.ok(lista);
    }

}
