package com.optional;

import com.commons.Book;
import com.commons.BookDao;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class OptionalImpl {

    public static void main(String[] args) {
        Book book = new Book(10, "testOptional",null);
        Optional<Object> emptyOptional = Optional.empty();
        System.out.println(emptyOptional);

        //if we know obj can be null,dont use Optional.of(), it will check nul internally and throw NPE
        // Optional<List<String>> publishers = Optional.of(book.getPublishers());
        Optional<List<String>> nullPublishers = Optional.ofNullable(book.getPublishers());
        System.out.println(nullPublishers); //returns Optional.empty
        //nullPublishers.get() will give NoSuchElementException

        System.out.println(nullPublishers.isPresent()?nullPublishers.get():"No value"); // return No value
        System.out.println(nullPublishers.orElse(Arrays.asList("No value")));//returns [No value]

        //nullPublishers.orElseThrow(()->new IllegalArgumentException("Value not present"));

        System.out.println("Exists: "+filterBook("spring"));
        //System.out.println("Not Exists: "+filterBook("abc"));//NoSuchElementException

        Book existsOrDefaultBook = new BookDao().getBooks().stream().filter(obj-> obj.getDescription().equals("abc")).findAny().orElse(new Book(11, "abc"));
        System.out.println("Exists or default value: "+existsOrDefaultBook);

        existsOrDefaultBook = new BookDao().getBooks().stream().filter(obj-> obj.getDescription().toString().contains("text")).findAny().orElse(existsOrDefaultBook);
        System.out.println("Not Exists, but returns empty: "+ existsOrDefaultBook);//NoSuchElementException

    }

    private static Book filterBook(String bookName) {
        return new BookDao().getBooks().stream().filter(obj-> obj.getName().equals(bookName)).findAny().get();
    }
}
