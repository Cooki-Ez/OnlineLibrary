package org.example.springcourse.services;

import org.example.springcourse.models.Book;
import org.example.springcourse.models.Person;
import org.example.springcourse.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Book> getBooksByPersonId(int id){
        Person person = getById(id);
        Hibernate.initialize(person.getBooks());
        return person.getBooks();
    }


    public List<Person> index(){
        return peopleRepository.findAll();
    }

    public Person getById(int id){
        return peopleRepository.findById(id).get();
    }

    @Transactional
    public void save(Person person, int id){
        person.setId(id);
        peopleRepository.save(person);
    }

    @Transactional
    public void deleteById(int id){
        peopleRepository.deleteById(id);
    }


}
