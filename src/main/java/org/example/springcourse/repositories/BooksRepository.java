package org.example.springcourse.repositories;

import org.example.springcourse.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    void deleteById(int id);

    Book findBookByNameStartingWith(String name);

}
