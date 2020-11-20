package be.vdab.frituurfrida.repositories;

import be.vdab.frituurfrida.domain.Saus;
import be.vdab.frituurfrida.exceptions.SausRepositoryException;

import java.util.List;

public interface SausRepository {
    public List<Saus> findAll() throws SausRepositoryException;
}
