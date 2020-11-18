package be.vdab.frituurfrida.controllers;

import be.vdab.frituurfrida.domain.Adres;
import be.vdab.frituurfrida.domain.Gemeente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("/")
class IndexController {
    private static final int EEN_JAAR_IN_SECONDEN = 31_536_000;

    @GetMapping
    public ModelAndView index(@CookieValue(required = false) String visits,
                              HttpServletResponse response){
        var cookie = new Cookie("visits", "0");
        String welkom;

        if (visits == null){
            welkom = "Welkom";
            visits = "0";
        } else if (visits.equals("0")){
            welkom = "Welkom";
        } else {
            welkom = "Welkom terug";
        }
        cookie.setValue(String.valueOf(Integer.valueOf(visits) + 1));
        cookie.setMaxAge(EEN_JAAR_IN_SECONDEN);
        cookie.setPath("/");
        response.addCookie(cookie);

        var openOfGesloten = LocalDate.now().getDayOfWeek().getValue() == 1 ? "gesloten" : "open";
        var modelandview = new ModelAndView("index", "open", openOfGesloten);
        modelandview.addObject("adres", new Adres("Grote straat", "99",
                new Gemeente("Gent", 9000)));
        modelandview.addObject("welkom", welkom);
        return modelandview;
    }

}

