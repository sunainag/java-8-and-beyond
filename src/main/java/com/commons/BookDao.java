package com.commons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookDao {

    public List<Book> getBooks(){
        return new ArrayList<Book>(){{
            add(new Book(1, "coreJava"));
            add(new Book(2, "spring"));
            add(new Book(3, "javaAdvances"));
            add(new Book(4, "aws"));
        }};
    };

    public List<Book> getAllBooksWithPublishers(){
        return Stream.of(new Book(1, "coreJava", Arrays.asList("abc","def")),
                  new Book(2, "spring", Arrays.asList("dfg","def")),
                  new Book(3, "javaAdvances", Arrays.asList("ghi","dfg")),
                  new Book(4, "aws", Arrays.asList("abc","jkl"))
        ).collect(Collectors.toList());
    }
}
