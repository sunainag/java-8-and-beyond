package com.lambda;

import java.util.List;

public class BookService {

    public void getBooksSortByName(){
        List<Book> books = new BookDao().getBooks();
        System.out.println("Unsorted list: "+books);
//        To sort:
//        1. Comparator, implement compare(o1, o2) and then call Collections.sort(list, comparatorName), or
//        2. create anonymous Comparator class, or
//        3. use lambda

    }

    public static void main(String[] args) {
        BookService service = new BookService();
        service.getBooksSortByName();
    }
}
