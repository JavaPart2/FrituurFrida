package be.vdab.frituurfrida.forms;

public class BerichtForm {
    private final String naam;
    private final String bericht;

    public BerichtForm(String naam, String bericht) {
        this.naam = naam;
        this.bericht = bericht;
    }

    public String getNaam() {
        return naam;
    }

    public String getBericht() {
        return bericht;
    }
}
