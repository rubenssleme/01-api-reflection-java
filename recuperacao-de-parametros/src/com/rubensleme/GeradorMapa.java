package com.rubensleme;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class GeradorMapa {
//    As duas listagens a seguir mostram como as anotações poderiam ser criadas,
//    sendo a primeira para ignorar uma propriedade e a segunda para definir um
//    nome diferente para uma propriedade.

//    Listagem 1.6 - Anotação que define quando uma propriedade precisa ser ignorada:
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Ignorar {
    }
//    Listagem 1.7 - Anotação que define um nome diferente para a propriedade:
    @Retention(RetentionPolicy.RUNTIME)
    public @interface NomePropriedade{
        String value();
    }

    public static Map<String, Object> gerarMapa(Object o) {
        Class<?> classe = o.getClass();
        Map<String, Object> mapa = new HashMap<>();
        for (Method m : classe.getMethods()) {
            try {
                if (isGetter(m)) {
                    String propriedade = null;

                    if(m.isAnnotationPresent(NomePropriedade.class)){
                        propriedade=
                                m.getAnnotation(NomePropriedade.class).value();
                    }else{
                        propriedade = deGetterParaPropriedade(m.getName());
                    }
                    Object valor = m.invoke(o);
                    mapa.put(propriedade, valor);

                    }

                } catch (Exception e) {
                throw new RuntimeException(
                        "Problema ao gerar o mapa", e);
            }
        }
        return mapa;
    }
    private static boolean isGetter(Method m) {
        return m.getName().startsWith("get") &&
                m.getReturnType() != void.class &&
                m.getParameterTypes().length == 0 &&
                !m.isAnnotationPresent(Ignorar.class);
    }

    private static String deGetterParaPropriedade(String nomeGetter){
        StringBuffer retorno = new StringBuffer();
        retorno.append(nomeGetter.substring(3, 4).toLowerCase());
        retorno.append(nomeGetter.substring(4));
        return retorno.toString();
    }


}
