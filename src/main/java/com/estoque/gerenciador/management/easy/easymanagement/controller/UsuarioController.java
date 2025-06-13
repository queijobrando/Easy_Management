package com.estoque.gerenciador.management.easy.easymanagement.controller;

import com.estoque.gerenciador.management.easy.easymanagement.dto.usuario.UsuarioDto;
import com.estoque.gerenciador.management.easy.easymanagement.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid UsuarioDto dto){
        usuarioService.salvar(dto);
    }

    @DeleteMapping("/desativar/{id}")
    @PreAuthorize("hasAuthority('PRODUTO_DESATIVAR')")
    @ResponseBody
    public ResponseEntity<String> desativarUsuario(@PathVariable Long id){
        usuarioService.desativarUsuario(id);
        return ResponseEntity.ok("Usuario desativado com Sucesso");
    }

    @DeleteMapping("/deletar/{id}")
    @PreAuthorize("hasAuthority('PRODUTO_DELETAR')")
    @ResponseBody
    public ResponseEntity<String> deletarUsuario(@PathVariable Long id){
        usuarioService.deletarUsuario(id);
        return ResponseEntity.ok("Usuario deletado com Sucesso");
    }
}
