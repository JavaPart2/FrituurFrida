package be.vdab.frituurfrida.sessions;

import java.io.Serializable;

public class Deur implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int index;
    private boolean open;
    private boolean metFriet;

    public Deur(int index, boolean metFriet) {
        this.index = index;
        this.open = false;
        this.metFriet = metFriet;
    }

    public void setMetFriet(boolean metFriet) {
        this.metFriet = metFriet;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isMetFriet() {
        return metFriet;
    }

    public int getIndex() {
        return index;
    }
}
