package be.vdab.frituurfrida.forms;

import be.vdab.frituurfrida.domain.GastenboekBericht;

import java.time.LocalDate;

public class BerichtForm extends GastenboekBericht {

    public BerichtForm(String naam, String bericht) {
        super(0, naam, bericht, LocalDate.now());
    }
}
