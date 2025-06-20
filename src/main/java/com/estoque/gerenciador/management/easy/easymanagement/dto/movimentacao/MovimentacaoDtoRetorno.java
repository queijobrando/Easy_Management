package com.estoque.gerenciador.management.easy.easymanagement.dto.movimentacao;

import com.estoque.gerenciador.management.easy.easymanagement.model.enuns.TipoMovimentacao;

import java.time.LocalDateTime;

public record MovimentacaoDtoRetorno(
        Long id,
        TipoMovimentacao tipo_movimentacao,
        Integer quantidade,
        Long lote_id,
        Long produto_id,
        String produto_nome,
        String usuario_login,
        LocalDateTime data_movimentacao,
        String observacao
) {
}
