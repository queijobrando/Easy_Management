package com.estoque.gerenciador.management.easy.easymanagement.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "permissoes")
@Data
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;
}
