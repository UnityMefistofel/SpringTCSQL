package ru.maxima.springtcsql.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.maxima.springtcsql.Book;
import ru.maxima.springtcsql.DAO.MainDAO;
import ru.maxima.springtcsql.Person;

@Controller
@RequestMapping("/books")
public class BookController {

    private MainDAO mainDAO;

    public BookController(MainDAO mainDAO) {
        this.mainDAO = mainDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", mainDAO.getAllBooks());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String elem(@PathVariable("id") String id, Model model) {
        Book book = mainDAO.getBookById(Long.parseLong(id));
        model.addAttribute("book",book);
        model.addAttribute("owner", mainDAO.getPersonById(book.getOwner()));
        if (book.getOwner() == 0) {
            model.addAttribute("people", mainDAO.getAllPersons());
        }
        return "books/elem";
    }

    @GetMapping("/create")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") Book book) {
        mainDAO.createBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") String id, Model model) {
        model.addAttribute("book", mainDAO.getBookById(Long.parseLong(id)));
        return "books/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("book") Book book) {
        mainDAO.updateBook(book);
        return "redirect:/books"+book.getId();
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") String id) {
        mainDAO.deleteBook(Long.parseLong(id));
        return "redirect:/books";
    }

    @PostMapping("/giveto")
    public String giveBookTo(@ModelAttribute("book") Book book) {
        mainDAO.setBookToOwner(book.getId(), book.getOwner());
        return "redirect:/books/"+book.getId();
    }

    @GetMapping("/{id}/return")
    public String returnBook(@PathVariable("id") String id) {
        mainDAO.returnBook(Long.parseLong(id));
        return "redirect:/books/"+id;
    }
}
