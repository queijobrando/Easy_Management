package com.estoque.gerenciador.management.easy.easymanagement.model;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String login;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private String senha;

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

}
