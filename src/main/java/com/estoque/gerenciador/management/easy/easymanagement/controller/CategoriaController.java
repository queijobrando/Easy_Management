package com.estoque.gerenciador.management.easy.easymanagement.controller;

import com.estoque.gerenciador.management.easy.easymanagement.dto.categoria.CategoriaDto;
import com.estoque.gerenciador.management.easy.easymanagement.dto.categoria.CategoriaDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/categorias")
public class CategoriaController implements GenericController {

    @Autowired
    private CategoriaService categoriaService;


    @PostMapping
    @PreAuthorize("hasAuthority('CATEGORIA_CADASTRAR')")
    public ResponseEntity<CategoriaDtoRetorno> cadastrarCategoria(@RequestBody @Valid CategoriaDto dto){
        CategoriaDtoRetorno categoria = categoriaService.cadastrarCategoria(dto);

        URI location = gerarHeaderLocation(categoria.id());

        return ResponseEntity.created(location).body(categoria);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CATEGORIA_BUSCAR')")
    public ResponseEntity<CategoriaDtoRetorno> buscarPorId(@PathVariable Long id){
        CategoriaDtoRetorno categoria = categoriaService.buscarCategoriaId(id);
        return ResponseEntity.ok(categoria);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('CATEGORIA_BUSCAR')")
    public ResponseEntity<List<CategoriaDtoRetorno>> buscarPorExemplo(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "descricao", required = false) String descricao,
            @RequestParam(value = "ativo", required = false) Boolean ativo
    ){
        List<CategoriaDtoRetorno> lista = categoriaService.pesquisarPorExample(nome, descricao, ativo);
        return ResponseEntity.ok(lista);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CATEGORIA_DESATIVAR')")
    public ResponseEntity<String> desativarCategoria(@PathVariable Long id){
        categoriaService.desativarCategoria(id);
        return ResponseEntity.ok("Categoria desativada com Sucesso");
    }

}
