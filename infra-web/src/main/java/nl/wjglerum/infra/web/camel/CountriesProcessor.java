package nl.wjglerum.infra.web.camel;

import nl.wjglerum.domain.Country;
import nl.wjglerum.services.FetchCountries;
import nl.wjglerum.services.StoreCountries;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Set;

@ApplicationScoped
public class CountriesProcessor implements Processor {

    private final FetchCountries fetchCountries;
    private final StoreCountries storeCountries;

    public CountriesProcessor(FetchCountries fetchCountries, StoreCountries storeCountries) {
        this.fetchCountries = fetchCountries;
        this.storeCountries = storeCountries;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        String name = exchange.getProperty("name").toString();
        Set<Country> countries = fetchCountries.getByName(name);
        storeCountries.persist(new ArrayList<>(countries));
    }
}
