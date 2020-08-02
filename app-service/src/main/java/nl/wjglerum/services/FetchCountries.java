package nl.wjglerum.services;

import nl.wjglerum.domain.Country;

import java.util.Set;

public interface FetchCountries {

    Set<Country> getByName(String name);
}
