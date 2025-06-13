package com.estoque.gerenciador.management.easy.easymanagement.config.security;

import com.estoque.gerenciador.management.easy.easymanagement.model.Usuario;
import com.estoque.gerenciador.management.easy.easymanagement.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.obterPorLogin(login);

        if (usuario == null){
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }

        // Permissões via grupos
        Set<GrantedAuthority> authorities = usuario.getGrupo().getPermissoes().stream()
                .map(permissao -> new SimpleGrantedAuthority(permissao.getNome()))
                .collect(Collectors.toSet());


        return User.builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .authorities(authorities)
                .build();
    }
}
