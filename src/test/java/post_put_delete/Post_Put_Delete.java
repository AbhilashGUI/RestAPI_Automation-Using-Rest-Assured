package post_put_delete;


import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
public class Post_Put_Delete {

    @Test
    public void post_request() {
        File file = new File("create_employee.json");
        Integer id = given()
                .baseUri("https://dummy.restapiexample.com/api/v1")
                .basePath("/create")
                .contentType(ContentType.JSON)
                .body(file)
                .when()
                .post()
                .then()
                .statusCode(200)
                .body("data.age", equalTo("23"))
                .extract().path("data.id");
        System.out.println(id);


    }

    @Test
    public void post_request_using_json_object() {

        JSONObject body = new JSONObject();
        body.put("name", "Abhilash");
        body.put("salary", "70000");
        body.put("age", "32");

        Integer id = given()
                .baseUri("https://dummy.restapiexample.com/api/v1")
                .basePath("/create")
                .contentType(ContentType.JSON)
                .body(body.toString())
                .when()
                .post()
                .then().body("data.name", equalTo("Abhilash"))
                .extract().path("data.id");
        System.out.println(id);

    }

    @Test
    public void put_request_using_json_object() {

        JSONObject body = new JSONObject();
        body.put("employee_name", "Amith");
        body.put("employee_salary", "180000");
        body.put("employee_age", "32");

        given()
                .baseUri("https://dummy.restapiexample.com/api/v1")
                .basePath("/update/21")
                .contentType(ContentType.JSON)
                .body(body.toString())
                .when()
                .put()
                .then().log().all()
                .statusCode(200);
    }


    @Test
    public void get_request_check() {
        given()
                .baseUri("https://dummy.restapiexample.com/api/v1")
                .basePath("/employee/21")
                .when().get()
                .then().log().all()
                .statusCode(200);
    }

    //Since they are Dummy API's they will not behave as intended


    @Test
    public void delete_request() {

        String message = given()
                .baseUri("https://dummy.restapiexample.com/api/v1")
                .basePath("/delete/21")
                .when().delete()
                .then().log().all()
                .statusCode(200)
                .extract().path("message");
        System.out.println(message);
    }


    @Test
    public void get_request_check2() {
        given()
                .baseUri("https://dummy.restapiexample.com/api/v1")
                .basePath("/employee/21")
                .when().get()
                .then().log().all()
                .statusCode(200);
    }

    //It retrieve the data , since they are dummy API's

}