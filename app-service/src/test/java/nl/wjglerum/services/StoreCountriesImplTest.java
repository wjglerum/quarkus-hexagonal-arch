package nl.wjglerum.services;

import nl.wjglerum.domain.CountriesRepository;
import nl.wjglerum.domain.Country;
import nl.wjglerum.domain.ImmutableCity;
import nl.wjglerum.domain.ImmutableCountry;
import nl.wjglerum.infra.persistence.CountriesRepositoryInMemory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class StoreCountriesImplTest {

    private static StoreCountries storeCountries;
    private static final Country netherlands = ImmutableCountry.builder()
            .name("Netherlands")
            .code("NL")
            .capital(ImmutableCity.of("Amsterdam"))
            .build();

    @BeforeAll
    static void start() {
        CountriesRepository repository = new CountriesRepositoryInMemory();
        storeCountries = new StoreCountriesImpl(repository);
    }

    @Test
    @DisplayName("Should return an empty list")
    void shouldReturnEmptyList() {
        Assertions.assertEquals(0, storeCountries.all().size());
    }

    @Test
    @DisplayName("Should return The Netherlands after saving")
    void shouldReturnCountryAfterSaving() {
        storeCountries.persist(List.of(netherlands));
        Assertions.assertEquals(1, storeCountries.all().size());
        Assertions.assertEquals("Netherlands", storeCountries.all().get(0).name());
    }
}
