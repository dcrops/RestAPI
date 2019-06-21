package usefultricks;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

public class TwitterCheckResponseTime {

    String consumerKey = "QDviLexGGnXVYp0xd0bxi2sDx";
    String consumerSecret = "ethF2T8dziQBgcS5KPPx6MvwN9Khwwn6KBCd4RmM0fKrvH5dlk";
    String accessToken = "886092793365409792-mBtgcXzcn9IOC1WmWp5c1fXMn3HDZHr";
    String accessSecret = "jUIEOAnEiZLlICLBlpR6UhVKZ98CMpQ7banbfcP5gW3eS";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://api.twitter.com";
        RestAssured.basePath = "/1.1/statuses";
    }

    @Test
    public void testMethod1() {
        long responseTime =
        given()
                .auth()
                .oauth(consumerKey, consumerSecret, accessToken, accessSecret)
                .queryParam("screen_name", "apiautomation")
                .queryParam("count", 2)
        .when()
                .get("/user_timeline.json")
              //  .time();
                .timeIn(TimeUnit.MILLISECONDS);
        System.out.println("Response Time: " + responseTime);
    }

    @Test
    public void testMethod2() {

        given()
                .auth()
                .oauth(consumerKey, consumerSecret, accessToken, accessSecret)
                .queryParam("screen_name", "apiautomation")
                .queryParam("count", 2)
        .when()
                .get("/user_timeline.json")
        .then()
                .time(lessThan(1L), TimeUnit.SECONDS)
                .log().body()
                .body("user.name", hasItem("RestAPI Automation"))
                .body("user.screen_name", hasItem("apiautomation"))
                .body("entities.hashtags[0].text", hasItem("andagain1"))
                .body("entities.hashtags[0].size", greaterThan(2))
                .statusCode(200);
    }
}
