package be.vdab.frituurfrida.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.Random;

@Component
@SessionScope
public class Game implements Serializable {
    private static final int AANTALDEUREN = 7;
    private Deur[] deuren = new Deur[AANTALDEUREN];

    public Game() {
        int posFriet = new Random().nextInt(AANTALDEUREN);
        for (int i = 0 ; i <= AANTALDEUREN; i++){
            if (i == posFriet){
                Deur deur = new Deur(i,true);
            }else {
                Deur deur = new Deur(i,false);
            }
        }
    }

    public Deur[] getDeuren() {
        return deuren;
    }

    public void setDeuren(Deur[] deuren) {
        this.deuren = deuren;
    }

    public void openDeur(int ind){
        deuren[ind].setOpen(true);
    }

    public Game nieuwSpel(){
        return new Game();
    }
}
