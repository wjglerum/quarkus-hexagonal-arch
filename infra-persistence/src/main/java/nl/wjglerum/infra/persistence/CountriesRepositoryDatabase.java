package nl.wjglerum.infra.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import nl.wjglerum.domain.CountriesRepository;
import nl.wjglerum.domain.Country;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CountriesRepositoryDatabase implements PanacheRepository<CountryRecord>, CountriesRepository {

    @Override
    public List<Country> getByName(String name) {
        return find("name", name).stream().map(CountryRecord::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Country> all() {
        return findAll().stream().map(CountryRecord::toDomain).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void persist(List<Country> countries) {
        persist(countries.stream().map(CountryRecord::fromDomain).collect(Collectors.toList()));
    }
}
