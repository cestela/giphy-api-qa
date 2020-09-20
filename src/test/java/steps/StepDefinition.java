package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.domain.GIF;
import org.example.domain.Images;
import org.hamcrest.Matcher;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static resources.Assertions.*;

public class StepDefinition {
    RequestSpecification requestSpecification;
    Response response;
    List<GIF> responseGIFList;
    JsonPath jsonPath;
    static GIF firstElement;
    public static int minimumSize;


    @Given("a set of query params")
    public void a_set_of_query_params(DataTable dataTable) throws FileNotFoundException {
        List <Map< String, String >> list = dataTable.asMaps(String.class, String.class);
        Map<String, String> map = list.get(0);
        Set<Map.Entry<String, String>> entries = map.entrySet();
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri("http://api.giphy.com/v1");
        entries.forEach(entry -> {
            requestSpecBuilder.addQueryParam(entry.getKey(), entry.getValue());
        });

        requestSpecification = requestSpecBuilder.build();

    }

    @When("user sends GET operation to Giphy search API")
    public void user_sends_get_operation_to_giphy_search_api() {

        ValidatableResponse validatableResponse = given()
                .spec(requestSpecification)
                .get("/gifs/search")
                .then();

        response = validatableResponse
                .extract()
                .response();
        responseGIFList = validatableResponse
                        .extract()
                .jsonPath().getList("data", GIF.class);

        jsonPath = new JsonPath(response.asString());
    }

    @Then("response status is {int}")
    public void responseStatusIs(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("response {string} is {int}")
    public void response_is(String element, Integer value) {
        Integer elementValue = jsonPath.getInt(element);
        assertEquals(elementValue, value);

    }

    @Then("response {string} is {string} {int}")
    public void response_is_greater_than(String element, String comparator, Integer value) {
        Matcher<Integer> matcher = null;
        if(comparator.equals("greater than")){
            matcher = greaterThan(value);
        }else if(comparator.equals("equal to")){
            matcher = lessThanOrEqualTo(value);
        }

        assertThat(jsonPath.getInt(element), Objects.requireNonNull(matcher));
    }

    @Then("response {string} is {string}")
    public void response_is(String element, String value) {
        assertEquals(jsonPath.getString(element).toString(), value);

    }

    @Then("response {string} equals data size")
    public void response_equals_data_size(String path) {
        long size = jsonPath.getLong(path);
        assertEquals(size, responseGIFList.size());
    }

    @And("response gifs ratings are not")
    public void response_gifs_ratings_are_not(DataTable dataTable) {
        List<List<String>> ratingTable = dataTable.asLists(String.class);
        List<String> ratingList = ratingTable.get(0);
        ratingList.forEach(rating -> responseGIFList.forEach(gif ->
                assertThat(gif.getRating(), not(containsString(rating)))));
    }

    @And("response {string} is the 5th element of the list without offset")
    public void responseIsThe5ThElementOfTheListWithoutOffset(String element) {
        String elementValue = jsonPath.getString(element);
        assertEquals(elementValue, firstElement.getId());


    }

    @And("GIFs urls are not broken")
    public void gifsUrlsAreNotBroken() {
        responseGIFList.forEach(gif -> checkGifsUrlsRedirect(gif));
    }

    @And("GIFs unshortened urls contain gifs GIPHY Id")
    public void imagesUnshortenedUrlsContainGifsGIPHYId() {
        responseGIFList.forEach(gif -> checkGifsUrlsIds(gif));
    }

    @And("response username matches user.username")
    public void responseUsernameMatchesUserUsername() {
        responseGIFList.stream()
                .filter(gif -> !gif.getUsername().isEmpty())
                .forEach( gif -> assertEquals(gif.getUsername(), gif.getUser().getUsername()));

    }

    @And("response user profile url is not broken")
    public void responseUserProfileUrlIsNotBroken() {
        responseGIFList.stream()
                .filter(gif -> !gif.getUsername().isEmpty())
                .forEach(gif -> given().get(gif.getUser().getProfile_url()).then().statusCode(200));
    }

    @And("response GIFs import datetime are before than system's date")
    public void responseGIFsImportDatetimeAreBeforeThanSystemSDate() {
        Date now = new Date(System.currentTimeMillis());
        responseGIFList.forEach(gif -> {
            Date importDate = new Date();
            try {
                importDate = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss").parse(gif.getImport_datetime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            assertThat(importDate, lessThan(now));
        });
    }

    @And("Images urls are not broken")
    public void imagesUrlsAreNotBroken() {
        responseGIFList.forEach(gif -> checkImagesUrlsRedirect(gif.getImages()));
    }

    @And("Images width, height and sizes are greater than {int}")
    public void imagesWidthHeightAndSizesAreGreaterThan(int size) {
        minimumSize = size;
        responseGIFList.forEach(gif -> {
            Images images = gif.getImages();
            assertImagesSizesOriginal(images);
            asssertImagesSizesDownsized(images);
            assertImagesSizesFixedHeight(images);
            assertImagesSizesFixedWidth(images);
            asssertMp4Size(images.getLooping().getMp4_size());
            assertImagesSizesPreview(images);
        });
    }







}
