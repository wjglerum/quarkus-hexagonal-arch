package nl.wjglerum.infra.web;

import nl.wjglerum.infra.web.camel.CountriesProcessor;
import nl.wjglerum.services.FetchCountries;
import nl.wjglerum.services.StoreCountries;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class CountriesCamelTest extends CamelTestSupport {

    private static FetchCountries fetchCountries;
    private static StoreCountries storeCountries;

    @BeforeAll
    static void start() {
        fetchCountries = mock(FetchCountries.class);
        storeCountries = mock(StoreCountries.class);
    }

    @Test
    void testMock() throws InterruptedException {
        MockEndpoint mockEndpoint = getMockEndpoint("mock:postBean");
        template.sendBody("direct:start", "hello mock");
        mockEndpoint.assertIsSatisfied();
    }

    @Override
    protected RoutesBuilder createRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .setProperty("name", constant("netherlands"))
                        .process(new CountriesProcessor(fetchCountries, storeCountries))
                        .to("mock:postBean");
            }
        };
    }
}
