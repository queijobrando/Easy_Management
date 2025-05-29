package com.estoque.gerenciador.management.easy.easymanagement.model;

import com.estoque.gerenciador.management.easy.easymanagement.service.CodigoBarrasUtil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "categorias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Categorias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 80, nullable = false, unique = true)
    private String nome;

    @Column(name = "descricao", length = 20, nullable = false)
    private String descricao;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @OneToMany(mappedBy = "categoria")
    private List<Produto> produtos;

    @CreatedDate
    @Column(name = "data_cadastro")
    private LocalDateTime data_cadastro;

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime data_atualizacao;

    @PrePersist
    public void gerarCamposAutomaticos(){
        this.ativo = true;
    }
}
