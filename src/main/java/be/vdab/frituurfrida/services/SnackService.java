package be.vdab.frituurfrida.services;

import be.vdab.frituurfrida.domain.Snack;

import java.util.List;
import java.util.Optional;

public interface SnackService {
    Optional<Snack> read(long id);
    void update(Snack snack);
    List<Snack> findByBeginName(String beginName);

}
