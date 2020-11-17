package be.vdab.frituurfrida.domain;

public class Adres {
    private final String straat;
    private final String huisNr;
    private final Gemeente gemeente;

    public Adres(String straat, String huisNr, Gemeente gemeente) {
        this.straat = straat;
        this.huisNr = huisNr;
        this.gemeente = gemeente;
    }

    public String getStraat() {
        return straat;
    }

    public String getHuisNr() {
        return huisNr;
    }

    public Gemeente getGemeente() {
        return gemeente;
    }
}
