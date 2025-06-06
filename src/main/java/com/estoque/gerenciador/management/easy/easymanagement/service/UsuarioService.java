package com.estoque.gerenciador.management.easy.easymanagement.service;

import com.estoque.gerenciador.management.easy.easymanagement.dto.usuario.UsuarioDto;
import com.estoque.gerenciador.management.easy.easymanagement.mapper.UsuarioMapper;
import com.estoque.gerenciador.management.easy.easymanagement.model.Usuario;
import com.estoque.gerenciador.management.easy.easymanagement.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public void salvar(UsuarioDto dto){
        Usuario usuario = usuarioMapper.toEntity(dto);
        var senha = usuario.getSenha();
        usuario.setSenha(passwordEncoder.encode(senha));
        usuarioRepository.save(usuario);
    }

    public Usuario obterPorLogin(String login){
        return usuarioRepository.findByLogin(login);
    }
}
