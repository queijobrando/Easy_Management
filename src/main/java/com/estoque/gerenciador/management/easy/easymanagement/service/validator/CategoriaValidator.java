package com.estoque.gerenciador.management.easy.easymanagement.service.validator;

import com.estoque.gerenciador.management.easy.easymanagement.exceptions.DesativarCategoriaException;
import com.estoque.gerenciador.management.easy.easymanagement.exceptions.RegistroDuplicadoException;
import com.estoque.gerenciador.management.easy.easymanagement.model.Categorias;
import com.estoque.gerenciador.management.easy.easymanagement.model.Produto;
import com.estoque.gerenciador.management.easy.easymanagement.repository.CategoriaRepository;
import com.estoque.gerenciador.management.easy.easymanagement.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoriaValidator {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public void validarDuplicidade(Categorias categorias){
        if (existeCategoriaCadastrada(categorias)){
            throw new RegistroDuplicadoException("Categoria com este nome e descrição já cadastrado!");
        }
    }

    public void produtoAtivoNaCategoria(Categorias categorias){
        if (produtoRepository.existsByCategoriaAndAtivoTrue(categorias)){
            throw new DesativarCategoriaException("Há produtos ativos nesta categoria. Desative-os antes de desativar a categoria.");
        }
    }

    private boolean existeCategoriaCadastrada(Categorias categorias){
        Optional<Categorias> produtoEncontrado = categoriaRepository.findByNomeAndDescricao(
                categorias.getNome(), categorias.getDescricao()
        );

        if (categorias.getId() == null){
            return produtoEncontrado.isPresent(); //false or true
        }

        return !categorias.getId().equals(produtoEncontrado.get().getId()) && produtoEncontrado.isPresent();

    }


}
