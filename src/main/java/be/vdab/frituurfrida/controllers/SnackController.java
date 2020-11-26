package be.vdab.frituurfrida.controllers;

import be.vdab.frituurfrida.domain.Saus;
import be.vdab.frituurfrida.domain.Snack;
import be.vdab.frituurfrida.forms.BeginLetterForm;
import be.vdab.frituurfrida.services.SausService;
import be.vdab.frituurfrida.services.SnackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("snacks")
class SnackController {
    private final char[] alfabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    @Autowired
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

    @GetMapping("beginLetters/form")
    public ModelAndView beginLetterForm(){
        return new ModelAndView("beginLetters")
                .addObject(new BeginLetterForm("a"));
    }

    @GetMapping("beginLetters")
    public ModelAndView beginLetters(BeginLetterForm form){
        ModelAndView modelAndView = new ModelAndView("beginLetters");
        modelAndView.addObject("snacks",
                snackService.findByBeginName(form.getBeginletters()));
        return modelAndView;

    }

    @GetMapping("wijzigen/{id}/form")
    public ModelAndView wijzigenSnackForm(@PathVariable long id){
        ModelAndView modelAndView =
                new ModelAndView("wijzigenSnack");
        snackService.read(id).ifPresent(snack -> modelAndView.addObject(snack));
        return modelAndView;
    }

    @PostMapping("wijzigen")
    public String wijzigenSnack(@Valid Snack snack, Errors errors, RedirectAttributes redirect){
        if (errors.hasErrors()) {
            return "wijzigSnack";
        }
        snackService.update(snack);
        return "redirect:/";

    }
}

