package com.estoque.gerenciador.management.easy.easymanagement.dto.estoqueLote;

import com.estoque.gerenciador.management.easy.easymanagement.model.enuns.Unidade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoEstoqueViewDto {
    Long id;
    String nome;
    String descricao;
    String codigo_de_barras;
    String categoria_nome;
    String usuario_login;
    BigDecimal preco;
    Unidade unidade;
    Boolean perecivel;
    Boolean ativo;
    Integer totalQuantidade;
    List<EstoqueLoteDtoRetorno> lotes;

}
