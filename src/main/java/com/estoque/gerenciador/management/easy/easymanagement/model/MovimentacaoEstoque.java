package com.estoque.gerenciador.management.easy.easymanagement.model;

import com.estoque.gerenciador.management.easy.easymanagement.model.enuns.TipoMovimentacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacao_estoque")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class MovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lote_id", nullable = false)
    private EstoqueLotes lote;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimentacao", nullable = false)
    private TipoMovimentacao tipo_movimentacao;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @CreatedDate
    @Column(name = "data_movimentacao")
    private LocalDateTime data_movimentacao;

    @Column(name = "observacao", length = 100)
    private String observacao;
}
