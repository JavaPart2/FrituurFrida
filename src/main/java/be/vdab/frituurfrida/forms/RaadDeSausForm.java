package be.vdab.frituurfrida.forms;

import javax.validation.constraints.NotNull;

public class RaadDeSausForm {
    @NotNull
    private final Character gokletter;

    public RaadDeSausForm(Character gokletter) {
        this.gokletter = gokletter;
    }

    public Character getGokletter() {
        return gokletter;
    }
}
