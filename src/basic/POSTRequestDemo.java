package basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class POSTRequestDemo {


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
            Response res =
            given()

                    .queryParam("key", "AIzaSyDQKXUyjfGYnIlLi-Q-OTOgnVMC1RKiiC8")
                    .body("{"
                            + "\"location\": {"
                            + "\"lat\": -33.8669710,"
                            + "\"lng\": -151.1958750 "
                            + "},"
                            + "\"accuracy\": 50,"
                            + "\"name\": \"Google Shoes!\","
                            + "\"phone_number\": \"(02) 9374 4000\","
                            + "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","
                            + "\"types\": [\"shoe_store\"],"
                            + "\"website\": \"http://www.google.com.au/\","
                            + "\"language\": \"en-AU\" "
                            + "}")

            .when()
                    .post("/place/add/json");
                    System.out.println(res.body().asString());
         /*   .then()
                    .statusCode(200).and()
                    .contentType(ContentType.JSON).and()
                    .body("scope", equalTo("APP")).and()
                    .body("status", equalTo("OK"));*/
        }
}
