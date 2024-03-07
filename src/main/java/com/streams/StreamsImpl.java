package com.streams;

import com.commons.EmployeeDao;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
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
        List<String> words = Arrays.asList("asdas","sdSdfqsdf","fsdas");
        System.out.println("Longest word is: "+ words.stream().reduce((word1, word2)-> word1.length()>word2.length()?word1:word2).get());

        //Create Map from List
        HashMap<String, Integer> res = words.stream().collect(Collectors.toMap(
                                                                                Function.identity(), String::length,
                                                                                (e1, e2) -> e1, HashMap::new)
                                                              );
        System.out.println("Elements in HashMap are : "+ res);

        //Step1: get employees with grade A
        //Step2: get salary of emp => data transformation
        //Step3: convert to stream of double
        //Step4: reduce to average salary => returns Optional
        //Step5: get value as double
        double averageSalaryOfGradeAEmployees    = EmployeeDao.getEmployees().stream()
                .filter(emp-> emp.getGrade().equalsIgnoreCase("A"))
                .map(emp-> emp.getSalary())
                .mapToDouble(salary-> salary)
                .average()
                .getAsDouble();
        System.out.println("Average salary: "+ averageSalaryOfGradeAEmployees);

        //ways to create stream
        //1. collection
        list.stream();

        //2. Sequential stream
        Stream.of(1,3,4);

        //3. Arrays.stream
        Arrays.stream(args).iterator();

        int[] arr = {1,3,5,45,2,1};
        System.out.println("Does array "+Arrays.toString(arr)+" contain number 5?: " +(Arrays.stream(arr).anyMatch(ele-> ele==5)));

        //4.
        Stream.empty();

        //5. The builder() method is used when the desired type should be additionally specified in the right part of the statement, otherwise the build() method will create an instance of the Stream.
        Stream.Builder<String> builder = Stream.builder();
        Stream<String> stream = builder.add("a").build();

        //6.  iterate() method returns an infinite sequential ordered Stream
        // produced by iterative application of a function f to an initial element seed
        int seedValue = 2, limitTerms = 5;
        Stream.iterate(2,(Integer n) -> n*n).limit(limitTerms).forEach(System.out::println);

        //7.The generate() method accepts a Supplier for generating elements
        // and the resulting stream is infinite.
        // So to restrict it, specify the desired size or the generate() method will work until
        // it reaches the memory limit.
        Stream.generate(Math::random)
                .limit(limitTerms)
                .forEach(System.out::println);

        List<String> listUseCollectors = Arrays.asList("a", "bb", "c", "d", "bb");
        String result = listUseCollectors.stream()
                .collect(Collectors.joining("","PRE-","-POST"));
        System.out.println("Joining using collectors: "+ result);

        Map<Integer, Set<String>> setFromList = listUseCollectors.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.toSet()));
        setFromList.entrySet().forEach(entry-> System.out.print(entry+", "));

        Map<Boolean, List<String>> listFromListUsingPredicate = listUseCollectors.stream()
                .collect(Collectors.partitioningBy(s->s.length()>1));
        listFromListUsingPredicate.entrySet().forEach(System.out::println);

        List<Integer> numbers = Arrays.asList(42, 4, 2, 24);
        Optional<Integer> min = numbers.stream().collect(Collectors.minBy(Integer::compareTo));
        System.out.println(min.get());
    }
}
