package steps;

import io.cucumber.java.Before;
import org.example.domain.GIF;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Hooks {


    @Before("@CustomOffset")
    public void beforeCustomOffset() throws IOException {
        StepDefinition sd = new StepDefinition();

        List<GIF> gifs = given()
                .baseUri("http://api.giphy.com/v1")
                .queryParams("q", "funny+cat","api_key", "dc6zaTOxFJmzC")
                .get("/gifs/search")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath().getList("data", GIF.class);

        sd.firstElement = gifs.get(4);
        }
 }

