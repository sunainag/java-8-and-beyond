package com.functionalInterface;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class JavaFunctionalInterfaceImpl {

    public static void main(String[] args) {
        Consumer<Integer> consumer = (t)-> System.out.println("Printing consumer output: "+t);
        consumer.accept(1);

        Predicate<Integer> predicate = t -> t%2==0;
        predicate.test(2);

        Supplier<String> supplier = ()-> "Welcome to supplier code";
        supplier.get();

        System.out.println("\nTesting streams:");
        //Use in streams
        List<Integer> list  = Arrays.asList(1,2,3,4);
        list.stream().filter(predicate).forEach(consumer);

        System.out.println();
        list.stream().filter(t-> t%3==0).forEach(System.out::println);

        List<String> list1 = Arrays.asList();
        System.out.println(list1.stream().findAny().orElseGet(()->"Default supplier get method"));

        list1.add(null);
        System.out.println(list1.stream().findAny().orElseThrow(NoSuchElementException::new));
    }
}
