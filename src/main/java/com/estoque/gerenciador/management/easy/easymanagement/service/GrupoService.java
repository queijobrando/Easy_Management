package com.estoque.gerenciador.management.easy.easymanagement.service;

import com.estoque.gerenciador.management.easy.easymanagement.dto.grupo.GrupoDto;
import com.estoque.gerenciador.management.easy.easymanagement.mapper.GrupoMapper;
import com.estoque.gerenciador.management.easy.easymanagement.model.Grupo;
import com.estoque.gerenciador.management.easy.easymanagement.model.Permissao;
import com.estoque.gerenciador.management.easy.easymanagement.repository.GrupoRepository;
import com.estoque.gerenciador.management.easy.easymanagement.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private GrupoMapper grupoMapper;

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Transactional
    public void salvar(GrupoDto grupoDto){
        Grupo grupo = grupoMapper.toEntity(grupoDto);

        var permissoes = permissaoRepository.findAllById(grupoDto.getPermissoes());

        if (grupoDto.getPermissoes().size() != permissoes.size()){
            throw new IllegalArgumentException("Um ou mais permissões informadas são inválidas.");
        }

        grupo.setPermissoes(permissoes);
        grupoRepository.save(grupo);

    }

    public List<Grupo> buscarTodos(){
        return grupoRepository.findAll().stream().toList();
    }

    public List<Permissao> buscarTodasPermissoes(){
        return permissaoRepository.findAll().stream().toList();
    }

}
