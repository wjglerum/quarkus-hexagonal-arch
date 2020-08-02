package nl.wjglerum.infra.external;

import nl.wjglerum.domain.CountriesService;
import nl.wjglerum.domain.Country;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class HTTPCountriesService implements CountriesService {

    private final CountriesClient client;

    @Inject
    public HTTPCountriesService(@RestClient CountriesClient client) {
        this.client = client;
    }

    @Override
    public Set<Country> getByName(String name) {
        return client.getByName(name).stream().map(JSONCountry::toDomain).collect(Collectors.toSet());
    }
}
