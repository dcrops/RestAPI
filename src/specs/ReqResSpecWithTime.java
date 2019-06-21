package specs;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

public class ReqResSpecWithTime {

    String consumerKey = "QDviLexGGnXVYp0xd0bxi2sDx";
    String consumerSecret = "ethF2T8dziQBgcS5KPPx6MvwN9Khwwn6KBCd4RmM0fKrvH5dlk";
    String accessToken = "886092793365409792-mBtgcXzcn9IOC1WmWp5c1fXMn3HDZHr";
    String accessSecret = "jUIEOAnEiZLlICLBlpR6UhVKZ98CMpQ7banbfcP5gW3eS";

    RequestSpecBuilder requestBuilder;
    static RequestSpecification requestSpec;

    ResponseSpecBuilder responseBuilder;
    static ResponseSpecification responseSpec;

    @BeforeClass
    public void setup() {

        AuthenticationScheme authScheme =
                RestAssured.oauth(consumerKey, consumerSecret, accessToken, accessSecret);
        requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri("https://api.twitter.com");
        requestBuilder.setBasePath("/1.1/statuses");
        requestBuilder.addQueryParam("user_id", "apiautomation");
        requestBuilder.setAuth(authScheme);
        requestSpec = requestBuilder.build();

        responseBuilder = new ResponseSpecBuilder();
        responseBuilder.expectStatusCode(200);
        responseBuilder.expectResponseTime(lessThan(1200L), TimeUnit.MILLISECONDS);
        responseBuilder.expectBody("user.name", hasItem("RestAPI Automation"));
        responseBuilder.expectBody("user.screen_name", hasItem("apiautomation"));
        responseSpec = responseBuilder.build();
    }

    @Test
    public void testMethod() {
        given()
                .spec(requestSpec)
        .when()
                .get("/user_timeline.json")
        .then()
                .spec(responseSpec)
                .body("entities.hashtags[1].text", hasItem("andagain1"))
                .body("entities.hashtags[1].size", greaterThan(2));
    }
}
