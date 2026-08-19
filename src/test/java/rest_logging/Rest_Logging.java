package rest_logging;


import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class Rest_Logging {
    @Test
    public void log_all_details() {

        given()
                .baseUri("https://restcountries.com/countries/v5")
                .basePath("/name")
                .queryParam("q","USA")
                .headers("Authorization", "Bearer rc_live_8f6a48fb0c9445b0974351a9b19be393")
                .when().get()
                .then().log().all();

    }

    @Test
    public void log_body_and_header() {
        given()
                .baseUri("https://restcountries.com/countries/v5")
                .basePath("/name")
                .queryParam("q", "USA")
                .headers("Authorization","Bearer rc_live_8f6a48fb0c9445b0974351a9b19be393")
                .when().get()
                .then()
                //log().headers();
                .log().body();
    }

    @Test
    public void log_cookies_and_status() {
        given()
                .baseUri("https://restcountries.com/countries/v5")
                .basePath("/name")
                .queryParam("q", "USA")
                .headers("Authorization", "Bearer rc_live_8f6a48fb0c9445b0974351a9b19be393")
                .when().get()
                .then()
                .log().status();
                //.log().cookies();
    }


    @Test
    public void log_if_error() {
        given()
                .baseUri("https://restcountries.com/countries/v5")
                .basePath("/name")
                .queryParam("q","")
                .headers("Authorization", "Bearer rc_live_8f6a48fb0c9445b0974351a9b19be393")
                .when().get()
                .then()
                //.log().ifError()             //Only if error occurs
                .log().ifStatusCodeIsEqualTo(400);  //Only if to display the occurred error code

    }

    @Test
    public void log_if_validation_fails() {

        given()
                .baseUri("https://restcountries.com/countries/v5")
                .basePath("/name")
                .queryParam("q","USA")
                .headers("Authorization", "Bearer rc_live_8f6a48fb0c9445b0974351a9b19be393")
                .when().get()
                .then()
                .log().ifValidationFails()  //About assertions
                //.statusCode(400);
                .statusCode(200);


}
    }