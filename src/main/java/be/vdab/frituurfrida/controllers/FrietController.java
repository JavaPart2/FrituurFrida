package be.vdab.frituurfrida.controllers;

import be.vdab.frituurfrida.sessions.Game;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("zoekdefriet")
public class FrietController {
    private final Game game;

    public FrietController(Game game) {
        this.game = game;
    }

    @GetMapping
    public ModelAndView toonGame(){
        ModelAndView modelAndView = new ModelAndView("zoekdefriet");
        modelAndView.addObject("deuren", game.getDeuren());
        return modelAndView;
    }

    @GetMapping("{ind}")
    public String  button(@PathVariable int ind){
        game.openDeur(ind);
        return "redirect:/zoekdefriet";
    }

    @PostMapping("nieuwspel")
    public String nieuwSpel(){
        game.nieuwSpel();
        return "redirect:/zoekdefriet";
    }
}
