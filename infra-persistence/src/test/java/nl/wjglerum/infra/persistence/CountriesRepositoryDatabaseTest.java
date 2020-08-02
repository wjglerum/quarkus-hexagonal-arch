package nl.wjglerum.infra.persistence;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import nl.wjglerum.domain.Country;
import nl.wjglerum.domain.ImmutableCity;
import nl.wjglerum.domain.ImmutableCountry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

@QuarkusTest
class CountriesRepositoryDatabaseTest {

    @InjectMock
    CountriesRepositoryDatabase repository;

    @Test
    @DisplayName("Mocking CountriesRepositoryDatabase")
    void testPanacheMocking() {
        Mockito.when(repository.all()).thenReturn(Collections.emptyList());
        Assertions.assertEquals(0, repository.count());
    }

    @Test
    @DisplayName("Save and return new country")
    void saveAndReturnNewCountry() {
        Country netherlands = ImmutableCountry.builder()
                .name("Netherlands")
                .code("NL")
                .capital(ImmutableCity.of("Amsterdam"))
                .build();
        Mockito.when(repository.getByName("netherlands")).thenReturn(List.of(netherlands));
        List<Country> result = repository.getByName("netherlands");
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Netherlands", result.get(0).name());
        Assertions.assertEquals("Amsterdam", result.get(0).capital().name());
    }
}
