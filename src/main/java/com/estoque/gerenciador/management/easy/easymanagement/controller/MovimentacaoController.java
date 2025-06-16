package com.estoque.gerenciador.management.easy.easymanagement.controller;

import com.estoque.gerenciador.management.easy.easymanagement.dto.movimentacao.MovimentacaoDto;
import com.estoque.gerenciador.management.easy.easymanagement.dto.movimentacao.MovimentacaoDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.model.enuns.TipoMovimentacao;
import com.estoque.gerenciador.management.easy.easymanagement.service.MovimentacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/movimentacao")
public class MovimentacaoController implements GenericController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @PostMapping
    @PreAuthorize("hasAuthority('MOVIMENTACAO_CADASTRAR')")
    public ResponseEntity<MovimentacaoDtoRetorno> cadastrarMovimentacao(@RequestBody @Valid MovimentacaoDto dto){
        MovimentacaoDtoRetorno movimentacao = movimentacaoService.cadastrarMovimentacao(dto);

        URI location = gerarHeaderLocation(movimentacao.id());

        return ResponseEntity.created(location).body(movimentacao);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('MOVIMENTACAO_BUSCAR')")
    public ResponseEntity<MovimentacaoDtoRetorno> buscarPorId(@PathVariable Long id){
        MovimentacaoDtoRetorno movimentacao = movimentacaoService.buscarMovimentacaoId(id);
        return ResponseEntity.ok(movimentacao);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('MOVIMENTACAO_BUSCAR')")
    public ResponseEntity<List<MovimentacaoDtoRetorno>> buscarPorExemplo(
            @RequestParam(value = "produto_id", required = false) Long produto,
            @RequestParam(value = "lote_id", required = false) Long lote,
            @RequestParam(value = "tipo_movimentacao", required = false) TipoMovimentacao tipoMovimentacao,
            @RequestParam(value = "quantidade", required = false) Integer quantidade
    ){
        List<MovimentacaoDtoRetorno> lista = movimentacaoService.pesquisarPorExample(produto, lote, tipoMovimentacao, quantidade);
        return ResponseEntity.ok(lista);
    }

}
