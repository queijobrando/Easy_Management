package com.estoque.gerenciador.management.easy.easymanagement.mapper;

import com.estoque.gerenciador.management.easy.easymanagement.dto.grupo.GrupoDto;
import com.estoque.gerenciador.management.easy.easymanagement.model.Grupo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GrupoMapper {

    @Mapping(target = "permissoes", ignore = true)
    Grupo toEntity(GrupoDto grupoDto);
}
