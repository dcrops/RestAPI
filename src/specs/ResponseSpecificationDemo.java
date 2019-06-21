package specs;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;

public class ResponseSpecificationDemo {

    String consumerKey = "QDviLexGGnXVYp0xd0bxi2sDx";
    String consumerSecret = "ethF2T8dziQBgcS5KPPx6MvwN9Khwwn6KBCd4RmM0fKrvH5dlk";
    String accessToken = "886092793365409792-mBtgcXzcn9IOC1WmWp5c1fXMn3HDZHr";
    String accessSecret = "jUIEOAnEiZLlICLBlpR6UhVKZ98CMpQ7banbfcP5gW3eS";

    ResponseSpecBuilder responseBuilder;
    static ResponseSpecification responseSpec;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://api.twitter.com";
        RestAssured.basePath = "/1.1/statuses";
        responseBuilder = new ResponseSpecBuilder();
        responseBuilder.expectStatusCode(200);
        responseBuilder.expectBody("user.name", hasItem("RestAPI Automation"));
        responseBuilder.expectBody("user.screen_name", hasItem("apiautomation"));
        responseSpec = responseBuilder.build();
    }

    @Test
    public void testMethod() {
        given()
                .auth()
                .oauth(consumerKey, consumerSecret, accessToken, accessSecret)
                .queryParam("status", "#HowtoHashtagagain1" +
                        " #andagain1" +
                        " #onemoretime1")
                .queryParam("count", 5)
        .when()
                .get("/user_timeline.json")
        .then()
               // .statusCode(200);
                .spec(responseSpec)
                .body("entities.hashtags[1].text", hasItem("andagain1"))
                .body("entities.hashtags[1].size", greaterThan(2));
    }
}
