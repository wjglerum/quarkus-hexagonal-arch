package nl.wjglerum.services;

import nl.wjglerum.domain.CountriesRepository;
import nl.wjglerum.domain.Country;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class StoreCountriesImpl implements StoreCountries {

    private final CountriesRepository repository;

    @Inject
    public StoreCountriesImpl(CountriesRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Country> all() {
        return repository.all();
    }

    @Override
    public void persist(List<Country> countries) {
        repository.persist(countries);
    }
}
