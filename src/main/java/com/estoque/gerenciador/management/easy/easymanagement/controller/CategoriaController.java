package com.estoque.gerenciador.management.easy.easymanagement.controller;

import com.estoque.gerenciador.management.easy.easymanagement.dto.categoria.CategoriaDto;
import com.estoque.gerenciador.management.easy.easymanagement.dto.categoria.CategoriaDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDtoRetorno> buscarPorId(@PathVariable Long id){
        CategoriaDtoRetorno categoria = categoriaService.buscarCategoriaId(id);
        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> desativarCategoria(@PathVariable Long id){
        categoriaService.desativarCategoria(id);
        return ResponseEntity.ok("Categoria desativada com Sucesso");
    }

}
