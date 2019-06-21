package twitterapiexample;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TwitterWorkflow {

    String consumerKey = "QDviLexGGnXVYp0xd0bxi2sDx";
    String consumerSecret = "ethF2T8dziQBgcS5KPPx6MvwN9Khwwn6KBCd4RmM0fKrvH5dlk";
    String accessToken = "886092793365409792-mBtgcXzcn9IOC1WmWp5c1fXMn3HDZHr";
    String accessSecret = "jUIEOAnEiZLlICLBlpR6UhVKZ98CMpQ7banbfcP5gW3eS";
    String tweetID = "";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://api.twitter.com";
        RestAssured.basePath = "/1.1/statuses";
    }

    @Test
    public void postTweet() {
        Response res =
        given()
                .auth()
                .oauth(consumerKey, consumerSecret, accessToken, accessSecret)
                .queryParam("status", "Here is another tweet1")
                .when()
                .post("/update.json")
                .then()
                .statusCode(200)
                .extract().response();

        tweetID = res.path("id_str");
        System.out.println(tweetID);
    }

    @Test(dependsOnMethods = {"postTweet"})
    public void readTweet() {
        Response res =
                given()
                        .auth()
                        .oauth(consumerKey, consumerSecret, accessToken, accessSecret)
                        .queryParam("id", tweetID)
                        .when()
                        .get("/show.json")
                        .then()
                        .statusCode(200)
                        .extract().response();

        String text = res.path("text");
        System.out.println("The tweet text is: " + text);
    }

    @Test(dependsOnMethods = {"readTweet"})
    public void deleteTweet() {

                given()
                        .auth()
                        .oauth(consumerKey, consumerSecret, accessToken, accessSecret)
                        .queryParam("id", tweetID)
                        .when()
                        .post("/destroy.json")
                        .then()
                        .statusCode(200);



    }

}
