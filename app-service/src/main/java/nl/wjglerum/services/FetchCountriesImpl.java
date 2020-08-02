package nl.wjglerum.services;

import nl.wjglerum.domain.CountriesService;
import nl.wjglerum.domain.Country;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Set;

@ApplicationScoped
public class FetchCountriesImpl implements FetchCountries {

    private final CountriesService service;

    @Inject
    public FetchCountriesImpl(CountriesService service) {
        this.service = service;
    }

    @Override
    public Set<Country> getByName(String name) {
        return service.getByName(name);
    }
}
