package com.estoque.gerenciador.management.easy.easymanagement.mapper;

import com.estoque.gerenciador.management.easy.easymanagement.dto.produto.ProdutoDto;
import com.estoque.gerenciador.management.easy.easymanagement.dto.produto.ProdutoDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.model.Categorias;
import com.estoque.gerenciador.management.easy.easymanagement.model.Produto;
import com.estoque.gerenciador.management.easy.easymanagement.repository.CategoriaRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ProdutoMapper {

    @Autowired
    protected CategoriaRepository categoriaRepository;

    @Mapping(target = "categoria", expression = "java(mapCategoria(produtoDto.getCategoria_id()))")
    public abstract Produto toEntity(ProdutoDto produtoDto);

    @Mapping(target = "categoria_nome", source = "categoria.nome")
    @Mapping(target = "usuario_login", source = "usuario.login")
    public abstract ProdutoDtoRetorno toDto(Produto produto);

    protected Categorias mapCategoria(Long id) {
        return id == null ? null : categoriaRepository.findById(id).orElse(null);
    }
}



