package com.streams;

import java.util.*;

public class StreamsDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList(){{
                                                add("one");
                                                add("two");
                                                add("three");
                                                add("four");
                                                add("five");
                                            }};
        list.stream().forEach(System.out::println);
        System.out.println();

        Map<Integer, String> map = new HashMap(){{
                                                    put(1,"one");
                                                    put(2,"two");
                                                    put(3,"three");
                                                    put(4,"four");
                                                }};
        map.forEach((key, value)-> System.out.println(key+":"+value));
        System.out.println();

        map.entrySet().stream().forEach((entry)->System.out.println(entry.getKey()+":"+entry.getValue()));

        System.out.println("\nUsing filter:");
        map.entrySet().stream().filter(t->t.getKey()%2==0).forEach(System.out::println);
    }
}
