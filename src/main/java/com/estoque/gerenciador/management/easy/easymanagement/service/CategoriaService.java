package com.estoque.gerenciador.management.easy.easymanagement.service;

import com.estoque.gerenciador.management.easy.easymanagement.dto.categoria.CategoriaDto;
import com.estoque.gerenciador.management.easy.easymanagement.dto.categoria.CategoriaDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.mapper.CategoriaMapper;
import com.estoque.gerenciador.management.easy.easymanagement.model.Categorias;
import com.estoque.gerenciador.management.easy.easymanagement.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaMapper categoriaMapper;

    @Transactional
    public CategoriaDtoRetorno cadastrarCategoria(CategoriaDto dto){
        Categorias categoria = categoriaMapper.toEntity(dto);
        categoriaRepository.save(categoria);

        return categoriaMapper.toDto(categoria);
    }

}
