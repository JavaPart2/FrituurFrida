package be.vdab.frituurfrida.controllers;

import be.vdab.frituurfrida.domain.Adres;
import be.vdab.frituurfrida.domain.Gemeente;
import be.vdab.frituurfrida.domain.Saus;
import be.vdab.frituurfrida.services.SausService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("sauzen")
class SausController {
    private final Saus[] sauzen = {
            new Saus(1, "Cocktail", new String[]{"ei", "azijn", "olie", "zout", "ketchup"}),
            new Saus(2, "Mayonaise", new String[]{"ei", "azijn", "olie", "zout"}),
            new Saus(3, "mosterd", new String[]{"azijn", "olie", "zout"}),
            new Saus(4, "Tartare", new String[]{"ei", "azijn", "olie", "zout", "kruiden"}),
            new Saus(5, "Vinaigrette", new String[]{"azijn", "olie", "zout"})
    };

    @Autowired
    private SausService sausService;

    @GetMapping
    public ModelAndView index(){
        var modelandview = new ModelAndView("sauzen", "sauzen", sausService.findAll());
        return modelandview;
    }

    @GetMapping("{id}")
    public ModelAndView sauss(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("saus");
        sausService.findById(id)
                .ifPresent(saus -> modelAndView.addObject("saus", saus));
//        Arrays.stream(sauzen).filter(saus -> saus.getNummer() == id).findFirst()
//                .ifPresent(saus -> modelAndView.addObject("saus", saus));
        return modelAndView;
    }

    private List<Character> beginLetters(){
//        return Arrays.stream(sauzen).map(saus -> saus.getNaam().toLowerCase().charAt(0))
//                .distinct().sorted().collect(Collectors.toList());
        return sausService.findAll().stream().map(saus -> saus.getNaam().toLowerCase().charAt(0))
                .distinct().sorted().collect(Collectors.toList());
    }

    @GetMapping("alfabet")
    public ModelAndView alfabet(){
        return new ModelAndView("alfabet", "beginletters", beginLetters());

    }

    private List<Saus> sauzenMetLetter(Character letter){
//        return Arrays.stream(sauzen)
//                .filter(saus -> saus.getNaam().toLowerCase().charAt(0) == letter)
//                .collect(Collectors.toList());
        return sausService.findByNameBeginMet(letter);
    }

    @GetMapping("alfabet/{letter}")
    public ModelAndView sausMetLetter(@PathVariable Character letter){
        ModelAndView modelAndView =
                new ModelAndView("alfabet", "sauzen",sauzenMetLetter(letter));
        modelAndView.addObject("beginletters", beginLetters());
        return modelAndView;
    }
}

