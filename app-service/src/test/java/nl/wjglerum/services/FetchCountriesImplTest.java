package nl.wjglerum.services;

import nl.wjglerum.domain.CountriesService;
import nl.wjglerum.domain.Country;
import nl.wjglerum.domain.ImmutableCity;
import nl.wjglerum.domain.ImmutableCountry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FetchCountriesImplTest {

    private static FetchCountries fetchCountries;
    private static final Country netherlands = ImmutableCountry.builder()
            .name("Netherlands")
            .code("NL")
            .capital(ImmutableCity.of("Amsterdam"))
            .build();

    @BeforeAll
    static void start() {
        CountriesService service = mock(CountriesService.class);
        when(service.getByName("netherlands")).thenReturn(Set.of(netherlands));
        fetchCountries = new FetchCountriesImpl(service);
    }

    @Test
    @DisplayName("Should find Amsterdam as capityal city of The Netherlands")
    void shouldReturnCountryOnQuery() {
        Set<Country> result = fetchCountries.getByName("netherlands");
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Amsterdam", new ArrayList<>(result).get(0).capital().name());
    }
}
