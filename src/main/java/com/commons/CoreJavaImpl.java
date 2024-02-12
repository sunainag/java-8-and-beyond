package com.commons;

import java.util.Objects;

public class CoreJavaImpl {
    public static void main(String[] args) {
        //Strings
        System.out.println("abc".contentEquals("ABC"));     //false
        System.out.println("abc".contentEquals("abc"));     //true
        System.out.println(Objects.equals("abc","abc"));  //true

        System.out.println("\nComparing obj and literal: ");
        String one = new String("one");
        String one_literal = "one";
        String one_new = new String("one");
        String one_other_literal = "one";
        System.out.println(one.equals(one_literal));            //true
        System.out.println(one.equals(one_new));                //true
        System.out.println(Objects.equals(one, one_literal));   //true
        System.out.println(Objects.equals(one, one_new));       //true
        System.out.println(one==one_literal);                   //false
        System.out.println(one==one_new);                       //false
        System.out.println(one_other_literal==one_new);         //false

        CoreJavaImpl obj = new CoreJavaImpl();
        System.out.println(obj.sum(1,2));
        System.out.println(obj.sum(1.0,2.0));
        System.out.println(obj.sum('A'));
        foo();
        foo(1);
        main("overloaded main method");
    }

    // Overloaded main methods
    public static void main(String arg1) {
        System.out.println("Hi, " + arg1);
        foo();
    }

    //Polymorphism - Overloading
    //Possible for static and non-static methods
    int sum(int x){
        System.out.println("int sum single arg");
        return x;
    }
    int sum(int x, int y){
        System.out.println("int sum");
        return x+y;
    }

    int sum(Integer x, int y){
        System.out.println("wrapper int sum");
        return x+y;
    }

    //Polymorphism - Overloading
    double sum(double x, double y){
        System.out.println("double sum");
        return x+y;
    }

    static void foo() {
        System.out.println("Test.foo() called ");
    }
    static void foo(int a) {
        System.out.println("Test.foo(int) called ");
    }
}
