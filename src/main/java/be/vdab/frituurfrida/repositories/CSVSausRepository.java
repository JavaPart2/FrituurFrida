package be.vdab.frituurfrida.repositories;

import be.vdab.frituurfrida.domain.Saus;
import be.vdab.frituurfrida.exceptions.SausRepositoryException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Qualifier("CSV")
public class CSVSausRepository implements SausRepository{

    private final Path PAD;

    public CSVSausRepository(@Value("${csv}") Path pad) {
        PAD = pad;
    }

    @Override
    public List<Saus> findAll() throws SausRepositoryException {
        List<Saus> sauzen = new ArrayList<>();
        try {
            return Files.lines(PAD)
                    .filter(regel -> !regel.isEmpty())
                    .map(regel -> maakSaus(regel))
                    .collect(Collectors.toList());
        } catch (IOException ex){
            throw new SausRepositoryException("Fout bij lezen " + PAD);
        }
    }

    private Saus maakSaus(String regel) throws SausRepositoryException {
        var onderdelen = regel.split(",");
        if (onderdelen.length < 2){
            throw new SausRepositoryException(PAD + ":" + regel + " bevat minder dan 2 onderdelen");
        }
        try {
            var ingredienten =
                    Arrays.copyOfRange(onderdelen, 2, onderdelen.length);
            return new Saus(Integer.parseInt(onderdelen[0]), onderdelen[1], ingredienten);
        } catch (NumberFormatException ex){
            throw new SausRepositoryException(PAD + ":" + regel + " bevat verkeerde id");
        }
    }
}
