package extractresponse;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GoogleExtractResponse {
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
    public void extractResponse() {
        Response res =
                given()
                        .queryParam("units", "imperial")
                        .queryParam("origins", "Rawtenstall,UK")
                        .queryParam("destinations", "Haslingden,UK")
                        .queryParam("key", "AIzaSyDQKXUyjfGYnIlLi-Q-OTOgnVMC1RKiiC8")
                .when()
                        .get("/distancematrix/xml")
                .then()
                        .statusCode(200).extract().response();
                String responseString = res.asString();
                System.out.println(responseString);

                String value = res.path("distancematrixresponse.row.element.distance.value");
                System.out.println("The distance is: " + value);


                XmlPath xmlp = new XmlPath(responseString);
                String valuePath = xmlp.get("distancematrixresponse.row.element.duration.value");
        System.out.println("The duration is: " + valuePath);
    }


}
