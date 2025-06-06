package com.estoque.gerenciador.management.easy.easymanagement.mapper;

import com.estoque.gerenciador.management.easy.easymanagement.dto.usuario.UsuarioDto;
import com.estoque.gerenciador.management.easy.easymanagement.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDto usuarioDto);
}
