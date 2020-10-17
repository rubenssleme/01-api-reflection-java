package com.rubensleme;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            FornecedorImplementacoes f =
                    new FornecedorImplementacoes("implementacoes.prop");
            Class<?> impl = f.getImplementacao(DAO.class);
            System.out.println("Implementação recuperada: " + impl.getName());
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(
                    "Problemas ao obter implementacões:" + e.getMessage());
        }
    }
}
