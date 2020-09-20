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
        if(images.getPreview() != null){
            assertBasicImagesHeightAndWidth(images.getPreview());
            if(images.getPreview().getMp4_size()!= null){
                asssertMp4Size(images.getPreview().getMp4_size());
            }
        }
    }

    public static void assertImagesSizesFixedWidth(Images images) {
        if(images.getFixed_width() !=  null){
            assertImagesSizes(images.getFixed_width());
        }

        if(images.getFixed_width_downsampled() != null) {
            assertBasicImagesHeightAndWidth(images.getFixed_width_downsampled());
            asssertWebpSize(images.getFixed_width_downsampled().getWebp_size());
        }

        if(images.getFixed_width_small() != null){
            assertImagesSizes(images.getFixed_width_small());
        }

        if(images.getFixed_width_small_still() != null){
            assertBasicImagesHeightAndWidth(images.getFixed_width_small_still());
        }

        if(images.getFixed_width_still() != null){
            assertBasicImagesHeightAndWidth(images.getFixed_width_still());
        }
    }

    public static void assertImagesSizesFixedHeight(Images images) {
        if(images.getFixed_height() != null){
            assertImagesSizes(images.getFixed_height());
        }

        if(images.getFixed_height_downsampled() != null){
            assertBasicImagesHeightAndWidth(images.getFixed_height_downsampled());
            asssertWebpSize(images.getFixed_height_downsampled().getWebp_size());
        }

        if(images.getFixed_height_small() != null){
            assertImagesSizes(images.getFixed_height_small());

        }

        if(images.getFixed_height_small_still() != null){
            assertBasicImagesHeightAndWidth(images.getFixed_height_small_still());
        }

        if(images.getFixed_height_still() != null) {
            assertBasicImagesHeightAndWidth(images.getFixed_height_still());
        }
    }

    public static void asssertImagesSizesDownsized(Images images) {
        if(images.getDownsized() != null){
            assertBasicImagesSizes(images.getDownsized());
        }
        if(images.getDownsized_large() != null){
            assertBasicImagesSizes(images.getDownsized_large());
        }
        if(images.getDownsized_medium() != null){
            assertBasicImagesSizes(images.getDownsized_medium());
        }

        if(images.getDownsized_small() != null){
            assertBasicImagesHeightAndWidth(images.getDownsized_small());
            asssertMp4Size(images.getDownsized_small().getMp4_size());
        }

        if(images.getDownsized_still() != null){
            assertBasicImagesHeightAndWidth(images.getDownsized_still());

        }


    }

    public static void assertImagesSizesOriginal(Images images) {
        if(images.getOriginal() != null){
            assertImagesSizes(images.getOriginal());
        }
        if(images.getOriginal_still() != null){
            assertBasicImagesHeightAndWidth(images.getOriginal_still());
        }
    }

    public static void assertImagesSizes(ImageSize imageSize){

        assertBasicImagesSizes(imageSize);
        if(imageSize.getMp4() != null){
            asssertMp4Size(imageSize.getMp4_size());
        }
        if(imageSize.getWebp_size() != null){
            asssertWebpSize(imageSize.getWebp_size());
        }


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

    public static void assertImagesSizesPreviewGif(Images images) {
        if(images.getPreview_gif() != null){
            assertBasicImagesHeightAndWidth(images.getPreview_gif());
        }
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

    public static void assertBasicImagesHeightAndWidth(ImageSize imageSize){
        if(imageSize.getHeight() != null){
            assertImageHeight(imageSize.getHeight());
        }

        if(imageSize.getWidth() != null){
            assertImageWidth(imageSize.getWidth());
        }

    }

    public static void assertBasicImagesSizes(ImageSize imageSize) {
        assertBasicImagesHeightAndWidth(imageSize);
        if(imageSize.getSize()!= null){
            assertImageSize(imageSize.getSize());
        }

    }

    public static void assertImageHeight(String height){
        assertThat(Integer.valueOf(height), greaterThan(minimumSize));

    }

    public static void assertImageSize(String size){

        assertThat(Integer.valueOf(size), greaterThan(minimumSize));
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
