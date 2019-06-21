package basic;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

    public class GetRequestDemo {
        /****
         * Given I have this information
         * When I perform this action
         * Then this should be the output
         *
         * https://maps.googleapis.com/maps/api/distancematrix/json?
         * units=imperial&origins=Washington,DC&destinations=New+York+City,NY&
         * key=AIzaSyDQKXUyjfGYnIlLi-Q-OTOgnVMC1RKiiC8
         */

        @BeforeClass
        public void setup() {
            RestAssured.baseURI = "https://maps.googleapis.com";
            RestAssured.basePath = "/maps/api";
        }

        @Test(enabled = false)
        public void statusCodeVerification() {
            given()
                    .param("units", "imperial")
                    .param("origins", "Rawtenstall,UK")
                    .param("destinations", "Haslingden,UK")
                    .param("key", "AIzaSyDQKXUyjfGYnIlLi-Q-OTOgnVMC1RKiiC8")
            .when()
                    .get("/distancematrix/json")
            .then()
                    .statusCode(200);
        }

    @Test
    public void getResponseBody() {
        Response res =
        given()
                .param("units", "imperial")
                .param("origins", "Rawtenstall,UK")
                .param("destinations", "Haslingden,UK")
                .param("key", "AIzaSyDQKXUyjfGYnIlLi-Q-OTOgnVMC1RKiiC8")
        .when()
                .get("/distancematrix/json");

        System.out.println(res.body().asString());

    }
}
