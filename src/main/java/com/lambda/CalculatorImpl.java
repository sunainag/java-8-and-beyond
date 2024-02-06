package com.lambda;

interface Calculator{
//    void switchOn();
    void mul(int input);
}
public class CalculatorImpl{
//    @Override
//    public void switchOn() {
//        System.out.println("traditional approach");
//    }

    public static void main(String[] args) {
        //lambda expr syntax:
        //parameter     expr        method body
        //()            ->          {body}
        //1. No need of method name/prefix, as this is anynonymous fn
        //2. Assign it to interface
        Calculator calculator = (input)->System.out.println("Lambda expr with arg"+ input);
        calculator.mul(1234);
    }
}
