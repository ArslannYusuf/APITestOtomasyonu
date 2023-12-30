package tests;

import baseUrl.BaseUrlHerokuapp;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

public class C21_BaseUrlHerokuappQueryParam extends BaseUrlHerokuapp {
    /*
    https://restful-booker.herokuapp.com/booking endpointine gerekli Query parametrelerini
    yazarak “firstname” degeri “Eric” olan rezervasyon oldugunu test edecek bir GET request gonderdigimizde,
    donen response’un ;
    status code’unun 200 oldugunu ve “Eric” ismine sahip en az bir booking oldugunu test edin
     */

    @Test
    public void test01(){

        // 1- Request body ve end point hazirlama
        specHerokuapp
                .pathParam("pp1","booking")             // pathParam() ile baseUrlde /'dan sonra gelen parametreyi girdim
                .queryParam("firstname","Eric"); // queryParam() ile baseUrlde + pathParam + ?'den sonra gelen parametreyi girdim

        // 2- Expected data hazirlama
        // body icerisinde sipesifik bir datayi test ediniz demedigi icin olusturmuyorum



        // 3- Request gonderip, donen response'i kaydetme
        Response response = given()
                            .when().spec(specHerokuapp)
                            .get("/{pp1}");



        // 4- Assertions
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("booking",hasSize(3));

        //Note: bookingid degeri surekli degistigi icin test fail olabilir !
    }
}
