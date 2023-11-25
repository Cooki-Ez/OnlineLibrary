package org.example.springcourse.controllers;

import jakarta.validation.Valid;
import org.example.springcourse.dao.BookDAO;
import org.example.springcourse.dao.PersonDAO;
import org.example.springcourse.models.Book;
import org.example.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person){
        model.addAttribute("book", bookDAO.show(id));
        Person tmp = bookDAO.showPerson(id);
        if(tmp != null) model.addAttribute("owner", tmp);
        else model.addAttribute("people", personDAO.index());
        return "books/show";
    }

    @PatchMapping("/{id}/addowner")
    public String addOwner(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        bookDAO.addOwner(id, person.getPerson_id());
        return "redirect:/books/{id}";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "books/new";
        bookDAO.save(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(Model model, @PathVariable("id") int id){
        model.addAttribute(bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors())
            return "books/edit";
        bookDAO.update(id, book);
        return "redirect:/books";
    }
    @PatchMapping("/{id}/freebook")
    public String freeBook(@PathVariable("id") int id){
        bookDAO.freeBook(id);
        return "redirect:/books/{id}";
    }
}
