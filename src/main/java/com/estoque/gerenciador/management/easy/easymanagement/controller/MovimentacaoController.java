package com.estoque.gerenciador.management.easy.easymanagement.controller;

import com.estoque.gerenciador.management.easy.easymanagement.dto.movimentacao.MovimentacaoDto;
import com.estoque.gerenciador.management.easy.easymanagement.dto.movimentacao.MovimentacaoDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.service.MovimentacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("movimentacao")
public class MovimentacaoController implements GenericController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @PostMapping
    public ResponseEntity<MovimentacaoDtoRetorno> cadastrarMovimentacao(@RequestBody @Valid MovimentacaoDto dto){
        MovimentacaoDtoRetorno movimentacao = movimentacaoService.cadastrarMovimentacao(dto);

        URI location = gerarHeaderLocation(movimentacao.id());

        return ResponseEntity.created(location).body(movimentacao);
    }

}
