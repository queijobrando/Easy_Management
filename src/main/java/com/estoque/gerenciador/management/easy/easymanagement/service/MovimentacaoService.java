package com.estoque.gerenciador.management.easy.easymanagement.service;

import com.estoque.gerenciador.management.easy.easymanagement.dto.movimentacao.MovimentacaoDto;
import com.estoque.gerenciador.management.easy.easymanagement.dto.movimentacao.MovimentacaoDtoRetorno;
import com.estoque.gerenciador.management.easy.easymanagement.exceptions.EntidadeNaoEncontradaException;
import com.estoque.gerenciador.management.easy.easymanagement.exceptions.MovimentacaoInvalidaException;
import com.estoque.gerenciador.management.easy.easymanagement.mapper.EstoqueLoteMapper;
import com.estoque.gerenciador.management.easy.easymanagement.mapper.MovimentacaoMapper;
import com.estoque.gerenciador.management.easy.easymanagement.model.EstoqueLotes;
import com.estoque.gerenciador.management.easy.easymanagement.model.MovimentacaoEstoque;
import com.estoque.gerenciador.management.easy.easymanagement.model.Produto;
import com.estoque.gerenciador.management.easy.easymanagement.repository.EstoqueLotesRepository;
import com.estoque.gerenciador.management.easy.easymanagement.repository.MovimentacaoEstoqueRepository;
import com.estoque.gerenciador.management.easy.easymanagement.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    @Autowired
    private EstoqueLotesRepository estoqueLotesRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MovimentacaoMapper movimentacaoMapper;

    @Autowired
    private EstoqueLoteMapper estoqueLoteMapper;

    @Transactional
    public MovimentacaoDtoRetorno cadastrarMovimentacao(MovimentacaoDto dto){
        Produto produto = produtoRepository.findById(dto.produto_id())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado"));

        System.out.println("Passou 1 verificacao");

        EstoqueLotes lote;
        if (dto.lote_id() != null) {
            lote = estoqueLotesRepository.findById(dto.lote_id()).orElse(null);
        } else {
            System.out.println("Lote ta nulo");
            lote = null;
        }


        switch (dto.tipoMovimentacao()){
            case ENTRADA -> {
                if (produto.getPerecivel() && dto.validade() == null){
                    throw new MovimentacaoInvalidaException("Campo validade obrigatório quando produto é perecível");
                } else if (lote != null && !Objects.equals(lote.getValidade(), dto.validade())) {
                    throw new MovimentacaoInvalidaException("A validade dos produtos da movimentação de ENTRADA não pode ser diferente da validade do lote");
                }

                if (lote != null){
                    if (lote.getProduto() != produto) {
                        throw new MovimentacaoInvalidaException("O produto informado não pertece ao lote informado");
                    }
                    System.out.println("Erro aqui?");
                    lote.setQuantidade_lote(lote.getQuantidade_lote() + dto.quantidade());
                    estoqueLotesRepository.save(lote);

                    MovimentacaoEstoque movimentacao = movimentacaoMapper.toEntity(dto);
                    movimentacaoEstoqueRepository.save(movimentacao);

                    return movimentacaoMapper.toDto(movimentacao);
                } else {
                    EstoqueLotes estoqueLote = estoqueLoteMapper.toEntity(dto);
                    estoqueLotesRepository.save(estoqueLote);
                    System.out.println("E agora");
                    MovimentacaoEstoque movimentacao = movimentacaoMapper.toEntity(dto, estoqueLote);
                    movimentacao.setId(null); // <-- ESSENCIAL!
                    movimentacaoEstoqueRepository.save(movimentacao);

                    System.out.println("e agora einn");
                    return movimentacaoMapper.toDto(movimentacao);
                }
            }
            case SAIDA -> {
                if (lote == null) {
                    throw new MovimentacaoInvalidaException("É necessário informar um lote para movimentações de SAIDA");
                } else if (lote.getProduto() != produto) {
                    throw new MovimentacaoInvalidaException("O produto informado não pertece ao lote informado");
                } else if (produto.getPerecivel() && lote.getValidade() != null && lote.getValidade().isBefore(LocalDate.now())) {
                    throw new MovimentacaoInvalidaException("A validade do lote já venceu");
                } else if (dto.quantidade() > lote.getQuantidade_lote()) {
                    throw new MovimentacaoInvalidaException("Quantidade de SAIDA maior que a do lote");
                }

                lote.setQuantidade_lote(lote.getQuantidade_lote() - dto.quantidade());
                estoqueLotesRepository.save(lote);

                MovimentacaoEstoque movimentacao = movimentacaoMapper.toEntity(dto);
                movimentacaoEstoqueRepository.save(movimentacao);

                return movimentacaoMapper.toDto(movimentacao);

            }
            default -> throw new MovimentacaoInvalidaException("Tipo de movimentação inválida ou inexistente");
        }

    }

}
