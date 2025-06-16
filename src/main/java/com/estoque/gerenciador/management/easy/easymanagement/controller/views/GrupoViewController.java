package com.estoque.gerenciador.management.easy.easymanagement.controller.views;

import com.estoque.gerenciador.management.easy.easymanagement.dto.grupo.GrupoDto;
import com.estoque.gerenciador.management.easy.easymanagement.dto.produto.ProdutoDto;
import com.estoque.gerenciador.management.easy.easymanagement.exceptions.DesativarCategoriaException;
import com.estoque.gerenciador.management.easy.easymanagement.exceptions.RegistroDuplicadoException;
import com.estoque.gerenciador.management.easy.easymanagement.model.Grupo;
import com.estoque.gerenciador.management.easy.easymanagement.service.GrupoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("grupos")
@PreAuthorize("hasAuthority('ADMIN')")
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
        model.addAttribute("permissoesAgrupadas", grupoService.buscarPermissoesAgrupadas());

        return "grupos/cadastrar";
    }

    @PostMapping("/cadastrar")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String cadastrarProduto(@Valid @ModelAttribute("grupoDto") GrupoDto grupoDto,
                                   BindingResult result,
                                   Model model) {
        if (result.hasErrors()) {
            model.addAttribute("permissoesAgrupadas", grupoService.buscarPermissoesAgrupadas());
            return "grupos/cadastrar";
        }

        try {
            grupoService.salvar(grupoDto);
            return "redirect:/grupos/buscar";
        } catch (RegistroDuplicadoException e) {
            model.addAttribute("erroMensagem", e.getMessage());
            model.addAttribute("permissoesAgrupadas", grupoService.buscarPermissoesAgrupadas());
        }

        return "grupos/cadastrar";
    }
}
