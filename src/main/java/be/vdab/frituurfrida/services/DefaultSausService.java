package be.vdab.frituurfrida.services;

import be.vdab.frituurfrida.domain.Saus;
import be.vdab.frituurfrida.repositories.SausRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultSausService implements SausService{
    @Autowired
    @Qualifier("Properties")
    private final SausRepository sausRepository;

    public DefaultSausService(@Qualifier("Properties") SausRepository sausRepository) {
        this.sausRepository = sausRepository;
    }

    @Override
    public List<Saus> findAll() {
        return sausRepository.findAll();
    }

    @Override
    public List<Saus> findByNameBeginMet(char letter) {
        return sausRepository.findAll().stream()
                .filter(saus -> saus.getNaam().toLowerCase().charAt(0) == letter)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Saus> findById(int id) {
        return sausRepository.findAll().stream().filter(saus -> saus.getNummer() == id)
                .findFirst();
    }
}
