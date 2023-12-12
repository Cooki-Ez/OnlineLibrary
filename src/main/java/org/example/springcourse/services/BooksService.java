package org.example.springcourse.services;

import org.example.springcourse.models.Book;
import org.example.springcourse.models.Person;
import org.example.springcourse.repositories.BooksRepository;
import org.example.springcourse.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public List<Book> index(Optional<Integer> page, Optional<Integer> booksPerPage, Optional<Boolean> sortByYear) {
        if (page.isPresent() && booksPerPage.isPresent()) {
            if (sortByYear.isPresent() && sortByYear.get()) {
                List<Book> list = booksRepository.findAll(PageRequest.of(page.get(), booksPerPage.get(), Sort.by("year"))).getContent();
                return list;
            }
            List<Book> list = booksRepository.findAll(PageRequest.of(page.get(), booksPerPage.get())).getContent();
            System.out.println(list);
            return list;
        }
        if (sortByYear.isPresent() && sortByYear.get()) {
            return booksRepository.findAll(Sort.by("year"));
        }
        return booksRepository.findAll();
    }


    public Book getById(int id) {
        Optional<Book> book = booksRepository.findById(id);
        return book.orElseGet(Book::new);
    }

    @Transactional
    public void addOwner(int bookId, int OwnerNewId) {
        Book book = booksRepository.findById(bookId).get();
        book.setPerson(peopleRepository.findById(OwnerNewId).get());
    }

    public Person getOwner(int bookId) {
        return booksRepository.findById(bookId).get().getPerson();
    }

    @Transactional
    public void save(Book book, int id) {
        book.setId(id);
        booksRepository.save(book);
    }

    @Transactional
    public void deleteById(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void freeBook(int bookId) {
        Person owner = getOwner(bookId);
        Book toBeRemoved = booksRepository.findById(bookId).get();
        toBeRemoved.setPerson(null);
        owner.getBooks().remove(toBeRemoved);
    }

    public Book findBookByQuery(String query){
        return booksRepository.findBookByNameStartingWith(query);
    }
}
