package extractresponse;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TwitterExtractResponse2 {

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
    public void postRequest() {
        Response res =
        given()
                .auth()
                .oauth(consumerKey, consumerSecret, accessToken, accessSecret)
                .queryParam("status", "This is allowing me to get the users name using JsonPath")
                .when()
                .post("/update.json")
                .then()
                .statusCode(200)
                .extract().response();

        String id = res.path("id_str");
        System.out.println("the id is: " + id);

        String responseString = res.asString();
        System.out.println(responseString);

        JsonPath jsPath = new JsonPath(responseString);
        String name = jsPath.get("user.name");
        System.out.println("The Users name is: " + name);
    }
}
