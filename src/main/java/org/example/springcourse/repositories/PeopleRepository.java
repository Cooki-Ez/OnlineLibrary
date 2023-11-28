package org.example.springcourse.repositories;

import org.example.springcourse.models.Book;
import org.example.springcourse.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PeopleRepository extends JpaRepository<Person, Integer> {

    void deleteById(int id);


}
