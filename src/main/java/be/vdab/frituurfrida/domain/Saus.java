package be.vdab.frituurfrida.domain;

public class Saus {
    private final int nummer;
    private final String naam;
    private final String[] ingredienten;

    public Saus(int nummer, String naam, String[] ingredienten) {
        this.nummer = nummer;
        this.naam = naam;
        this.ingredienten = ingredienten;
    }

    public int getNummer() {
        return nummer;
    }

    public String getNaam() {
        return naam;
    }

    public String[] getIngredienten() {
        return ingredienten;
    }
}
