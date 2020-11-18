package be.vdab.frituurfrida.controllers;

import be.vdab.frituurfrida.domain.Adres;
import be.vdab.frituurfrida.domain.Gemeente;
import be.vdab.frituurfrida.domain.Saus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Arrays;

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
    @GetMapping
    public ModelAndView index(){
        var modelandview = new ModelAndView("sauzen", "sauzen", sauzen);
        return modelandview;
    }

    @GetMapping("{id}")
    public ModelAndView sauss(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("saus");
        Arrays.stream(sauzen).filter(saus -> saus.getNummer() == id).findFirst()
                .ifPresent(saus -> modelAndView.addObject("saus", saus));
        return modelAndView;
    }
}

