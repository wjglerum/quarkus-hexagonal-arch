package nl.wjglerum.infra.web;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.FixedHostPortGenericContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@Testcontainers
@QuarkusTest
class CountriesResourceTest {

    private static String countryJSON;
    private static final int PORT = 8091;

    @Container
    GenericContainer<?> wiremock = new FixedHostPortGenericContainer<>("rodolpheche/wiremock")
            .withClasspathResourceMapping("countries-api", "/home/wiremock", BindMode.READ_ONLY)
            .withFixedExposedPort(PORT, 8080)
            .waitingFor(Wait.forHttp("/__admin").forStatusCode(200));


    @BeforeAll
    static void start() {
        CountryView country = new CountryView();
        country.name = "Netherlands";
        country.code = "NL";
        country.capital = "Amsterdam";

        try (Jsonb jsonb = JsonbBuilder.create()) {
            countryJSON = jsonb.toJson(country);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.setProperty("nl.wjglerum.country-api/mp-rest/url", "http://localhost:" + PORT);
    }

    @Test
    @DisplayName("Should return the correct country on query")
    void testCountryEndpoint() {
        given().when()
                .get("/country/name/netherlands")
                .then()
                .statusCode(200)
                .body(containsString(countryJSON));
    }

    @Test
    @DisplayName("Should return HTTP 201 when posting a new country")
    void testCountryPostEndpoint() {
        given().when()
                .header("Content-Type", "application/json")
                .body(countryJSON)
                .post("/country/name")
                .then()
                .statusCode(201);
    }
}
