package com.estoque.gerenciador.management.easy.easymanagement.controller.views;

import com.estoque.gerenciador.management.easy.easymanagement.config.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("usuarios")
public class UsuarioViewController {

    @Autowired
    private SecurityService securityService;

    @GetMapping("/perfil")
    public String exibirPerfil(Model model){
        model.addAttribute("perfil", securityService.obterUsuarioLogado());
        return "usuarios/perfil";
    }

}
