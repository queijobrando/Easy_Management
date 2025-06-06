package com.estoque.gerenciador.management.easy.easymanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // paginas web
public class ViewController {

    @GetMapping("/login")
    public String paginaLogin(){
        return "login";
    }

    @GetMapping({"/", "/home"})
    public String home() {
        return "home";
    }

}
