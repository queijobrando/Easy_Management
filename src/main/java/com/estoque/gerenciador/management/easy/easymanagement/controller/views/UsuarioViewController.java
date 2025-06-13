package com.estoque.gerenciador.management.easy.easymanagement.controller.views;

import com.estoque.gerenciador.management.easy.easymanagement.config.security.SecurityService;
import com.estoque.gerenciador.management.easy.easymanagement.dto.usuario.UsuarioDto;
import com.estoque.gerenciador.management.easy.easymanagement.exceptions.RegistroDuplicadoException;
import com.estoque.gerenciador.management.easy.easymanagement.service.GrupoService;
import com.estoque.gerenciador.management.easy.easymanagement.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("usuarios")
public class UsuarioViewController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/perfil")
    public String exibirPerfil(Model model){
        model.addAttribute("perfil", securityService.obterUsuarioLogado());
        return "usuarios/perfil";
    }

    @GetMapping("/buscar")
    public String buscarUsuarios(Model model){
        model.addAttribute("usuarios", usuarioService.buscarTodos());

        return "usuarios/buscar";
    }

    @GetMapping("/cadastrar")
    public String exibirFormularioCadastro(Model model){
        model.addAttribute("usuarioDto", new UsuarioDto());
        model.addAttribute("grupos", grupoService.buscarTodos());

        return "usuarios/cadastrar";
    }

    @PostMapping("/cadastrar")
    public String cadastrarUsuario(@Valid @ModelAttribute("usuarioDto") UsuarioDto usuarioDto,
                                   BindingResult result,
                                   Model model){
        if (result.hasErrors()) {
            model.addAttribute("grupos", grupoService.buscarTodos());
            return "usuarios/cadastrar";
        }

        try {
            usuarioService.salvar(usuarioDto);
            return "redirect:/usuarios/buscar";
        } catch (RegistroDuplicadoException | IllegalArgumentException e){
            model.addAttribute("erroMensagem", e.getMessage());
            model.addAttribute("grupos", grupoService.buscarTodos());
        }

        return "usuarios/cadastrar";

    }

}
