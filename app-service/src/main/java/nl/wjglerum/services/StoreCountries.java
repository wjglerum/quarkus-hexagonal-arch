package nl.wjglerum.services;

import nl.wjglerum.domain.Country;

import java.util.List;

public interface StoreCountries {

    List<Country> all();

    void persist(List<Country> countries);
}
