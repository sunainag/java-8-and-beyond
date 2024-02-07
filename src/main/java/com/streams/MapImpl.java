package com.streams;

import com.commons.Book;
import com.commons.BookDao;

import java.util.*;
import java.util.stream.Collectors;

public class MapImpl {

    public static void main(String[] args) {
        //1. sorted map using treeMap
        //2. if we have hashMap, then: Collections.sort takes a list only
        Map<Integer, String> map = new HashMap(){{
            put(1,"one");
            put(12,"twelve");
            put(3,"three");
            put(4,"four");
            put(2,"two");
        }};

        List<Map.Entry<Integer, String>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (entry1, entry2)-> entry1.getKey().compareTo(entry2.getKey()));
        System.out.println(list); //sorted order

        //stream api to sort
        map.entrySet().stream().sorted(Comparator.comparing(entry->entry.getKey())).forEach(System.out::println);
        System.out.println(map);
        map.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);

        Map<Integer, Book> empMap = new HashMap();
        List<Book> books = new BookDao().getBooks();
        int i=1;
        for(Book book: books){
            empMap.put(i++, book);
        }

        empMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.comparing(Book::getName).reversed())).forEach(System.out::println);

        //Map and flatMap are intermediate methods and return another stream as part of the output
        //map-> used for data transformation   Stream<R> map(Stream<T> input){}    one-to-one mapping
        //flatMap-> used for data transformation & flattening the data  Stream<R> flatMap(Stream<Stream<T>> input){}   one-to-many mapping

        List<Book> allBooksWithPublishers = new BookDao().getAllBooksWithPublishers();

        //List<Book> -> List<String>  : use map for data transformation Here one-to-one mapping between book and names. This will fail if we try to get publishers since it is one-to-many
        List<String> allBookNames = allBooksWithPublishers.stream().map(book-> book.getName()).collect(Collectors.toList());
        System.out.println(allBookNames);

        //Book-> book publishers  : data transformation
        List<List<String>> allPublishers = allBooksWithPublishers.stream().map(book-> book.getPublishers()).collect(Collectors.toList());
        System.out.println(allPublishers); //return Stream of Stream data: [[abc, def], [dfg, def], [ghi, dfg], [abc, jkl]]

        //Hence go for flatMap : one-to-many mapping
        //flatMap takes Stream<Stream<T>> input
        //Step1: Book-> book publishers  : data transformation
        //Step2: book publishers-> each publisher : data flattening
        List<String> allPublisherNames = allBooksWithPublishers.stream().flatMap(book-> book.getPublishers().stream()).collect(Collectors.toList());
        System.out.println(allPublisherNames); //returns: [abc, def, dfg, def, ghi, dfg, abc, jkl]
    }
}
