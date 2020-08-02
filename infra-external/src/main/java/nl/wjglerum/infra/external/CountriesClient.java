package nl.wjglerum.infra.external;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Set;

@Path("/rest/v2")
@RegisterRestClient(configKey = "nl.wjglerum.country-api")
public interface CountriesClient {

    @GET
    @Path("/name/{name}")
    @Produces("application/json")
    Set<JSONCountry> getByName(@PathParam("name") String name);
}
