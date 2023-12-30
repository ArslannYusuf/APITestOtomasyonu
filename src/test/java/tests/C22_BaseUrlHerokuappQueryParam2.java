package tests;

import baseUrl.BaseUrlHerokuapp;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C22_BaseUrlHerokuappQueryParam2 extends BaseUrlHerokuapp {
    /*
    https://restful-booker.herokuapp.com/booking endpointine gerekli Query parametrelerini yazarak “firstname” degeri “Jim”
    ve “lastname” degeri “Jackson” olan rezervasyon oldugunu test edecek bir GET request gonderdigimizde,
    donen response’un ;
    status code’unun 200 oldugunu ve “Jim Jackson” ismine sahip en az bir booking oldugunu test edin
     */

    @Test
    public void test01(){

        // 1- Request body ve end point hazirlama
        specHerokuapp
                .pathParam("pp1","booking")
                .queryParams("firstname","Jim","lastname","Jackson"); //

        // 2- Expected data hazirlama
        // body icerisinde sipesifik bir datayi test ediniz demedigi icin olusturmuyorum



        // 3- Request gonderip, donen response'i kaydetme
        Response response = given()
                            .when().spec(specHerokuapp)
                            .get("/{pp1}");



        // 4- Assertions
        response.then().assertThat().statusCode(200).body("bookingid", Matchers.hasSize(1));


        //Note: site surekli degistigi icin test fail olabilir !
    }
}
