package com.lambda;

import java.util.ArrayList;
import java.util.List;

public class BookDao {

    public List<Book> getBooks(){
        return new ArrayList<Book>(){{
            add(new Book(1, "core java"));
            add(new Book(2, "spring"));
            add(new Book(3, "J2EE"));
            add(new Book(4, "AWS"));
        }};
    };
}
