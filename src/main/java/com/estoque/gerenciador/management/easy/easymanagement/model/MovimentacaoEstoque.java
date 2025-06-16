package com.estoque.gerenciador.management.easy.easymanagement.model;

import com.estoque.gerenciador.management.easy.easymanagement.model.enuns.TipoMovimentacao;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacao_estoque")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class MovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lote_id")
    private EstoqueLotes lote;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimentacao", nullable = false)
    private TipoMovimentacao tipo_movimentacao;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @CreatedDate
    @Column(name = "data_movimentacao")
    private LocalDateTime data_movimentacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "observacao", length = 100)
    private String observacao;
}
