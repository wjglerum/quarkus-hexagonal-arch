package nl.wjglerum.infra.persistence;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import nl.wjglerum.domain.Country;
import nl.wjglerum.domain.ImmutableCity;
import nl.wjglerum.domain.ImmutableCountry;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class CountryRecord extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String code;
    private String capital;
    @OneToMany
    List<CurrencyRecord> currencies;

    public static Country toDomain(CountryRecord record) {
        return ImmutableCountry.builder()
                .name(record.name)
                .code(record.code)
                .capital(ImmutableCity.of(record.capital))
                .addAllCurrencies(record.currencies.stream().map(CurrencyRecord::toDomain).collect(Collectors.toList()))
                .build();
    }

    public static CountryRecord fromDomain(Country country) {
        CountryRecord record = new CountryRecord();
        record.name = country.name();
        record.code = country.code();
        record.capital = country.capital().name();
        record.currencies = country.currencies().stream().map(CurrencyRecord::fromDomain).collect(Collectors.toList());
        return record;
    }
}
