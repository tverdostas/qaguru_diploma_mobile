package helpers;

import config.MobileConfig;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;

public class BrowserstackHelper {

    private static final MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());
    private static final String username = config.browserstackUser();
    private static final String accessKey = config.browserstackKey();

    public static String getBrowserstackVideoUrl(String sessionId) {
        String video_url = given()
                .auth().basic(username, accessKey)
                .when()
                .get("https://api-cloud.browserstack.com/app-automate/sessions/" + sessionId +".json")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .path("automation_session.video_url");

        System.out.println("video_url: " + video_url);
        return video_url;
    }
}
