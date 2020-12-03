package be.vdab.frituurfrida.domain;

import java.time.LocalDate;

public class GastenboekBericht {
    private final long id;
    private String naam;
    private String bericht;
    private LocalDate datum;

    public GastenboekBericht(long id, String naam, String bericht) {
        this.id = id;
        this.naam = naam;
        this.bericht = bericht;
        this.datum = LocalDate.now();
    }

    public GastenboekBericht(long id, String naam, String bericht, LocalDate datum) {
        this.id = id;
        this.naam = naam;
        this.bericht = bericht;
        this.datum = datum;
    }

    public String getNaam() {
        return naam;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBericht() {
        return bericht;
    }

    public void setBericht(String bericht) {
        this.bericht = bericht;
    }
}
