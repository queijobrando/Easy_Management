package com.estoque.gerenciador.management.easy.easymanagement.model;

import com.estoque.gerenciador.management.easy.easymanagement.service.CodigoBarrasUtil;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "estoque_lotes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class EstoqueLotes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(name = "quantidade_lote", nullable = false)
    private Integer quantidade_lote;

    @Column(name = "validade")
    private LocalDate validade;

    @Column(name = "codigo_de_barras", length = 20, nullable = false, unique = true)
    private String codigo_de_barras;

    @CreatedDate
    @Column(name = "data_cadastro")
    private LocalDate data_cadastro;

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime data_atualizacao;

    @PrePersist
    public void gerarCamposAutomaticos(){
        this.codigo_de_barras = this.codigo_de_barras = CodigoBarrasUtil.gerarEAN13();
    }
}
