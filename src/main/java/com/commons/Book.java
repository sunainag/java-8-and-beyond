package com.commons;

import lombok.Data;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Data
public class Book {

    public Book(int id, String name ){
        this.id=id;
        this.name=name;
    }

    public Book(int id, String name, List<String> publishers){
        this.id=id;
        this.name=name;
        this.publishers = publishers;
    }

    public Book(int id, String name, String description, String type){
        this.id=id;
        this.name=name;
        this.description = description;
    }

    private int id;
    private String name;
    private String description;
    private List<String> publishers;

    public Optional<String> getDescription(){
        return Optional.ofNullable(description);
    }

    public void setEmail(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description=" + description +
                ", publishers=" + publishers +
                '}';
    }
}
