package com.estoque.gerenciador.management.easy.easymanagement.service.validator;

import com.estoque.gerenciador.management.easy.easymanagement.dto.movimentacao.MovimentacaoDto;
import com.estoque.gerenciador.management.easy.easymanagement.exceptions.MovimentacaoInvalidaException;
import com.estoque.gerenciador.management.easy.easymanagement.model.Produto;
import com.estoque.gerenciador.management.easy.easymanagement.model.EstoqueLotes;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Component
public class MovimentacaoValidator {

    public void validarEntrada(MovimentacaoDto dto, Produto produto, EstoqueLotes lote) {
        if (produto.getPerecivel() && dto.validade() == null) {
            throw new MovimentacaoInvalidaException("Campo validade obrigatório quando produto é perecível");
        }

        if (lote != null) {
            if (!Objects.equals(lote.getValidade(), dto.validade())) {
                throw new MovimentacaoInvalidaException("A validade dos produtos da movimentação de ENTRADA não pode ser diferente da validade do lote");
            }

            if (lote.getProduto() != produto) {
                throw new MovimentacaoInvalidaException("O produto informado não pertence ao lote informado");
            }
        }
    }

    public void validarSaida(MovimentacaoDto dto, Produto produto, EstoqueLotes lote) {
        if (lote == null) {
            throw new MovimentacaoInvalidaException("É necessário informar um lote para movimentações de SAIDA");
        }

        if (lote.getProduto() != produto) {
            throw new MovimentacaoInvalidaException("O produto informado não pertence ao lote informado");
        }

        if (produto.getPerecivel() && lote.getValidade() != null && lote.getValidade().isBefore(LocalDate.now())) {
            throw new MovimentacaoInvalidaException("A validade do lote já venceu");
        }

        if (dto.quantidade() > lote.getQuantidade_lote()) {
            throw new MovimentacaoInvalidaException("Quantidade de SAIDA maior que a do lote");
        }
    }
}
