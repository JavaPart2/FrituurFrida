package be.vdab.frituurfrida.services;

import be.vdab.frituurfrida.domain.Snack;
import be.vdab.frituurfrida.repositories.SnackRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultSnackService implements SnackService{
    private SnackRepository repository;
    @Override
    public Optional<Snack> read(long id) {
        return repository.findById(id);
    }

    @Override
    public void update(Snack snack) {
        repository.update(snack);
    }

    @Override
    public List<Snack> findByBeginName(String beginName) {
        return repository.findByBeginName(beginName);
    }
}
