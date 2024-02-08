package com.streams;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GeeksForGeeksExercises {
    public static void main(String[] args) {
        //1. Converting ArrayList to HashMap in Java 8 using a Lambda Expression
        //Input : List : [1="1", 2="2", 3="3"]
        //Output: Map  : {1=1, 2=2, 3=3, 4=4, 5=5}
        List<ListItems> list = new ArrayList(){{
            add(new ListItems(1, "1"));
            add(new ListItems(2, "2"));
            add(new ListItems(3, "3"));
        }};


        //TODO: check use of Collections.emptyMap()
        Map<Integer, Integer> resMap = list.stream().collect(Collectors.toMap(item->item.getKey(), item->Integer.valueOf(item.getValue())));
        System.out.println(resMap);

        //2. Extract HashMap from an ArrayList of Hashmap by using lambda expression with Java 8

        Map<String, String> myMap = new HashMap<String, String>() {{
            put("label1", "test0");
            put("label2", "test1");
            put("label3", "test2");
        }};
        List<Map<String, String>> listOfMap = new ArrayList<>();
        listOfMap.add(new HashMap<>(myMap));

        myMap.clear();
        myMap.put("1","1");
        myMap.put("2","2");

        listOfMap.add(myMap);
        System.out.println("List of map: "+ listOfMap); //output: [{label1=test0, label2=test1, label3=test2}, {1=1, 2=2}]

        Map<String, String> flatMap = listOfMap.stream().flatMap(mapEntry->mapEntry.entrySet().stream()).collect(Collectors.toMap(s-> s.getKey(), s-> s.getValue()));
        System.out.println(flatMap); //output: {1=1, 2=2, label1=test0, label2=test1, label3=test2}


        //3.Check if a String Contains Only Alphabets in Java
        //Input : GeeksforGeeks
        //Output : True

        //Input  : Geeks4Geeks
        //Output : False

        //Input  : null
        //Output : False

        String input = "abcdfs";
        System.out.println("All Characters?: "+ !input.chars().filter(c-> !Character.isLetter(c)).findAny().isPresent());

        //4. Remove elements from a List that satisfy given predicate
        //Please note: without assigning list = list.stream()... , the list was not altered
        list = list.stream().filter(item-> item.getKey()!=2).collect(Collectors.toList());
        System.out.println(list); //output: [ListItems(key=1, value=1), ListItems(key=3, value=3)]

        //No assignment of variable was required here
        list.removeIf(item-> item.getValue()=="1");
        System.out.println(list);  //output: [ListItems(key=3, value=3)]


        //Collection.stream().forEach() VS Collection.forEach()

        //Collection.forEach() uses the collection’s iterator (if one is specified),
        // so the processing order of the items is defined.
        // In contrast, the processing order of Collection.stream().forEach() is undefined

        //list.forEach() processes the items in insertion order,
        // while list.parallelStream().forEach() produces a different result at each run
        List<Integer> l = IntStream.range(1,10000).boxed().collect(Collectors.toList());
        long start = System.currentTimeMillis();

        for(int i: l){
            System.out.print(i+" ");
        }
        long end = System.currentTimeMillis();
        System.out.println("\nTime taken by enhanced-for loop: "+ (end-start));

        Consumer<Integer> consumer = t-> System.out.print(t+" ");
        start = System.currentTimeMillis();
        l.forEach(consumer);
        end = System.currentTimeMillis();
        System.out.println("\nTime taken by functional style forEach loop: "+ (end-start));

        start = System.currentTimeMillis();
        l.stream().forEach(consumer);
        end = System.currentTimeMillis();
        System.out.println("\nTime taken by functional style stream.forEach loop: "+ (end-start));

        start = System.currentTimeMillis();
        l.parallelStream().forEach(consumer);
        end = System.currentTimeMillis();
        System.out.println("\nTime taken by functional style parallelStream.forEach loop: "+ (end-start));

        //ArrayList, HashSet throw ConcurrentModificationException if modified during iteration => fail-fast
        //Same exception if coll modified during execution of stream pipeline. However, the exception will be thrown later.
        //However, Java does not guarantee that a ConcurrentModificationException is thrown at all.
        // That means we should never write a program that depends on this exception.
        //If we don’t require a stream but only want to iterate over a collection,
        // the first choice should be using forEach() directly on the collection.
        List<Integer> list1 = IntStream.range(1,4).boxed().collect(Collectors.toList());
        Consumer<Integer> removeElement = s -> {
            System.out.println(s + " " + list1.size());
            if (s != null && s==2) {
                list1.remove(list1.size()-1);
            }
        };
        //list1.forEach(removeElement);
        //output: 1 3
        //        2 3
        //        Exception in thread "main" java.util.ConcurrentModificationException

        //list1.stream().forEach(removeElement);
        //output: 1 3
        //        2 3
        //        null 2
        //        Exception in thread "main" java.util.ConcurrentModificationException


        //But, We can change an element while iterating over a list using either Collection.forEach() or stream().forEach():, but should not be done as this could lead to unexpected behavior.
        list1.forEach(e -> {
            list1.set(3, 13);
        });
    }
}

@Data
@AllArgsConstructor
class ListItems{
    int key;
    String value;
}
