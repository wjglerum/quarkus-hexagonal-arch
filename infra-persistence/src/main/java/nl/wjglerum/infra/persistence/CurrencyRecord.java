package nl.wjglerum.infra.persistence;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import nl.wjglerum.domain.Currency;
import nl.wjglerum.domain.ImmutableCurrency;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CurrencyRecord extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String code;
    private String symbol;

    public static Currency toDomain(CurrencyRecord record) {
        return ImmutableCurrency.builder()
                .code(record.code)
                .name(record.name)
                .symbol(record.symbol)
                .build();
    }

    public static CurrencyRecord fromDomain(Currency currency) {
        CurrencyRecord record = new CurrencyRecord();
        record.name = currency.name();
        record.code = currency.code();
        record.symbol = currency.symbol();
        return record;
    }
}
