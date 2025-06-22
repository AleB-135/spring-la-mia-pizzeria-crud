package org.lessons.java.spring.crud.spring_pizzeria_crud.controller;

import java.util.List;

import org.lessons.java.spring.crud.spring_pizzeria_crud.model.Pizza;
import org.lessons.java.spring.crud.spring_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping ("/pizze")
public class PizzeController {

    @Autowired //Forza la dipendency injection (Crea una classe temporanea che implementa l'interfaccia e la inserisca in pizzaRepository)
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String index(Model model, @RequestParam(name = "keyword", required = false) String keyword){ 
        //Mi aspetto di ricevere un parametro chiamato "keyword". Ciò che è salvato dentro la chiave keyword dev'essere salvato d'ora in poi con la stringa "keyword".
        List<Pizza> Pizza;
        if (keyword != null && !keyword.isEmpty()) {
            Pizza = pizzaRepository.findByNameContainingIgnoreCase(keyword);
            //Se la keyword non è vuota e sto cercando qualcosa nello specifico, torna in repository e chiama il metodo sopracitato "(findByNameContainingIgnoreCase(keyword))" riportando solo i nomi delle pizze che abbiano al loro interno anche solo una parte del nome che possiedono.
        } else {
            Pizza = pizzaRepository.findAll();
            //Se la keyword è vuota oppure è null, allora prendi una index normale
        }
        model.addAttribute("pizze", Pizza);
        return"pizze/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("Pizza", pizzaRepository.findById(id).get());
        return "pizze/show";
    }

}
