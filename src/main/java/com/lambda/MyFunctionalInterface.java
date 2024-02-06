package com.lambda;


//not required to add the annotation; if only one abstract method-> FI
@FunctionalInterface
public interface MyFunctionalInterface {

    void m1();

    default void m2(){
        System.out.println("default method m2");
    }

    default void m3(){
        System.out.println("default method m3");
    }

    static void m4(){
        System.out.println("static method m4");
    }
}
