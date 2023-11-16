package org.example.springcourse.dao;

import org.example.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class personDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public personDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("Select * from Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO person(fullname, dateofbirth) VALUES (?, ?)", person.getFullName(), person.getDateOfBirth());
    }

    public void update(int id, Person person){
        jdbcTemplate.update("UPDATE person set fullName=?, dateOfBirth=? where person_id=?", person.getFullName(), person.getDateOfBirth(), id);
    }

    public Optional<Person> show(int id){
        return jdbcTemplate.query("select * from Person where person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }
    public void delete(int id){
        jdbcTemplate.update("delete from person where person_id=?", id);
    }

}
