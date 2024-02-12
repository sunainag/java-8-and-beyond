package com.commons;

import com.functionalInterface.CheckedExceptionConsumerHandler;

import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ExceptionHandlingImplWithStreams {

    public static void main(String[] args) {

        //Unchecked exception handling
        List<String> listWithStrings = Arrays.asList("1","2","zx");
        
        listWithStrings.forEach(handleExceptionInString(s -> System.out.println(Integer.parseInt(s))));

        List<Integer> listWithNumbers = Arrays.asList(0,2);
        listWithNumbers.forEach(handleGenericException(num -> System.out.println(10/num), ArithmeticException.class));

        listWithStrings.forEach(handleGenericException(s -> System.out.println(Integer.parseInt(s)), NumberFormatException.class));

        //Checked exception handling
        //Not clean code if we add try-catch here
        List<Integer> list2 = Arrays.asList(10,20);
        list2.forEach(i->{
            try {
                Thread.sleep(i);
                System.out.println(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //Rather, let's create Custom exception class and call again
        list2.forEach(handleCheckedExceptionConsumer(Thread::sleep));

    }

    private static <Target> Consumer<Target> handleCheckedExceptionConsumer(CheckedExceptionConsumerHandler<Target,Exception> handlerConsumer) {
        return obj->{
            try {
                handlerConsumer.accept(obj);
                System.out.println(obj);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    private static <Target, ExObj extends Exception> Consumer<Target> handleGenericException(Consumer<Target> targetConsumer,
                                                                                             Class<ExObj> exObjClass) {

        return obj->{
            try{
                targetConsumer.accept(obj);
            }catch(Exception e){
                try{
                    ExObj exObj = exObjClass.cast(e);
                    System.out.println("ex handled for "+ exObj.getMessage());
                }catch (ClassCastException ecx){
                    throw ecx;
                }
            }
        };
    }

    private static Consumer<String> handleExceptionInString(Consumer<String> targetConsumer) {
        return obj->{
            try{
                targetConsumer.accept(obj);
            }catch(NumberFormatException e){
                System.out.println("ex handled for "+   e.getMessage());
            }
        };
    }
}
