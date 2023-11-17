package org.example.springcourse.models;

public class Book {
    private int book_id;
    private Integer person_id;
    private String name;
    private String author;
    private int year;

    public Book(String name, String author, int year, Integer person_id) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.person_id = person_id;
    }

    public Book() {
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

