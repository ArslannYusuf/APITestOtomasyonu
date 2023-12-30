package test;

import baseUrl.BaseUrlHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C20_BaseUrlHerokuapp2 extends BaseUrlHerokuapp {

    /*
    2- https://restful-booker.herokuapp.com/booking endpointine yandaki body’ye sahip bir POST request
        gonderdigimizde donen response’un status code’unun 200 oldugunu ve “firstname” degerinin “Ahmet” oldugunu test edin

        {
        "firstname" : "Yusuf",
        "lastname" : “Leon",
        "totalprice" : 500,
        "depositpaid" : false,
        "bookingdates" : {
            "checkin" : "2021-06-01",
            "checkout" : "2021-06-10"
        },
        "additionalneeds" : "wi-fi"
        }
     */


    @Test
    public void test01() {

        // 1- Request body ve end point hazirlama
        specHerokuapp.pathParam("pp1", "booking");

        JSONObject requestBody = new JSONObject();
        JSONObject rezervasyonTairhleriJson = new JSONObject(); // inner JSonObject

        rezervasyonTairhleriJson.put("checkin", "2021-06-01");
        rezervasyonTairhleriJson.put("checkout", "2021-06-10");

        requestBody.put("firstname", "Yusuf");
        requestBody.put("lastname", "Leon");
        requestBody.put("totalprice", 500);
        requestBody.put("depositpaid", false);
        requestBody.put("bookingdates", rezervasyonTairhleriJson);
        requestBody.put("additionalneeds", "wi-fi");


        // 2- Expected data hazirlama
        // body icerisinde sipesifik bir datayi test ediniz demedigi icin olusturmuyorum


        // 3- Request gonderip, donen response'i kaydetme
        Response response = given().contentType(ContentType.JSON)
                            .when().spec(specHerokuapp).body(requestBody.toString())
                            .post("/{pp1}");


        // 4- Assertions
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("booking.firstname",equalTo("Yusuf"));

    }
}
