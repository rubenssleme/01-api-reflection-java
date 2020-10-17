package com.rubensleme;

public class Main {

    public static void main(String[] args) {
        Number object = new Integer(100);
        Class<? extends Number> c = object.getClass();
        System.out.println(c.getName());
//...
    }
}
