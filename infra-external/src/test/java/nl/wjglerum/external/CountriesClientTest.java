package nl.wjglerum.external;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import nl.wjglerum.infra.external.CountriesClient;
import nl.wjglerum.infra.external.JSONCountry;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.junit.jupiter.api.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;

class CountriesClientTest {

    private static WireMockServer server;
    private static CountriesClient client;

    @BeforeAll
    static void start() throws MalformedURLException {
        WireMockConfiguration configuration = WireMockConfiguration.options()
                .dynamicPort()
                .usingFilesUnderClasspath("countries-api");
        server = new WireMockServer(configuration);
        server.start();
        client = RestClientBuilder.newBuilder()
                .baseUrl(new URL(server.baseUrl()))
                .build(CountriesClient.class);
    }

    @AfterAll
    static void stop() {
        server.stop();
    }

    @Test
    @DisplayName("Should find and return The Netherlands for query \"netherlands\"")
    void shouldReturnNL() {
        Set<JSONCountry> result = client.getByName("netherlands");
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Netherlands", new ArrayList<>(result).get(0).name);
    }
}
