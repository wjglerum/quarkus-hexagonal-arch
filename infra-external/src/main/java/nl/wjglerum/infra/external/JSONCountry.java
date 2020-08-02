package nl.wjglerum.infra.external;

import nl.wjglerum.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class JSONCountry {

    public String name;
    public String alpha2Code;
    public String capital;
    public List<JSONCurrency> currencies;

    public static class JSONCurrency {
        public String code;
        public String name;
        public String symbol;

        public static Currency toDomain(JSONCurrency json) {
            return ImmutableCurrency.builder()
                    .code(json.code)
                    .name(json.name)
                    .symbol(json.symbol)
                    .build();
        }
    }

    public static Country toDomain(JSONCountry json) {
        City capital = ImmutableCity.of(json.capital);
        List<Currency> currencies = json.currencies.stream()
                .map(JSONCurrency::toDomain)
                .collect(Collectors.toList());
        return ImmutableCountry.builder()
                .name(json.name)
                .code(json.alpha2Code)
                .capital(capital)
                .addAllCurrencies(currencies)
                .build();
    }
}
