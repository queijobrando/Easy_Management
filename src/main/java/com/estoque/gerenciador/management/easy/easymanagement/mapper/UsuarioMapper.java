package com.estoque.gerenciador.management.easy.easymanagement.mapper;

import com.estoque.gerenciador.management.easy.easymanagement.dto.usuario.UsuarioDto;
import com.estoque.gerenciador.management.easy.easymanagement.dto.usuario.UsuarioDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "grupo", ignore = true)
    Usuario toEntity(UsuarioDto usuarioDto);

    @Mapping(target = "grupo_nome", source = "grupo.nome")
    UsuarioDtoRetorno toDto(Usuario usuario);
}
