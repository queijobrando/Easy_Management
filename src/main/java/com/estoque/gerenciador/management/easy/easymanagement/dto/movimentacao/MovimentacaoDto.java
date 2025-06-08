package com.estoque.gerenciador.management.easy.easymanagement.dto.movimentacao;

import com.estoque.gerenciador.management.easy.easymanagement.model.enuns.TipoMovimentacao;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimentacaoDto {
        @NotNull(message = "Campo Obrigatório")
        TipoMovimentacao tipo_movimentacao;
        @NotNull(message = "Campo Obrigatório")
        Long produto_id;
        Long lote_id;
        @NotNull(message = "Campo Obrigatório")
        @Min(1)
        Integer quantidade;
        LocalDate validade;
        String observacao;
}
