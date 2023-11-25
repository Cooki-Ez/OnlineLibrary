package org.example.springcourse.dao;

import org.example.springcourse.models.Book;
import org.example.springcourse.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class BookDAO {

    private final SessionFactory sessionFactory;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(SessionFactory sessionFactory, JdbcTemplate jdbcTemplate) {
        this.sessionFactory = sessionFactory;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly = true)
    public List<Book> index() {
        return sessionFactory.getCurrentSession().createQuery("from Book", Book.class).getResultList();
    }

    @Transactional
    public void save(Book book) {
        sessionFactory.getCurrentSession().persist(book);
    }

    @Transactional
    public void update(int id, Book book) {
        Session session = sessionFactory.getCurrentSession();
        Book toBeUpdated = session.get(Book.class, id);
        toBeUpdated.setPerson(book.getPerson());
        toBeUpdated.setAuthor(book.getAuthor());
        toBeUpdated.setName(book.getName());
        toBeUpdated.setYear(book.getYear());
    }

    @Transactional(readOnly = true)
    public Book show(int id) {
        return sessionFactory.getCurrentSession().get(Book.class, id);
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Book.class, id));
    }

    @Transactional
    public Person showPerson(int id) {
        return sessionFactory.getCurrentSession().createQuery("from Person p join p.books b where b.id=:bookId", Person.class)
                .setParameter("bookId", id).getSingleResultOrNull();
    }

    @Transactional
    public void freeBook(int id) {
        sessionFactory.getCurrentSession().createQuery("update Book set person = null where book_id=:bookId", Book.class)
                .setParameter("bookId", id);
    }

    public void addOwner(int bookId, int personId) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("update Book b set person=:newPerson where book_id=:bookId", Book.class)
                .setParameter("newPerson", session.get(Person.class, personId))
                .setParameter("bookId", bookId);
    }
}
