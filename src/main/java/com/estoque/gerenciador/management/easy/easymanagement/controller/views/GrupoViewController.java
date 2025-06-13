package com.estoque.gerenciador.management.easy.easymanagement.controller.views;

import com.estoque.gerenciador.management.easy.easymanagement.dto.grupo.GrupoDto;
import com.estoque.gerenciador.management.easy.easymanagement.model.Grupo;
import com.estoque.gerenciador.management.easy.easymanagement.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("grupos")
public class GrupoViewController {

    @Autowired
    private GrupoService grupoService;

    @GetMapping("/buscar")
    public String buscarGrupos(Model model){
        List<Grupo> grupos = grupoService.buscarTodos();
        model.addAttribute("grupos", grupos);

        return "grupos/buscar";
    }

    @GetMapping("/cadastrar")
    public String cadastrarGrupo(Model model){
        model.addAttribute("grupoDto", new GrupoDto());
        model.addAttribute("permissoes", grupoService.buscarTodasPermissoes());

        return "grupos/cadastrar";
    }
}
