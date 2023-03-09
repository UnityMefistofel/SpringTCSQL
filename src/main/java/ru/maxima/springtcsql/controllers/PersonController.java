package ru.maxima.springtcsql.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.maxima.springtcsql.DAO.MainDAO;
import ru.maxima.springtcsql.Person;

@Controller
@RequestMapping("/persons")
public class PersonController {
    private MainDAO mainDAO;

    public PersonController(MainDAO mainDAO) {
        this.mainDAO = mainDAO;
    }

    // основная страница
    @GetMapping()
    public String index (Model model){
        //получение всех людей из нашей бд и пересылать их в отображение
        model.addAttribute("people", mainDAO.getAllPersons());
        return "persons/index";
    }

    @GetMapping("/{id}")
    public String elem(@PathVariable("id") String id, Model model) {
        model.addAttribute("person",mainDAO.getPersonById(Long.parseLong(id)));
        model.addAttribute("books", mainDAO.getBooksByOwner(Long.parseLong(id)));
        return "persons/elem";
    }

    @GetMapping("/create")
    public String addPerson(Model model) {
        model.addAttribute("person", new Person());
        return "persons/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person) {
        mainDAO.createPerson(person);
        return "redirect:/persons";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(Model model, @PathVariable("id") String id) {
        model.addAttribute("person",mainDAO.getPersonById(Long.parseLong(id)));
        return "persons/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("person") Person person) {
        mainDAO.updatePerson(person);
        return "redirect:/persons";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") String id) {
        mainDAO.deletePerson(Long.parseLong(id));
        return "redirect:/persons";
    }
}
