package com.estoque.gerenciador.management.easy.easymanagement.dto.produto;

import com.estoque.gerenciador.management.easy.easymanagement.model.enuns.Unidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDto {
        @NotBlank(message = "Campo Obrigatório")
        String nome;
        @NotBlank(message = "Campo Obrigatório")
        String descricao;
        @NotNull(message = "Campo Obrigatório")
        Long categoria_id;
        @NotNull(message = "Campo Obrigatório")
        BigDecimal preco;
        @NotNull(message = "Campo Obrigatório")
        Unidade unidade;
        @NotNull(message = "Campo Obrigatório")
        Boolean perecivel;
}
