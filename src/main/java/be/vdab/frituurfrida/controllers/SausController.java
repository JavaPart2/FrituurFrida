package be.vdab.frituurfrida.controllers;

import be.vdab.frituurfrida.domain.Saus;
import be.vdab.frituurfrida.forms.RaadDeSausForm;
import be.vdab.frituurfrida.services.SausService;
import be.vdab.frituurfrida.sessions.RaadSauzen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;
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
    private final SausService sausService;
    private final RaadSauzen sausRaden;

    SausController(SausService sausService, RaadSauzen sausRaden) {
        this.sausService = sausService;
        this.sausRaden = sausRaden;
    }

    public String randomSaus(){
        List<Saus> sauzen = sausService.findAll();
        int aantalSauzen = sauzen.size();
        int randomNummer = new Random().nextInt(aantalSauzen);
        return sauzen.get(randomNummer).getNaam();
    }

    @GetMapping("raaddesaus")
    public ModelAndView sausRadenStart(){
        sausRaden.nieuwSpel(randomSaus());
        ModelAndView modelAndView = new ModelAndView("raaddesaus");
        modelAndView.addObject("game", sausRaden);
        modelAndView.addObject(new RaadDeSausForm(null));
        return modelAndView;
    }

    @GetMapping("raaddesaus/gok")
    public ModelAndView sausGok(){
        ModelAndView modelAndView = new ModelAndView("raaddesaus");
        modelAndView.addObject("game", sausRaden);
        modelAndView.addObject(new RaadDeSausForm(null));
        return modelAndView;
    }

    @PostMapping("raaddesaus/gok")
    public ModelAndView sausGokgedaan(@Valid RaadDeSausForm form, Errors errors){
        if (errors.hasErrors()){
            return new ModelAndView("raaddesaus").addObject(sausRaden);
        }
        sausRaden.gok(form.getGokletter());
        return new ModelAndView("redirect:/sauzen/raaddesaus/gok");
    }

    @PostMapping("raaddesaus/nieuwspel")
    public String nieuwSpel(){
        return "redirect:/sauzen/raaddesaus";
    }

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

