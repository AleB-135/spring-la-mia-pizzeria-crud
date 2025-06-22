package org.lessons.java.spring.crud.spring_pizzeria_crud.controller;

import java.util.List;

import org.lessons.java.spring.crud.spring_pizzeria_crud.model.Pizza;
import org.lessons.java.spring.crud.spring_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping ("/pizze")
public class PizzeController {

    @Autowired //Forza la dipendency injection (Crea una classe temporanea che implementa l'interfaccia e la inserisca in pizzaRepository)
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String index(Model model){

        List<Pizza> Pizza = pizzaRepository.findAll();  //Prenderà dal DB i dati che servono
        model.addAttribute("pizze", Pizza);
        return"pizze/index";
    }

}
