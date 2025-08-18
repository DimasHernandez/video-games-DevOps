package com.catalog.videogames.controllers;

import com.catalog.videogames.models.Creator;
import com.catalog.videogames.services.CreatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CreatorController {

    private final CreatorService creatorService;

    public CreatorController(CreatorService creatorService) {
        this.creatorService = creatorService;
    }

    @PostMapping("/creator/save")
    public String saveCreator(@ModelAttribute Creator creator) {
        Creator creatorDb = creatorService.save(creator);
        System.out.println("Name: " + creatorDb.getName());
        System.out.println("Country: " + creatorDb.getCountry());
        return "success";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        // agregar el atrinuto a la plantilla que el modelo podra usar
        model.addAttribute("creator", new Creator());

        // Debemos retornar el archivo index sin la extensión
        return "creator";
    }

}
