package com.estoque.gerenciador.management.easy.easymanagement.dto.grupo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GrupoDto {

    @NotBlank(message = "Campo Obrigatório")
    String nome;

    List<Long> permissoes;
}
