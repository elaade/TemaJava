package com.example.lab10.controller;

import com.example.lab10.model.Carte;
import com.example.lab10.service.CarteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/carti")
public class CarteController {
    private final CarteService carteService;

    public CarteController(CarteService carteService) {
        this.carteService = carteService;
    }

    @GetMapping
    public String listCarti(@RequestParam(required = false) String autor, Model model) {
        List<Carte> carti;
        String mesaj;

        if (autor == null || autor.isEmpty()) {
            carti = carteService.findAll();
            mesaj = "Toate cărțile disponibile.";
        } else {
            carti = carteService.findByAutor(autor);
            mesaj = carti.isEmpty()
                    ? "Nu s-au găsit cărți pentru autorul: " + autor
                    : "Cărțile următoare aparțin autorului " + autor + ".";
        }

        model.addAttribute("carti", carti);
        model.addAttribute("mesaj", mesaj);
        return "carti";
    }

    @PostMapping("/adauga")
    public String adaugaCarte(@ModelAttribute Carte carte, Model model) {
        if (carte.getIsbn() == null || carte.getTitlu() == null || carte.getAutor() == null
                || carte.getIsbn().isEmpty() || carte.getTitlu().isEmpty() || carte.getAutor().isEmpty()) {
            model.addAttribute("mesaj", "Adăugarea nu se realizează dacă nu completați toate caracteristicile!");
        } else {
            carteService.save(carte);
            model.addAttribute("mesaj", "Adăugare realizată cu succes!");
        }
        return "redirect:/carti";
    }

    @PostMapping("/modifica")
    public String modificaCarte(@ModelAttribute Carte carte, Model model) {
        Carte existingCarte = carteService.findByIsbn(carte.getIsbn());
        if (existingCarte == null) {
            model.addAttribute("mesaj", "Nu se găsește nicio carte cu ISBN-ul introdus.");
        } else {
            existingCarte.setTitlu(carte.getTitlu());
            existingCarte.setAutor(carte.getAutor());
            carteService.save(existingCarte);
            model.addAttribute("mesaj", "Cartea cu ISBN-ul " + carte.getIsbn() + " a fost modificată!");
        }
        return "redirect:/carti";
    }

    @PostMapping("/sterge")
    public String stergeCarte(@RequestParam String isbn, Model model) {
        Carte existingCarte = carteService.findByIsbn(isbn);
        if (existingCarte == null) {
            model.addAttribute("mesaj", "Nu se găsește nicio carte cu ISBN-ul introdus.");
        } else {
            carteService.deleteByIsbn(isbn);
            model.addAttribute("mesaj", "Cartea cu ISBN-ul " + isbn + " a fost ștearsă cu succes!");
        }
        return "redirect:/carti";
    }
}

