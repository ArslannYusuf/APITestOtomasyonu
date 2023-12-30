package test;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C01_GetRequest_GetRequestResponseBodyYazdirma {

    @Test
    public void get01() {

        // https://restful-booker.herokuapp.com/booking/10 url’ine
        // bir GET request gonderdigimizde donen Response’u yazdirin.


        // 1- Request body ve end point hazirlama
        String url = "https://restful-booker.herokuapp.com/booking/10";

        // 2- Expected data hazirlama
        // donen response'nin niteliği belirtilmedigi icin pass gecildi

        // 3- Request gonderip, donen response'i kaydetme
        Response response = given().when().get(url);
        response.prettyPrint(); // response'in body'sini yazdirir

        // 4- Assertion
        // assertion istenmedigi icin pass gecildi

    }
}



/*

Donen response :

{
    "firstname": "Mark",
    "lastname": "Brown",
    "totalprice": 990,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2016-02-18",
        "checkout": "2017-08-02"
    },
    "additionalneeds": "Breakfast"
}

 */