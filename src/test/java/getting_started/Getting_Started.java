package getting_started;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Getting_Started {

    @Test
    public void simple_get_request() {

        given()
                .baseUri("https://restcountries.com")
                .basePath("/countries/v5")
                .headers("Authorization", "Bearer rc_live_8f6a48fb0c9445b0974351a9b19be393")
                .when().get()
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void validate_json_response() {

        given()
                .baseUri("https://api.restcountries.com/countries/v5")
                .basePath("/name")
                .queryParam("q", "USA")
                .headers("Authorization", "Bearer rc_live_8f6a48fb0c9445b0974351a9b19be393")
                .when().get()
                .then().log().all()
                .statusCode(200)
                .body("data.objects[0].names.common", equalTo("United States"))
                .body("data.objects[0].names.official", equalTo("United States of America"))
                .body("data.objects[0].currencies[0].code", equalTo("USD"))
                .body("data.objects[0].currencies[0].name", equalTo("United States dollar"))
                .body("data.objects[0].currencies[0].symbol", equalTo("$"))
                .body("data.objects[0].languages[0].name", equalTo("English"));
    }

    @Test
    public void validate_xml_response() {
        given()
                .baseUri("https://api.openweathermap.org/data/2.5")
                .basePath("/weather")
                .queryParam("q", "London,uk")
                .queryParam("APPID", "5e76ce9abdf5914f0123cbd6303bafd7")
                .queryParam("mode", "xml")
                .when().get()
                .then().log().all()
                .statusCode(200)
                .body("current.city .@id", equalTo("2643743"))
                .body("current.city .@name", equalTo("London"))
                .body("current.city.country", equalTo("GB"))
                .body("current.city.timezone", equalTo("3600"));


    }

    @Test
    public void extract_response_data() {
        Response response = given()
                .baseUri("https://api.restcountries.com/countries/v5")
                .basePath("/name")
                .queryParam("q", "USA")
                .headers("Authorization", "Bearer rc_live_8f6a48fb0c9445b0974351a9b19be393")
                .when().get()
                .then().extract().response();
        System.out.println(response.asString());

    }
        @Test
        public void extract_single_value () {

        //APIID/key needs to updated
             String temperature=given()
                     .baseUri("https://api.openweathermap.org/data/2.5")
                     .basePath("/weather")
                     .queryParam("q","London,uk")
                     .queryParam("APPID","ce219d032a3b9a4bb9a961227120aa6b")
                     .queryParam("mode","xml")
                     .when().get()
                     .then().statusCode(200)
                     .extract().path("current.temperature.@value");
             System.out.println(temperature);

        }

        @Test
        public void verify_status_line () {
               given()
                       .baseUri("https://api.printful.com")
                       .basePath("/variant/1")
                       .when().get()
                       .then().log().all()
                       .statusCode(404)
                       .statusLine("HTTP/1.1 404 Not Found");

        }
    }



