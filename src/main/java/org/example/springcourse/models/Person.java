package org.example.springcourse.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fullname")
    private String fullName;
    @Pattern(regexp = "\\d{2}\\.\\d{2}\\.\\d{4}")
    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @OneToMany(mappedBy = "person", cascade = CascadeType.PERSIST)
    private List<Book> books;

    public Person(String fullName, String dateOfBirth) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
    }

    public Person() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer person_id) {
        this.id = person_id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
