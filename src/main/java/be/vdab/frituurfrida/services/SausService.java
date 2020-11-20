package be.vdab.frituurfrida.services;

import be.vdab.frituurfrida.domain.Saus;

import java.util.List;
import java.util.Optional;

public interface SausService {
    public List<Saus> findAll();
    public List<Saus> findByNameBeginMet(char letter);
    public Optional<Saus> findById(int id);
}
