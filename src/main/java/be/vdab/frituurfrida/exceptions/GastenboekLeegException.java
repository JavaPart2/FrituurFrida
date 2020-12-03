package be.vdab.frituurfrida.exceptions;

import java.sql.SQLException;

public class GastenboekLeegException extends SQLException {
    private String boodschap;

    public void toonboodschap(String m){
        System.out.println(m);
    }
}
