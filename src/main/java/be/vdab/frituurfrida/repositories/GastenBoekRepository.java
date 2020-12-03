package be.vdab.frituurfrida.repositories;

import be.vdab.frituurfrida.domain.GastenboekBericht;
import be.vdab.frituurfrida.domain.Snack;
import be.vdab.frituurfrida.exceptions.GastenboekLeegException;

import java.util.List;
import java.util.Optional;

public interface GastenBoekRepository {
    public Optional<GastenboekBericht> findById(long id);
    public List<GastenboekBericht> findAll() throws GastenboekLeegException;
    public void update(GastenboekBericht gastenboekBericht) throws GastenboekLeegException;
    public void delete(GastenboekBericht gastenboekBericht);
    public long insert(GastenboekBericht gastenboekBericht);
}
