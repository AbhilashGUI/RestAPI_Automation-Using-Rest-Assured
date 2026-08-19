package request_parameters;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Request_Parameters {
    @Test
    public void handling_query_parameters() {

        given()
                .baseUri("https://restcountries.com/countries/v5")
                .basePath("/name")
                .queryParam("q", "India")
                .headers("Authorization", "Bearer rc_live_8f6a48fb0c9445b0974351a9b19be393")
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200);

    }

    @Test
    public void handling_multiple_query_parameters() {

        HashMap<String,Object> params=new HashMap<>();
        params.put("access_key","eaaaa33d3571fef962d994f117f202dd");
        params.put("Symbols","INR");

        given()
                .baseUri("http://data.fixer.io/api")
                .basePath("latest")
                .queryParams(params)
                //.queryParam("access_key","eaaaa33d3571fef962d994f117f202dd")
                //.queryParam("Symbols","INR")
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200);

    }

    @Test
    public void handling_multi_value_parameters() {
        given()
                .baseUri("https://restcountries.com/countries/v5")
                .basePath("/name")
                .queryParams("q","alpha")
                .headers("Authorization", "Bearer rc_live_8f6a48fb0c9445b0974351a9b19be393")
                .queryParam("codes", "col,no,ee,in")
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200);

    }



    @Test
    public void handling_path_parameter() {
        given()
                .baseUri("https://restcountries.com/countries/v5")
                .basePath("/currencies/{currency}")
                .pathParam("currency", "usd")
                .headers("Authorization", "Bearer rc_live_8f6a48fb0c9445b0974351a9b19be393")
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200);
    }

    //For Form Parameters: https://postman-echo.com/post
    //multipart/form-data
    //application/x-www-form-urlencoded
    //application/json

    @Test
    public void handling_form_data() {

        given()
                .baseUri("https://postman-echo.com")
                .basePath("/post")
                .contentType("application/x-www-form-urlencoded;charset=UTF-8")
                .formParam("First Name", "Abhilash")
                .formParam("Last Name", "Vemula").
                when()
                .post()
                .then()
                .log().all()
                .statusCode(200);

    }


}
