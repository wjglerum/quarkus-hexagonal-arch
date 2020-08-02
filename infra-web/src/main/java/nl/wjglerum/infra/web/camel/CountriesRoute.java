package nl.wjglerum.infra.web.camel;

import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Duration;

@ApplicationScoped
public class CountriesRoute extends RouteBuilder {

    private static final String DIRECT_ROUTE = "direct:CountriesProcessor";

    @ConfigProperty(name = "nl.wjglerum.country-route.interval")
    String interval;

    @ConfigProperty(name = "nl.wjglerum.country-route.delay")
    String delay;

    @Inject
    CountriesProcessor processor;

    @Override
    public void configure() {
        Duration retryDelay = Duration.parse(delay);

        errorHandler(deadLetterChannel(DIRECT_ROUTE)
                .maximumRedeliveries(-1)
                .log("Retrying CountriesProcessor")
                .redeliveryDelay(retryDelay.toMillis()));

        from(DIRECT_ROUTE).process(processor);

        from("quartz://nl.wjglerum/countries?cron=" + interval)
                .id(CountriesRoute.class.getName())
                .log("Fetching countries")
                .setProperty("name", () -> "Netherlands")
                .to(DIRECT_ROUTE);
    }
}
