package loggingexample;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ResponseLoggingExample {

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
        public void testMethod() {
            given()
                    .log()
                    //.headers()
                    .body()
                    //.parameters()
                    //.all()
                    //.ifValidationFails()
                    .auth()
                    .oauth(consumerKey, consumerSecret, accessToken, accessSecret)
                    .queryParam("status", "Testing Response Logging again!!")
                    .when()
                    .post("/update.json")
                    .then()
                    .log()
                    //.headers()
                    //.body()
                    //.parameters()
                    //.all()
                    //.ifValidationFails()
                    .ifError()
                    .statusCode(200);
    }
}
