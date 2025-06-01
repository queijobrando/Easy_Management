package com.estoque.gerenciador.management.easy.easymanagement.dto.movimentacao;

import com.estoque.gerenciador.management.easy.easymanagement.model.enuns.TipoMovimentacao;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MovimentacaoDto(
        @NotNull(message = "Campo Obrigatório")
        TipoMovimentacao tipoMovimentacao,
        @NotNull(message = "Campo Obrigatório")
        Long produto_id,
        Long lote_id,
        @NotNull(message = "Campo Obrigatório")
        @Min(1)
        Integer quantidade,
        LocalDate validade,
        String observacao
) {
}
