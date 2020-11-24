package be.vdab.frituurfrida.repositories;

import be.vdab.frituurfrida.domain.Snack;

import java.util.List;
import java.util.Optional;

public interface SnackRepository {
    public Optional<Snack> findById(long id);
    public void update(Snack snack);
    List<Snack> findByBeginName(String beginNaam);
}
