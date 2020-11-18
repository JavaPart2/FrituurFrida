package be.vdab.frituurfrida.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Controller
@RequestMapping("visit")
class VisitController {
    private static final int EEN_JAAR_IN_SECONDEN = 31_536_000;
    @GetMapping
    public ModelAndView toonPagina(@CookieValue(required = false) int nbrVisits){
        return new ModelAndView("visit", "visits", nbrVisits);
    }
    @GetMapping("{kleur}")
    public String kiesKleur(@PathVariable String kleur,
                            HttpServletResponse response){
        var cookie = new Cookie("kleur", kleur);
        cookie.setMaxAge(EEN_JAAR_IN_SECONDEN);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "kleuren";
    }
}

