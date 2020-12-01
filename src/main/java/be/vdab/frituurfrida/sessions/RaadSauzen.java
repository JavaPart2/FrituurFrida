package be.vdab.frituurfrida.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.Random;

@Component
@SessionScope
public class RaadSauzen implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int MAXVERKEERDEPOGINGEN = 15;
    private StringBuilder sausPuntjes;
    private String teRadenSaus;
    private int aantalPogingen = 0;
    private int aantalVerkeerdePogingen = 0;

    public RaadSauzen() {
        nieuwSpel(" ");
    }

    public void nieuwSpel(String saus){
        this.teRadenSaus = saus;
        this.sausPuntjes = new StringBuilder(".".repeat(saus.length()));
        aantalVerkeerdePogingen = 0;
        aantalPogingen = 0;
    }

    public boolean isGewonnen(){
        return this.sausPuntjes.indexOf(".") == -1;
    }

    public boolean isVerloren(){
        return aantalVerkeerdePogingen == MAXVERKEERDEPOGINGEN;
    }

    public void gok(char letter){
        int letterIndex = teRadenSaus.indexOf(letter);
        if (letterIndex == -1){
            aantalVerkeerdePogingen++;
        } else {
            do {
                sausPuntjes.setCharAt(letterIndex,letter);
                letterIndex = teRadenSaus.indexOf(letter,letterIndex+1);
            }while (letterIndex != -1);
        }
    }

    public StringBuilder getSausPuntjes() {
        return sausPuntjes;
    }

    public String getTeRadenSaus() {
        return teRadenSaus;
    }

    public int getAantalPogingen() {
        return aantalPogingen;
    }

    public int getAantalVerkeerdePogingen() {
        return aantalVerkeerdePogingen;
    }
}
