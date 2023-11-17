package org.example.springcourse.models;


import jakarta.validation.constraints.Pattern;

public class Person {
    private int person_id;
    private String fullName;
    @Pattern(regexp = "\\d{2}\\.\\d{2}\\.\\d{4}")
    private String dateOfBirth;

    public Person(int person_id, String fullName, String dateOfBirth) {
        this.person_id = person_id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
    }

    public Person() {
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
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
