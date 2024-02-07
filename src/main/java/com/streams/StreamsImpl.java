package com.streams;

import com.commons.EmployeeDao;

import java.util.*;
import java.util.stream.Stream;

public class StreamsImpl {

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

        //        Map-reduce serves 2 purpose:
        //        1. map: to transform data
        //        2. reduce: aggregate
        //        Sum of numbers present in Stream [2,4,5,7]:
        //        1. map object to stream of int,
        //        2. combine stream of int to produce sum result

        //        reduce(T identity, BinaryOperator<T> accumulator)
        Stream.of(1,2,3,4).reduce(0, (a, b)->a+b);

        List<Integer> nums = Arrays.asList(1,2,3,4);

        //1. Iterate and add to find sum
        //2. use IntStream provided sum method:
        System.out.println(nums.stream().mapToInt(i->i).sum());

        //3. use streams map-reduce:
        System.out.println(nums.stream().reduce(0,(a,b)->a+b));

        System.out.println(nums.stream().reduce(Integer::sum).get());

        System.out.println(nums.stream().reduce(Integer::max).get());

        //example use reduce to find longest word length in list using:
        List<String> words = Arrays.asList("asdas","sdSdfqsdf","asdas");
        System.out.println("Longest word is: "+ words.stream().reduce((word1, word2)-> word1.length()>word2.length()?word1:word2).get());

        //Step1: get employees with grade A
        //Step2: get salary of emp => data transformation
        //Step3: convert to stream of double
        //Step4: reduce to average salary => returns Optional
        //Step5: get value as double
        double averageSalaryOfGradeAEmployees = EmployeeDao.getEmployees().stream()
                .filter(emp-> emp.getGrade().equalsIgnoreCase("A"))
                .map(emp-> emp.getSalary())
                .mapToDouble(salary-> salary)
                .average()
                .getAsDouble();
        System.out.println("Average salary: "+ averageSalaryOfGradeAEmployees);

    }
}
