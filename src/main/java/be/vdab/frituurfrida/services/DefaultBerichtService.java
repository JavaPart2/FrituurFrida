package be.vdab.frituurfrida.services;

import be.vdab.frituurfrida.domain.GastenboekBericht;
import be.vdab.frituurfrida.domain.Snack;
import be.vdab.frituurfrida.exceptions.GastenboekLeegException;
import be.vdab.frituurfrida.repositories.GastenBoekRepository;
import be.vdab.frituurfrida.repositories.SnackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultBerichtService implements GastenBoekService{
    @Autowired
    private GastenBoekRepository repository;

    @Override
    public Optional<GastenboekBericht> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<GastenboekBericht> findAll() {
        try {
            return repository.findAll();
        }catch (GastenboekLeegException e){
            e.toonboodschap(e.getMessage());
            return null;
        }
    }

    @Override
    public void update(GastenboekBericht gastenboekBericht) {
        try {
            repository.update(gastenboekBericht);
        } catch (GastenboekLeegException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(GastenboekBericht gastenboekBericht) {
        repository.delete(gastenboekBericht);
    }

    @Override
    public long insert(GastenboekBericht gastenboekBericht) {
        return repository.insert(gastenboekBericht);
    }

    @Override
    public void deleteIds(long[] ids) {
        for (int i = 0; i < ids.length; i++) {
            findById(ids[i]).ifPresent(gastenboekBericht -> repository.delete(gastenboekBericht));
        }
    }

    @Override
    public void updateIds(long[] ids) {
        for (int i = 0; i < ids.length; i++) {
            findById(ids[i]).ifPresent(this::update);
        }
    }
}
