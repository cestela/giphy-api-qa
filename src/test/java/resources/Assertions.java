package resources;

import org.example.domain.GIF;
import org.example.domain.ImageSize;
import org.example.domain.Images;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;
import static steps.StepDefinition.minimumSize;

public class Assertions {

    public static void assertImagesSizesPreview(Images images) {
        assertImageHeight(images.getPreview().getHeight());
        assertImageWidth(images.getPreview().getWidth());
        asssertMp4Size(images.getPreview().getMp4_size());
        assertBasicImagesSizes(images.getPreview_gif());
    }

    public static void assertImagesSizesFixedWidth(Images images) {
        assertImagesSizes(images.getFixed_width());
        assertBasicImagesSizes(images.getFixed_width_downsampled());
        asssertWebpSize(images.getFixed_width_downsampled().getWebp_size());
        assertImagesSizes(images.getFixed_width_small());
        assertBasicImagesSizes(images.getFixed_width_small_still());
        assertBasicImagesSizes(images.getFixed_width_still());
    }

    public static void assertImagesSizesFixedHeight(Images images) {
        assertImagesSizes(images.getFixed_height());
        assertBasicImagesSizes(images.getFixed_height_downsampled());
        asssertWebpSize(images.getFixed_height_downsampled().getWebp_size());
        assertImagesSizes(images.getFixed_height_small());
        assertBasicImagesSizes(images.getFixed_height_small_still());
        assertBasicImagesSizes(images.getFixed_height_still());
    }

    public static void asssertImagesSizesDownsized(Images images) {
        assertBasicImagesSizes(images.getDownsized());
        assertBasicImagesSizes(images.getDownsized_large());
        assertBasicImagesSizes(images.getDownsized_medium());
        assertBasicImagesSizes(images.getDownsized_medium());
        assertImageHeight(images.getDownsized_small().getHeight());
        assertImageWidth(images.getDownsized_small().getWidth());
        asssertMp4Size(images.getDownsized_small().getMp4_size());
        assertBasicImagesSizes(images.getDownsized_still());
    }

    public static void assertImagesSizesOriginal(Images images) {
        assertImagesSizes(images.getOriginal());
        assertBasicImagesSizes(images.getOriginal_still());
    }

    public static void assertImagesSizes(ImageSize imageSize){
        assertBasicImagesSizes(imageSize);
        asssertMp4Size(imageSize.getMp4_size());
        assertThat(Integer.valueOf(imageSize.getWebp_size()), greaterThan(minimumSize));


    }

    public static void checkImagesUrlsRedirect(Images images) {
        checkDownsized(images);
        checkFixedHeight(images);
        checkOriginal(images);
        checkFixedWidth(images);
        given().get(images.getPreview().getMp4()).then().statusCode(200);
        given().get(images.getPreview_gif().getUrl()).then().statusCode(200);
        given().get(images.getLooping().getMp4()).then().statusCode(200);
    }

    public static void checkFixedWidth(Images images) {
        given().get(images.getFixed_width().getUrl()).then().statusCode(200);
        given().get(images.getFixed_width().getMp4()).then().statusCode(200);
        given().get(images.getFixed_width().getWebp()).then().statusCode(200);
        given().get(images.getFixed_width_downsampled().getUrl()).then().statusCode(200);
        given().get(images.getFixed_width_downsampled().getWebp()).then().statusCode(200);
        given().get(images.getFixed_width_small().getUrl()).then().statusCode(200);
        given().get(images.getFixed_width_small().getMp4()).then().statusCode(200);
        given().get(images.getFixed_width_small().getWebp()).then().statusCode(200);
        given().get(images.getFixed_width_small_still().getUrl()).then().statusCode(200);
        given().get(images.getFixed_width_still().getUrl()).then().statusCode(200);

    }

    public static void checkOriginal(Images images) {
        given().get(images.getOriginal().getUrl()).then().statusCode(200);
        given().get(images.getOriginal().getMp4()).then().statusCode(200);
        given().get(images.getOriginal().getWebp()).then().statusCode(200);
        given().get(images.getOriginal_still().getUrl()).then().statusCode(200);
        given().get(images.getOriginal_still().getUrl()).then().statusCode(200);
    }

    public static void checkFixedHeight(Images images) {
        given().get(images.getFixed_height().getUrl()).then().statusCode(200);
        given().get(images.getFixed_height().getMp4()).then().statusCode(200);
        given().get(images.getFixed_height().getWebp()).then().statusCode(200);
        given().get(images.getFixed_height_downsampled().getUrl()).then().statusCode(200);
        given().get(images.getFixed_height_downsampled().getWebp()).then().statusCode(200);
        given().get(images.getFixed_height_small().getUrl()).then().statusCode(200);
        given().get(images.getFixed_height_small().getMp4()).then().statusCode(200);
        given().get(images.getFixed_height_small().getWebp()).then().statusCode(200);
        given().get(images.getFixed_height_small_still().getUrl()).then().statusCode(200);
        given().get(images.getFixed_height_still().getUrl()).then().statusCode(200);
    }

    public static void checkDownsized(Images images) {
        given().get(images.getDownsized().getUrl()).then().statusCode(200);
        given().get(images.getDownsized_large().getUrl()).then().statusCode(200);
        given().get(images.getDownsized_medium().getUrl()).then().statusCode(200);
        given().get(images.getDownsized_small().getMp4()).then().statusCode(200);
        given().get(images.getDownsized_still().getUrl()).then().statusCode(200);

    }

    public static void checkGifsUrlsRedirect(GIF gif) {
        given().get(gif.getUrl()).then().statusCode(200);
        given().get(gif.getBitly_url()).then().statusCode(200);
        given().get(gif.getEmbed_url()).then().statusCode(200);
        given().get(gif.getSource_post_url()).then().statusCode(200);
        given().get(gif.getSource()).then().statusCode(200);
        given().get(gif.getBitly_url()).then().statusCode(200);
    }

    public static void checkGifsUrlsIds(GIF gif){
        assertThat(gif.getUrl(), containsString(gif.getId()));
        assertThat(gif.getEmbed_url(), containsString(gif.getId()));

    }

    public static void assertBasicImagesSizes(ImageSize imageSize) {
        assertImageHeight(imageSize.getHeight());
        assertImageWidth(imageSize.getWidth());
        assertThat(Integer.valueOf(imageSize.getSize()), greaterThan(minimumSize));
    }

    public static void assertImageHeight(String height){
        assertThat(Integer.valueOf(height), greaterThan(minimumSize));

    }

    public static void assertImageWidth(String width){
        assertThat(Integer.valueOf(width), greaterThan(minimumSize));
    }

    public static void asssertMp4Size(String mp4size){
        assertThat(Integer.valueOf(mp4size), greaterThan(minimumSize));
    }

    public static void asssertWebpSize(String webpsize){
        assertThat(Integer.valueOf(webpsize), greaterThan(minimumSize));
    }
}
