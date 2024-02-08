package com.functionalInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ConsumerImpl{

    public static void main(String[] args) {
        //Consumer andThen

        Consumer<List<Integer>> modify = list -> {
            for(int i=0;i<list.size();i++){
                list.set(i, list.get(i)*2);
            }
        };
        Consumer<List<Integer>> display = list->list.stream().forEach(t->System.out.print(t+" "));

        List<Integer> list = Arrays.asList(1,2,3,4);
        testConsumer(list, modify, display);
    }

    private static void testConsumer(List<Integer> list, Consumer<List<Integer>> modify, Consumer<List<Integer>> display) {
        modify.andThen(display).accept(list); //output: 2 4 6 8

        try {
            modify.andThen(null).accept(list);
        }
        catch (Exception e) {
            System.out.println("\nException: " + e);
        }

    }
}