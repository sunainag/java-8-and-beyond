package com.lambda;

interface Calculator{
    void switchOn();
}
public class CalculatorImpl implements Calculator{
    @Override
    public void switchOn() {
        System.out.println("traditional approach");
    }

    public static void main(String[] args) {
        Calculator calculator = new CalculatorImpl();
        calculator.switchOn();
    }
}
