package be.vdab.frituurfrida.controllers;

import be.vdab.frituurfrida.domain.Adres;
import be.vdab.frituurfrida.domain.Gemeente;
import be.vdab.frituurfrida.domain.Saus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
@RequestMapping("sauzen")
class SausController {
    @GetMapping
    public ModelAndView index(){
        Saus[] sauzen = new Saus[]{
                new Saus(1, "Cocktail", new String[]{"ei", "azijn", "olie", "zout", "ketchup"}),
                new Saus(2, "Mayonaise", new String[]{"ei", "azijn", "olie", "zout"}),
                new Saus(3, "Mosterd", new String[]{"azijn", "olie", "zout"}),
                new Saus(4, "Tartare", new String[]{"ei", "azijn", "olie", "zout", "kruiden"}),
                new Saus(5, "Vinaigrette", new String[]{"azijn", "olie", "zout"})
        };
        var modelandview = new ModelAndView("sauzen", "sauzen", sauzen);
        return modelandview;
    }

}

