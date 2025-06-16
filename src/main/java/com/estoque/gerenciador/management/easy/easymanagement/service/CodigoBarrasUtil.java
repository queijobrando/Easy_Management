package com.estoque.gerenciador.management.easy.easymanagement.service;

public class CodigoBarrasUtil {

    public static String gerarEAN13() {
        // Gera número aleatório de 12 dígitos
        String base = String.format("%012d", (long)(Math.random() * 1_000_000_000_000L));
        return base + calcularDigitoVerificador(base);
    }

    private static int calcularDigitoVerificador(String codigo12Digitos) {
        int soma = 0;
        for (int i = 0; i < 12; i++) {
            int digito = Character.getNumericValue(codigo12Digitos.charAt(i));
            soma += (i % 2 == 0) ? digito : digito * 3;
        }
        int resto = soma % 10;
        return (resto == 0) ? 0 : (10 - resto);
    }
}

