package tests;

import baseUrl.BaseUrlHerokuapp;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C19_BaseUrlHerokuapp extends BaseUrlHerokuapp {
    /*
    1- https://restful-booker.herokuapp.com/booking endpointine bir GET request gonderdigimizde
    donen response’un status code’unun 200 oldugunu ve Response’ta 1223 booking oldugunu test edin
     */


    @Test
    public void test01(){

        // 1- Request body ve end point hazirlama
        specHerokuapp.pathParam("pp1","booking");


        // 2- Expected data hazirlama
        // body icerisinde sipesifik bir datayi test ediniz demedigi icin olusturmuyorum


        // 3- Request gonderip, donen response'i kaydetme
        Response response = given()
                            .when().spec(specHerokuapp)
                            .get("/{pp1}");


        // 4- Assertions
        JsonPath responseJsonPath = response.jsonPath();
        System.out.println(responseJsonPath.getList("bookingid").size());
        // canli site oldugu icin degerler sureli degisiyor
        // bu yüzden jsonPath() methodu ile obje olsuturup bunu once List'e cevirip sonrasinda size'ini aldim

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("bookingid", Matchers.hasSize(1285));


        //Note: bookingid degeri surekli degistigi icin test fail oluyor !

    }
}
