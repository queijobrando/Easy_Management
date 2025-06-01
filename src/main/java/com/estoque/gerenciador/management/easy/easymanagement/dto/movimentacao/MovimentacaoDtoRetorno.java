package com.estoque.gerenciador.management.easy.easymanagement.dto.movimentacao;

import com.estoque.gerenciador.management.easy.easymanagement.model.enuns.TipoMovimentacao;

public record MovimentacaoDtoRetorno(
        Long id,
        TipoMovimentacao tipo_movimentacao,
        Integer quantidade,
        Long lote_id,
        Long produto_id,
        String produto_nome
) {
}
