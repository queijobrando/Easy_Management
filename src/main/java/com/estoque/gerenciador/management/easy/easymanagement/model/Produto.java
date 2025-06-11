package com.estoque.gerenciador.management.easy.easymanagement.model;


import com.estoque.gerenciador.management.easy.easymanagement.model.enuns.Unidade;
import com.estoque.gerenciador.management.easy.easymanagement.service.CodigoBarrasUtil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "produtos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 80, nullable = false)
    private String nome;

    @Column(name = "descricao", length = 100, nullable = false)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categorias categoria;

    @Column(name = "preco", precision = 18, scale = 2)
    private BigDecimal preco; //Melhor para preços

    @Column(name = "codigo_de_barras", length = 20, nullable = false, unique = true)
    private String codigo_de_barras;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidade", nullable = false)
    private Unidade unidade;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @Column(name = "perecivel", nullable = false)
    private Boolean perecivel;

    @CreatedDate
    @Column(name = "data_cadastro")
    private LocalDateTime data_cadastro;

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime data_atualizacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @PrePersist
    public void gerarCamposAutomaticos(){
        this.ativo = true;
        this.codigo_de_barras = this.codigo_de_barras = CodigoBarrasUtil.gerarEAN13();
    }
}
