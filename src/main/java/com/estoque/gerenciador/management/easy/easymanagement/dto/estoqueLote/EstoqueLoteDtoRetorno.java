package com.estoque.gerenciador.management.easy.easymanagement.dto.estoqueLote;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record EstoqueLoteDtoRetorno(
        Long id,
        Long produto_id,
        String produto_nome,
        Integer quantidade_lote,
        LocalDate validade,
        String codigo_de_barras,
        LocalDate data_cadastro,
        LocalDateTime data_atualizacao
) {
}
