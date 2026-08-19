package headers_and_cookies;

import io.restassured.http.Cookie;
import io.restassured.http.Headers;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Headers_And_Cookies {
    /*
     * What are Headers?
     *
     * Headers contain important information in the form of meta-data associated with:
     *
     * 		1. Request Body
     * 		2. Response Body
     * 		3. Caching of Response
     * 		4. Authentication
     * 		5. Cookies
     */


    /*
     * Adding Request Headers
     *
     * http://data.fixer.io/api/latest
     *
     * 1. If-None-Match: ee8d42ca86290f687b5a42ee5b8ecc07
     * 2. If-Modified-Since: 	Thu, 18 Sep 2025 15:44:05 GMT
     *
     */


    @Test
    public void sending_response_headers() {   //Validing the headers in response body
       given()
               .baseUri("https://data.fixer.io/api")
               .basePath("/latest")
               .queryParam("access_key", "eaaaa33d3571fef962d994f117f202dd")
               .queryParam("Symbols","INR")
               .headers("etag", "16e5b3731478389e0d2c0a0ab61d4105")
               .headers("last-modified","Wed, 19 Aug 2026 03:35:04 GMT")
               .log().all()
               .when().get()
               .then().log().all()
               .statusCode(200);
    }

    @Test
    public void sending_headers_intheformofobject() { //Validating the headers in response body
        HashMap<String,Object> headers=new HashMap<>();
        headers.put("content-type","application/json; Charset=UTF-8");
        headers.put("access-control-allow-methods","GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS");
        headers.put("access-control-allow-origin","*");
        headers.put("x-request-time","0.013");

        given()
                .baseUri("https://data.fixer.io/api")
                .basePath("latest")
                .queryParam("access_key","eaaaa33d3571fef962d994f117f202dd")
                .queryParam("Symbols","INR")
                .headers(headers).log().all()
                .when().get()
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void sending_request_cookies() {   //Validating the cookies in the response body

        given()
                .baseUri("https://data.fixer.io/api")
                .basePath("/latest")
                .queryParam("access_key","eaaaa33d3571fef962d994f117f202dd")
                .queryParam("Symbols","INR")
                .cookie("Userdefined","Testcheck1","Testcheck2")
                .log().all()
                .when().get()
                .then().log().all()
                .statusCode(200);
    }
  //Importance of cookie builder is that we have an additional methods
    @Test
    public void sending_cookies_using_builder() {  //Validating the cookies in the response body

        Cookie cookie= new Cookie.Builder("Usertype","int").setSecured(true).setComment("test cookie").build();
        given()
                .baseUri("https://data.fixer.io/api")
                .basePath("/latest")
                .queryParam("access_key","eaaaa33d3571fef962d994f117f202dd")
                .queryParam("Symbols","INR")
                .cookie(cookie).log().all()
                .when().get()
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void validate_response_header() {  //Sending the headers in response and Validating the same
       given()
               .baseUri("https://data.fixer.io/api")
               .basePath("/latest")
               .queryParam("access_key","eaaaa33d3571fef962d994f117f202dd")
               .queryParam("Symbols","INR")
               .when().get()
               .then().log().all()
               .statusCode(200)
               .headers("content-type","application/json; Charset=UTF-8");
    }

    @Test
    public void extract_response_header() {  //Extracting the values of headers in response body

        Headers headers= given()
                .baseUri("https://data.fixer.io/api")
                .basePath("/latest")
                .queryParam("access_key","eaaaa33d3571fef962d994f117f202dd")
                .queryParam("Symbols","INR")
                .when().get()
                .then().log().all()
                .extract().headers();
        System.out.println(headers.getValue("date"));
        System.out.println(headers.getValue("content-type"));
        System.out.println(headers.getValue("x-request-time"));


    }

    @Test
    public void extract_response_cookies() {
       Map<String,String> cookies= given()
                .baseUri("https://data.fixer.io/api")
                .basePath("/latest")
                .queryParam("access_key","eaaaa33d3571fef962d994f117f202dd")
                .queryParam("Symbols","INR")
                .when().get()
                .then().log().all()
                .extract().cookies();
        System.out.println(cookies.get("chunked"));

    }
}

