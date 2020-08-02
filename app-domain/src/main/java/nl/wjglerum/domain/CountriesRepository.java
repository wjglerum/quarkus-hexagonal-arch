package nl.wjglerum.domain;

import java.util.List;

public interface CountriesRepository {

    List<Country> getByName(String name);

    List<Country> all();

    void persist(List<Country> countries);
}
