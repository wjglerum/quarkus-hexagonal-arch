package nl.wjglerum.infra.persistence;

import nl.wjglerum.domain.Country;
import nl.wjglerum.domain.ImmutableCity;
import nl.wjglerum.domain.ImmutableCountry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class CountriesRepositoryInMemoryTest {

    private static final CountriesRepositoryInMemory repository = new CountriesRepositoryInMemory();
    private static final Country country = ImmutableCountry.builder()
            .name("Netherlands")
            .code("NL")
            .capital(ImmutableCity.of("Amsterdam"))
            .build();

    @Test
    @DisplayName("Should return an empty list")
    void shouldBeEmpty() {
        Assertions.assertEquals(0, repository.all().size());
    }

    @Test
    @DisplayName("Should return the country after persisting")
    void shouldReturnCountryAfterPersisting() {
        repository.persist(List.of(country));
        Assertions.assertEquals(1, repository.all().size());
        Assertions.assertEquals(country, repository.getByName("netherlands").get(0));
    }
}
