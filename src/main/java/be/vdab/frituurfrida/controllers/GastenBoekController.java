package be.vdab.frituurfrida.controllers;

import be.vdab.frituurfrida.domain.GastenboekBericht;
import be.vdab.frituurfrida.domain.Snack;
import be.vdab.frituurfrida.exceptions.GastenboekLeegException;
import be.vdab.frituurfrida.forms.BeginLetterForm;
import be.vdab.frituurfrida.forms.BerichtForm;
import be.vdab.frituurfrida.services.GastenBoekService;
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

@Controller
@RequestMapping("gastenboek")
class GastenBoekController {

    @Autowired
    private GastenBoekService service;

    @GetMapping
    public ModelAndView toonBerichten() throws GastenboekLeegException {
        ModelAndView modelAndView = new ModelAndView("gastenboek");
        modelAndView.addObject("berichten", service.findAll());
        return modelAndView;
    }

    @GetMapping("/toevoegen/form")
    public ModelAndView toonBerichtenForm() throws GastenboekLeegException {
        ModelAndView modelAndView = new ModelAndView("gastenboek");
        modelAndView.addObject("berichten", service.findAll());
        modelAndView.addObject("bericht", new GastenboekBericht(0, "", ""));
        return modelAndView;
    }

    @PostMapping("/toevoegen")
    public String toevoegBericht(GastenboekBericht bericht, Errors errors){
        if (errors.hasErrors()){
            return "redirect:/gastenboek";
        }
        service.insert(bericht);
        return "redirect:/gastenboek";
    }
}

