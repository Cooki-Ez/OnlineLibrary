package org.example.springcourse.repositories;

import org.example.springcourse.models.Book;
import org.example.springcourse.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    void deleteById(int id);

}
