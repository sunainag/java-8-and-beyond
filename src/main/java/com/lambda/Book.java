package com.lambda;

import lombok.Data;
import lombok.ToString;

@Data
public class Book {

    public Book(int id, String name ){
        this.id=id;
        this.name=name;
    }

    private int id;
    private String name;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
