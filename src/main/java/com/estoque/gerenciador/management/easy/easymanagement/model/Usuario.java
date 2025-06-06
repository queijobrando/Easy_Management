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
    public Long id;

    @Column
    public String login;

    @Column
    public String senha;

    @Type(ListArrayType.class)
    @Column(name = "roles", columnDefinition = "varchar[]")
    public List<String> roles;

}
