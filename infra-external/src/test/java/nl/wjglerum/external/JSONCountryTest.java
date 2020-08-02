package nl.wjglerum.external;

import nl.wjglerum.domain.*;
import nl.wjglerum.infra.external.JSONCountry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class JSONCountryTest {

    @Test
    @DisplayName("Should correctly convert to domain objects")
    void should() {
        JSONCountry.JSONCurrency jsonCurrency = new JSONCountry.JSONCurrency();
        jsonCurrency.code = "EUR";
        jsonCurrency.name = "Euro";
        jsonCurrency.symbol = "€";
        JSONCountry jsonCountry = new JSONCountry();
        jsonCountry.name = "Netherlands";
        jsonCountry.capital = "Amsterdam";
        jsonCountry.alpha2Code = "NL";
        jsonCountry.currencies = List.of(jsonCurrency);

        Currency currency = ImmutableCurrency.builder()
                .code("EUR")
                .name("Euro")
                .symbol("€")
                .build();
        Country country = ImmutableCountry.builder()
                .name("Netherlands")
                .code("NL")
                .capital(ImmutableCity.of("Amsterdam"))
                .addCurrencies(currency)
                .build();

        Assertions.assertEquals(country, JSONCountry.toDomain(jsonCountry));
    }
}
