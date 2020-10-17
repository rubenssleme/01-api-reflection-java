package com.rubensleme;

import java.util.Map;

public class Main {
    //Código que executa o método de geração do mapa e imprime
    public static void main(String[] args) {
        Produto p = new Produto("Design Patterns","LIVRO",59.90,
                "Publicado pela Casa do Codigo");
        Map<String,Object> props = GeradorMapa.gerarMapa(p);
        for(String prop : props.keySet()){
            System.out.println(prop+" = "+props.get(prop));
        }
    }
}
