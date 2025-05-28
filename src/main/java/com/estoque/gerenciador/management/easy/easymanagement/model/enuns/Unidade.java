package com.estoque.gerenciador.management.easy.easymanagement.model.enuns;

public enum Unidade {

    // Massa
    KG("Quilograma"),
    G("Grama"),
    MG("Miligrama"),

    // Volume
    L("Litro"),
    ML("Mililitro"),
    M3("Metro cúbico"),

    // Comprimento
    M("Metro"),
    CM("Centímetro"),
    MM("Milímetro"),

    // Quantidade
    UN("Unidade"),
    DZ("Dúzia"),
    CX("Caixa"),
    PCT("Pacote"),
    RL("Rolo"),

    // Outros
    M2("Metro quadrado");

    private final String descricao;

    Unidade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

