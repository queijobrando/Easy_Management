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
import com.estoque.gerenciador.management.easy.easymanagement.service.validator.MovimentacaoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private MovimentacaoValidator movimentacaoValidator;

    @Transactional
    public MovimentacaoDtoRetorno cadastrarMovimentacao(MovimentacaoDto dto){
        Produto produto = produtoRepository.findById(dto.produto_id())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado"));


        EstoqueLotes lote;
        if (dto.lote_id() != null) {
            lote = estoqueLotesRepository.findById(dto.lote_id()).orElse(null);
        } else {
            lote = null;
        }

        switch (dto.tipo_movimentacao()){
            case ENTRADA -> {
                movimentacaoValidator.validarEntrada(dto, produto, lote);

                if (lote != null){
                    if (lote.getProduto() != produto) {
                        throw new MovimentacaoInvalidaException("O produto informado não pertece ao lote informado");
                    }
                    lote.setQuantidade_lote(lote.getQuantidade_lote() + dto.quantidade());
                    estoqueLotesRepository.save(lote);

                    MovimentacaoEstoque movimentacao = movimentacaoMapper.toEntity(dto);
                    movimentacaoEstoqueRepository.save(movimentacao);

                    return movimentacaoMapper.toDto(movimentacao);
                } else {
                    EstoqueLotes estoqueLote = estoqueLoteMapper.toEntity(dto);
                    estoqueLotesRepository.save(estoqueLote);

                    MovimentacaoEstoque movimentacao = movimentacaoMapper.toEntity(dto, estoqueLote);
                    movimentacao.setId(null); // <-- ESSENCIAL!
                    movimentacaoEstoqueRepository.save(movimentacao);

                    return movimentacaoMapper.toDto(movimentacao);
                }
            }
            case SAIDA -> {
                movimentacaoValidator.validarSaida(dto, produto, lote);

                if (lote == null) {
                    throw new MovimentacaoInvalidaException("Lote não pode ser nulo na saída");
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
