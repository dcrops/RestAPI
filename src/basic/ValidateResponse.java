package basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

public class ValidateResponse {


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

        @Test
        public void statusCodeVerification() {
            given()
                    .param("units", "imperial")
                    .param("origins", "Rawtenstall,UK")
                    .param("destinations", "Haslingden,UK")
                    .param("key", "AIzaSyDQKXUyjfGYnIlLi-Q-OTOgnVMC1RKiiC8")
            .when()
                    .get("/distancematrix/json")
            .then()
                    .statusCode(200)
                    .and()
                    .body("rows[0].elements[0].distance.text", equalTo("1.6 mi"))
                    .contentType(ContentType.JSON);

        }
}
