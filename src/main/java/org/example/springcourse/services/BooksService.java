package org.example.springcourse.services;

import org.example.springcourse.models.Book;
import org.example.springcourse.models.Person;
import org.example.springcourse.repositories.BooksRepository;
import org.example.springcourse.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> index(){
        return booksRepository.findAll();
    }

    public Book getById(int id){
        return booksRepository.findById(id).get();
    }

    @Transactional
    public void addOwner(int bookId, int OwnerNewId){
        Book book = booksRepository.findById(bookId).get();
        book.setPerson(peopleRepository.findById(OwnerNewId).get());
    }

    public Person getOwner(int bookId){
        return booksRepository.findById(bookId).get().getPerson();
    }

    @Transactional
    public void save(Book book, int id){
        book.setId(id);
        booksRepository.save(book);
    }

    @Transactional
    public void deleteById(int id){
        booksRepository.deleteById(id);
    }

    @Transactional
    public void freeBook(int bookId){
        Person owner = getOwner(bookId);
        Book toBeRemoved = booksRepository.findById(bookId).get();
        toBeRemoved.setPerson(null);
        owner.getBooks().remove(toBeRemoved);
    }
}
