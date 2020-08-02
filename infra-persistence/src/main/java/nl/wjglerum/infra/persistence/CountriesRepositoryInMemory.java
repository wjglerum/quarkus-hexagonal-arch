package nl.wjglerum.infra.persistence;

import nl.wjglerum.domain.CountriesRepository;
import nl.wjglerum.domain.Country;

import java.util.ArrayList;
import java.util.List;

public class CountriesRepositoryInMemory implements CountriesRepository {

    private final List<Country> repo = new ArrayList<>();

    @Override
    public List<Country> getByName(String name) {
        return new ArrayList<>(repo);
    }

    @Override
    public List<Country> all() {
        return new ArrayList<>(repo);
    }

    @Override
    public void persist(List<Country> countries) {
        repo.addAll(countries);
    }
}
