package basic;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.PlacesAddModel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class POSTRequestWithPOJO {


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

            Map<String, Double> locationMap = new HashMap<String, Double>();
            locationMap.put("lat", -33.8669710);
            locationMap.put("lng",-151.1958750);

            List<String> types = new ArrayList<String>();
            types.add("shoe_store");

            PlacesAddModel places = new PlacesAddModel();
            places.setLocation(locationMap);
            places.setAccuracy(50);
            places.setName("Google Shoes!");
            places.setPhone_number("(02) 9374 4000");
            places.setAddress("48 Pirrama Road, Pyrmont, NSW 2009, Australia");
            places.setTypes(types);
            places.setWebsite("http://www.google.com.au/");
            places.setLanguage("en-AU");

            Response res =
            given()

                    .queryParam("key", "AIzaSyDQKXUyjfGYnIlLi-Q-OTOgnVMC1RKiiC8")
                    .body(places)

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
