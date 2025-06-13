package com.estoque.gerenciador.management.easy.easymanagement.config.security;

import com.estoque.gerenciador.management.easy.easymanagement.model.Grupo;
import com.estoque.gerenciador.management.easy.easymanagement.model.Permissao;
import com.estoque.gerenciador.management.easy.easymanagement.model.Usuario;
import com.estoque.gerenciador.management.easy.easymanagement.repository.GrupoRepository;
import com.estoque.gerenciador.management.easy.easymanagement.repository.PermissaoRepository;
import com.estoque.gerenciador.management.easy.easymanagement.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        if (usuarioRepository.findByLogin("admin").isEmpty()){
            List<Permissao> permissoes = new ArrayList<>();

            permissoes.add(buscarPermissao("PRODUTO_CADASTRAR"));
            permissoes.add(buscarPermissao("PRODUTO_BUSCAR"));
            permissoes.add(buscarPermissao("PRODUTO_DESATIVAR"));
            permissoes.add(buscarPermissao("PRODUTO_DELETAR"));
            permissoes.add(buscarPermissao("MOVIMENTACAO_CADASTRAR"));
            permissoes.add(buscarPermissao("MOVIMENTACAO_BUSCAR"));
            permissoes.add(buscarPermissao("MOVIMENTACAO_DELETAR"));
            permissoes.add(buscarPermissao("ESTOQUE_BUSCAR"));
            permissoes.add(buscarPermissao("CATEGORIA_BUSCAR"));
            permissoes.add(buscarPermissao("CATEGORIA_CADASTRAR"));
            permissoes.add(buscarPermissao("CATEGORIA_DESATIVAR"));
            permissoes.add(buscarPermissao("ADMIN"));

            Grupo grupoAdmin = criarGrupo("ADMIN", permissoes);

            Usuario admin = new Usuario();
            admin.setLogin("admin");
            admin.setNome("Admin");
            admin.setEmail("admin@sistema.com");
            admin.setSenha(passwordEncoder.encode("admin123"));
            admin.setGrupos(List.of(grupoAdmin));
            usuarioRepository.save(admin);
        }
    }


    private Permissao buscarPermissao(String nome){
        return permissaoRepository.findByNome(nome).orElseGet(() -> {
            Permissao p = new Permissao();
            p.setNome(nome);
            return permissaoRepository.save(p);
        });
    }

    private Grupo criarGrupo(String nome, List<Permissao> permissoes){
        return grupoRepository.findByNome(nome).orElseGet(() ->{
            Grupo g = new Grupo();
            g.setNome(nome);
            g.setPermissoes(permissoes);
            return grupoRepository.save(g);
        });
    }

}
