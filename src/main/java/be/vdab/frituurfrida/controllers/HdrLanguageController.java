package be.vdab.frituurfrida.controllers;

import be.vdab.frituurfrida.domain.Adres;
import be.vdab.frituurfrida.domain.Gemeente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Arrays;

@Controller
@RequestMapping("language")
class HdrLanguageController {
    private static final String[] languages = {"nl","en","fr","ge"};
    @GetMapping
    public ModelAndView oso(@RequestHeader("Accept-Language") String language){
        var modelAndView = new ModelAndView("requestHdr");
        Arrays.stream(languages).filter(l -> language.contains(l)).findFirst()
                .ifPresent(l -> modelAndView.addObject("hdrlang", l));
        return modelAndView;
    }

}

