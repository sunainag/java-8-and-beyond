package com.lambda;

interface Calculator{
//    void switchOn();
    int sub(int a, int b);
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
        //3. no need to pass data type
        Calculator calculator = (i1, i2)->{
                                            return i2-i1;
                                        };
        System.out.println("Lambda expr with 2 args and return type: "+ calculator.sub(1, 4));
    }
}
