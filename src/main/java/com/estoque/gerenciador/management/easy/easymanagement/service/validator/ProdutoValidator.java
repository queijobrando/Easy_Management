package com.estoque.gerenciador.management.easy.easymanagement.service.validator;

import com.estoque.gerenciador.management.easy.easymanagement.exceptions.DesativarCategoriaException;
import com.estoque.gerenciador.management.easy.easymanagement.exceptions.RegistroDuplicadoException;
import com.estoque.gerenciador.management.easy.easymanagement.model.Produto;
import com.estoque.gerenciador.management.easy.easymanagement.repository.CategoriaRepository;
import com.estoque.gerenciador.management.easy.easymanagement.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProdutoValidator {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public void validar(Produto produto){
        if (!validarCategoriaAtiva(produto.getCategoria().getId())){
            throw new DesativarCategoriaException("A categoria informada está desativada.");
        }


        if (existeProdutoCadastrado(produto)){
            throw new RegistroDuplicadoException("Produto com este nome e descrição já cadastrado!");
        }

    }

    private boolean validarCategoriaAtiva(Long id) {
        return categoriaRepository.findByIdAndAtivoTrue(id).isPresent();
    }

    private boolean existeProdutoCadastrado(Produto produto){
        Optional<Produto> produtoEncontrado = produtoRepository.findByNomeAndDescricao(
                produto.getNome(), produto.getDescricao()
        );

        if (produto.getId() == null){
            return produtoEncontrado.isPresent(); //false or true
        }

        return !produto.getId().equals(produtoEncontrado.get().getId()) && produtoEncontrado.isPresent();

    }
}
