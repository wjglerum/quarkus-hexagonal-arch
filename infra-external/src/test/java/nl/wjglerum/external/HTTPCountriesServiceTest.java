package nl.wjglerum.external;

import nl.wjglerum.domain.Country;
import nl.wjglerum.infra.external.CountriesClient;
import nl.wjglerum.infra.external.HTTPCountriesService;
import nl.wjglerum.infra.external.JSONCountry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HTTPCountriesServiceTest {

    private static HTTPCountriesService service;

    @BeforeAll
    static void start() {
        CountriesClient client = mock(CountriesClient.class);
        JSONCountry json = new JSONCountry();
        json.name = "Netherlands";
        json.capital = "Amsterdam";
        json.alpha2Code = "NL";
        json.currencies = Collections.emptyList();
        when(client.getByName("netherlands")).thenReturn(Set.of(json));
        service = new HTTPCountriesService(client);
    }

    @Test
    @DisplayName("Should return a correct domain object when querying for country")
    void shouldReturnCorrectDomainObjec() {
        Set<Country> result = service.getByName("netherlands");
        Assertions.assertEquals(1, result.size());
        Country netherlands = new ArrayList<>(result).get(0);
        Assertions.assertEquals("Netherlands", netherlands.name());
        Assertions.assertEquals("Amsterdam", netherlands.capital().name());
    }
}
