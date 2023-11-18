package org.example.springcourse.dao;

import org.example.springcourse.models.Book;
import org.example.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        List<Book> books = jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
        books.forEach(b -> {
            if (b.getPerson() == null) {
                b.setPerson(new Person());
                b.getPerson().setPerson_id(0);
            }
        });
        return books;
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(person_id, name, author, year) VALUES (?, ?, ?, ?)",
                book.getPerson().getPerson_id(), book.getName(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE Book set person_id=?, name=?, author=?, year=? where book_id=?",
                book.getPerson().getPerson_id(), book.getName(), book.getAuthor(), book.getYear(), id);
    }

    public Book show(int id) {
        return jdbcTemplate.query("select * from Book where book_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from Book where book_id=?", id);
    }

    public Person showPerson(int id) {
        List<Person> people = jdbcTemplate.query("select * from person join book on book.person_id=person.person_id and book_id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class));
        return people.isEmpty() ? null : people.get(0);
    }

    public void freeBook(int id) {
        jdbcTemplate.update("update book set person_id=null where book_id=?", id);
    }

    public void addOwner(int bookId, int personId) {
        jdbcTemplate.update("update book set person_id=? where book_id=?", personId, bookId);
    }
}
