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
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate, SessionFactory sessionFactory) {
        this.jdbcTemplate = jdbcTemplate;
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        return sessionFactory.getCurrentSession().createQuery("from Person", Person.class)
                .getResultList();
    }

    @Transactional
    public void save(Person person) {
        sessionFactory.getCurrentSession().persist(person);
    }

    @Transactional
    public void update(int id, Person person) {
        Person toBeUpdated = sessionFactory.getCurrentSession().get(Person.class, id);
        toBeUpdated.setFullName(person.getFullName());
        toBeUpdated.setDateOfBirth(person.getDateOfBirth());
    }

    @Transactional
    public Person show(int id) {
        return sessionFactory.getCurrentSession().get(Person.class, id);
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class, id));
    }
@Transactional
    public List<Book> showBooks(int id) {
        return sessionFactory.getCurrentSession().createQuery("from Book b join b.person where b.person.id=:personId", Book.class)
                .setParameter("personId", id)
                .getResultList();
    }

    public Person show(String fullName) {
        return sessionFactory.getCurrentSession().createQuery("from Person where fullName like :fullname", Person.class)
                .setParameter("fullname", fullName)
                .getSingleResultOrNull();
    }

}
