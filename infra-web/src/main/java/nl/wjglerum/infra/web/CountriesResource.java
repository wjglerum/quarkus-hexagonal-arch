package nl.wjglerum.infra.web;

import nl.wjglerum.services.FetchCountries;
import nl.wjglerum.services.StoreCountries;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Path("/country")
public class CountriesResource {

    private final FetchCountries fetchCountries;
    private final StoreCountries storeCountries;

    @Inject
    public CountriesResource(FetchCountries fetchCountries, StoreCountries storeCountries) {
        this.fetchCountries = fetchCountries;
        this.storeCountries = storeCountries;
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CountryView> name(@PathParam("name") String name) {
        return fetchCountries.getByName(name)
                .stream()
                .map(CountryView::fromDomain)
                .collect(Collectors.toList());
    }

    @POST
    @Path("/name")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save() {
        storeCountries.persist(Collections.emptyList());
        return Response.status(201).build();
    }
}
