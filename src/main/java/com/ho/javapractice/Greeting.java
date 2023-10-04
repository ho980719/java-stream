package com.ho.javapractice;

public class Greeting {
    private String name;
    public Greeting() {
    }

    public  Greeting(String name) {
        this.name = name;
    }

    public static String hello(String name) {
        return "hello " + name;
    }

    public String hi(String name) {
        return "hi " + name;
    }

}
