package tests;

import baseUrl.BaseUrlHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import testData.TestDataHerokuapp;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C27_Post_TestDataKullanimi extends BaseUrlHerokuapp {
    /*
    https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST request
    gonderdigimizde donen response’un id haric asagidaki gibi oldugunu test edin.


        Request body
            {
            "firstname" : "Yusuf",
            "lastname" : “Leon",
            "totalprice" : 3500,
            "depositpaid" : false,
            "bookingdates" : {
                "checkin" : "2021-06-01",
                "checkout" : "2021-06-10"
            },
            "additionalneeds" : "wi-fi"
            }


        Response Body
        {
        "bookingid": 24,
        "booking": {
            "firstname": "Yusuf",
            "lastname": "Leon",
            "totalprice": 3500,
            "depositpaid": false,
            "bookingdates": {
                "checkin": "2021-06-01",
                "checkout": "2021-06-10"
            },
            "additionalneeds": "wi-fi"
            }
        }

     */

    @Test
    public void test01(){

        // 1- Request body ve end point hazirlama
        specHerokuapp.pathParam("pp1","booking");

        JSONObject requestBody = TestDataHerokuapp.jsonRequestBodyOlustur();


        // 2- Expected data hazirlama
        JSONObject responseBody = TestDataHerokuapp.jsonResponseBodyOlustur();


        // 3- Request gonderip, donen response'i kaydetme
        Response response = given().spec(specHerokuapp).contentType(ContentType.JSON)
                            .when().body(requestBody.toString())
                            .post("/{pp1}");


        // 4- Assertions
        JsonPath responseJsonPath = response.jsonPath(); // assert islemlerinde kolaylik olmasi acisindan jsonPath formatina cevirdim

        assertEquals(responseBody.getJSONObject("booking").getString("firstname"),
                                    responseJsonPath.getString("booking.firstname"));

        assertEquals(responseBody.getJSONObject("booking").getString("lastname"),
                                    responseJsonPath.getString("booking.lastname"));

        assertEquals(responseBody.getJSONObject("booking").getInt("totalprice"),
                                    responseJsonPath.getInt("booking.totalprice"));

        assertEquals(responseBody.getJSONObject("booking").getBoolean("depositpaid"),
                                    responseJsonPath.getBoolean("booking.depositpaid"));

        assertEquals(responseBody.getJSONObject("booking").getString("additionalneeds"),
                                    responseJsonPath.getString("booking.additionalneeds"));

        assertEquals(responseBody.getJSONObject("booking").getJSONObject("bookingdates").getString("checkin"),
                                    responseJsonPath.getString("booking.bookingdates.checkin"));

        assertEquals(responseBody.getJSONObject("booking").getJSONObject("bookingdates").getString("checkout"),
                                    responseJsonPath.getString("booking.bookingdates.checkout"));
    }
}