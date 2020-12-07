package be.vdab.frituurfrida.services;

import be.vdab.frituurfrida.domain.GastenboekBericht;
import be.vdab.frituurfrida.domain.Snack;
import be.vdab.frituurfrida.exceptions.GastenboekLeegException;

import java.util.List;
import java.util.Optional;

public interface GastenBoekService {
    Optional<GastenboekBericht> findById(long id);
    List<GastenboekBericht> findAll() throws GastenboekLeegException;
    void update(GastenboekBericht gastenboekBericht);
    void delete(GastenboekBericht gastenboekBericht);
    long insert(GastenboekBericht gastenboekBericht);

    void deleteIds(long[] ids);

    void updateIds(long[] ids);
}
