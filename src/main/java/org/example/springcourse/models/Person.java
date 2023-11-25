package org.example.springcourse.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Integer person_id;
    @Column(name = "fullname")
    private String fullName;
    @Pattern(regexp = "\\d{2}\\.\\d{2}\\.\\d{4}")
    @Column(name = "dateofbirth")
    private String dateOfBirth;

    @OneToMany(mappedBy = "person", cascade = CascadeType.PERSIST)
    private List<Book> books;

    public Person(String fullName, String dateOfBirth) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
    }

    public Person() {
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
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
}
