package com.estoque.gerenciador.management.easy.easymanagement.service;

import com.estoque.gerenciador.management.easy.easymanagement.dto.usuario.UsuarioDto;
import com.estoque.gerenciador.management.easy.easymanagement.dto.usuario.UsuarioDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.exceptions.RegistroDuplicadoException;
import com.estoque.gerenciador.management.easy.easymanagement.mapper.UsuarioMapper;
import com.estoque.gerenciador.management.easy.easymanagement.model.Usuario;
import com.estoque.gerenciador.management.easy.easymanagement.repository.GrupoRepository;
import com.estoque.gerenciador.management.easy.easymanagement.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public void salvar(UsuarioDto dto){
        if (usuarioRepository.findByLogin(dto.getLogin()).isPresent()){
            throw new RegistroDuplicadoException("Já existe um usuario com esse nome.");
        }

        Usuario usuario = usuarioMapper.toEntity(dto);
        var senha = usuario.getSenha();
        usuario.setSenha(passwordEncoder.encode(senha));

        var grupo = grupoRepository.findById(dto.getGrupo())
                .orElseThrow(() -> new IllegalArgumentException("Grupo informado é inválido"));

        usuario.setGrupo(grupo);

        usuarioRepository.save(usuario);
    }

    public Usuario obterPorLogin(String login){
        return usuarioRepository.findByLogin(login).orElse(null);
    }

    public List<UsuarioDtoRetorno> buscarTodos(){
        return usuarioRepository.findAll().stream().map(usuarioMapper::toDto).toList();
    }
}
