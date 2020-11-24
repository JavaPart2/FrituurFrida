package be.vdab.frituurfrida.controllers;

import be.vdab.frituurfrida.domain.Saus;
import be.vdab.frituurfrida.domain.Snack;
import be.vdab.frituurfrida.services.SausService;
import be.vdab.frituurfrida.services.SnackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("snacks")
class SnackController {
    private final char[] alfabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    private SnackService snackService;

    @GetMapping
    public ModelAndView alfabet(){
        return new ModelAndView("snacks", "beginletters", alfabet);

    }

    @GetMapping("/{letter}")
    public ModelAndView sausMetLetter(@PathVariable Character letter){
        ModelAndView modelAndView =
                new ModelAndView("snacks", "snacks",
                        snackService.findByBeginName(String.valueOf(letter)));
        modelAndView.addObject("beginletters", alfabet);
        return modelAndView;
    }
}

