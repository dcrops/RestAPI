package specs;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RequestSpecificationDemo {

    String consumerKey = "QDviLexGGnXVYp0xd0bxi2sDx";
    String consumerSecret = "ethF2T8dziQBgcS5KPPx6MvwN9Khwwn6KBCd4RmM0fKrvH5dlk";
    String accessToken = "886092793365409792-mBtgcXzcn9IOC1WmWp5c1fXMn3HDZHr";
    String accessSecret = "jUIEOAnEiZLlICLBlpR6UhVKZ98CMpQ7banbfcP5gW3eS";

    RequestSpecBuilder requestBuilder;
    static RequestSpecification requestSpec;

    @BeforeClass
    public void setup() {
        /*RestAssured.baseURI = "https://api.twitter.com";
        RestAssured.basePath = "/1.1/statuses";*/
        AuthenticationScheme authScheme =
                RestAssured.oauth(consumerKey, consumerSecret, accessToken, accessSecret);
        requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri("https://api.twitter.com");
        requestBuilder.setBasePath("/1.1/statuses");
        requestBuilder.addQueryParam("status", "#RestAPI");
        requestBuilder.setAuth(authScheme);
        requestSpec = requestBuilder.build();
    }

    @Test
    public void postRequest() {
        given()
                .spec(requestSpec)
               /* .auth()
                .oauth(consumerKey, consumerSecret, accessToken, accessSecret)
                .queryParam("status", "#HowtoHashtagagain1" +
                        " #andagain1" +
                        " #onemoretime1")*/
                .when()
                .post("/update.json")
                .then()
                .statusCode(200);
    }
}
