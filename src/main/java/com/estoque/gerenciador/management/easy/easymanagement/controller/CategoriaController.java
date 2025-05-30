package com.estoque.gerenciador.management.easy.easymanagement.controller;

import com.estoque.gerenciador.management.easy.easymanagement.dto.categoria.CategoriaDto;
import com.estoque.gerenciador.management.easy.easymanagement.dto.categoria.CategoriaDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.mapper.CategoriaMapper;
import com.estoque.gerenciador.management.easy.easymanagement.model.Categorias;
import com.estoque.gerenciador.management.easy.easymanagement.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("categorias")
public class CategoriaController implements GenericController {

    @Autowired
    private CategoriaService categoriaService;
    

    @PostMapping
    public ResponseEntity<CategoriaDtoRetorno> cadastrarCategoria(@RequestBody @Valid CategoriaDto dto){
        CategoriaDtoRetorno categoria = categoriaService.cadastrarCategoria(dto);

        URI location = gerarHeaderLocation(categoria.id());

        return ResponseEntity.created(location).body(categoria);
    }

}
