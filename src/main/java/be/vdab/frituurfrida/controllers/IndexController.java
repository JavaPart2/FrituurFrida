package be.vdab.frituurfrida.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("/")
class IndexController {
    @GetMapping
    public ModelAndView index(){
        var openOfGesloten = LocalDate.now().getDayOfWeek().getValue() == 1 ? "gesloten" : "open";
        return new ModelAndView("index", "open", openOfGesloten);
    }

}

