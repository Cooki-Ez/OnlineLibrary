package org.example.springcourse.controllers;

import jakarta.validation.Valid;
import org.example.springcourse.models.Book;
import org.example.springcourse.models.Person;
import org.example.springcourse.services.BooksService;
import org.example.springcourse.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;

        this.peopleService = peopleService;
    }


    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", booksService.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person){
        model.addAttribute("book", booksService.getById(id));
        Person tmp = booksService.getOwner(id);
        if(tmp != null) model.addAttribute("owner", tmp);
        else model.addAttribute("people", peopleService.index());
        return "books/show";
    }

    @PatchMapping("/{id}/addowner")
    public String addOwner(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        booksService.addOwner(id, person.getId());
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
        booksService.save(book, book.getId());
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        booksService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(Model model, @PathVariable("id") int id){
        model.addAttribute(booksService.getById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors())
            return "books/edit";
        booksService.save(book, id);
        return "redirect:/books";
    }
    @PatchMapping("/{id}/freebook")
    public String freeBook(@PathVariable("id") int id){
        booksService.freeBook(id);
        return "redirect:/books/{id}";
    }
}
