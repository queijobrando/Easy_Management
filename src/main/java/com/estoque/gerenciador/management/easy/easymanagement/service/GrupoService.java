package com.estoque.gerenciador.management.easy.easymanagement.service;

import com.estoque.gerenciador.management.easy.easymanagement.dto.grupo.GrupoDto;
import com.estoque.gerenciador.management.easy.easymanagement.exceptions.EntidadeNaoEncontradaException;
import com.estoque.gerenciador.management.easy.easymanagement.exceptions.RegistroDuplicadoException;
import com.estoque.gerenciador.management.easy.easymanagement.mapper.GrupoMapper;
import com.estoque.gerenciador.management.easy.easymanagement.model.Grupo;
import com.estoque.gerenciador.management.easy.easymanagement.model.Permissao;
import com.estoque.gerenciador.management.easy.easymanagement.repository.GrupoRepository;
import com.estoque.gerenciador.management.easy.easymanagement.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

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
        if (grupoRepository.findByNomeIgnoreCase(grupoDto.getNome()).isPresent()){
            throw new  RegistroDuplicadoException("Já existe um grupo com esse nome.");
        }


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

    public Map<String, List<Permissao>> buscarPermissoesAgrupadas() {
        List<Permissao> todas = permissaoRepository.findAll();

        return todas.stream()
                .filter(p -> !p.getNome().equalsIgnoreCase("ADMIN"))
                .collect(Collectors.groupingBy(Permissao::getArea, TreeMap::new, Collectors.toList()));
    }

    @Transactional
    public void deletarGrupo(Long id){
        Grupo grupo = grupoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Grupo não encontrado"));

        grupoRepository.deleteById(id);
    }


}
