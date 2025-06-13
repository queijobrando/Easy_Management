package com.estoque.gerenciador.management.easy.easymanagement.controller;

import com.estoque.gerenciador.management.easy.easymanagement.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/grupos")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @DeleteMapping("/deletar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseBody
    public ResponseEntity<String> deletarGrupo(@PathVariable Long id){
        grupoService.deletarGrupo(id);
        return ResponseEntity.ok("Grupo deletado com Sucesso");
    }

}
