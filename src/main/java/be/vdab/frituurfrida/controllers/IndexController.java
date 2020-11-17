package be.vdab.frituurfrida.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public String index(){
        var openOfGesloten = LocalDate.now().getDayOfWeek().getValue() == 1 ? "gesloten" : "open";
        return "<!doctype html><html><title>Hallo</title><body>De frituur is " + openOfGesloten + "</body></html>";
    }

}

