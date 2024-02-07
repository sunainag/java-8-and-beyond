package com.streams;

import com.lambda.Book;
import com.lambda.BookDao;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListImpl {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("1","2","3");
        //list.add("4"); unsupportedOperationException since Arrays.asList returns fixed-size list backed by array

        list = new ArrayList(){{
            add("4");
            add("5");
        }};

        List<Integer> list1 = Stream.of(8, 20, 19, 7, 2).collect(Collectors.toList());
        System.out.println(list1); //elements in order as added
        Collections.sort(list1, (o1,o2)->o2.compareTo(o1));
        System.out.println(list1); //elements in descending order

        Collections.reverse(list1);
        System.out.println(list1); //elements in ascending order

        list1.stream().sorted(Comparator.reverseOrder()).forEach(t->System.out.print(t+" "));//elements in descending order
        // java 9 onwards: list = List.of();

        System.out.println("\nSort List of books based on book name");

        List<Book> books = new BookDao().getBooks();
        books.stream().sorted(Comparator.comparing(Book::getName)).forEach(System.out::println);

    }
}
