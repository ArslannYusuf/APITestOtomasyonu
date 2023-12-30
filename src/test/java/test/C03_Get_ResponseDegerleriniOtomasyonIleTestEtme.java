package test;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C03_Get_ResponseDegerleriniOtomasyonIleTestEtme {
    @Test
    public void test02() {

        /*
        https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request
        gonderdigimizde donen Response’un,
        status code’unun 200,
        ve content type’inin application/json; charset=utf-8,
        ve Server isimli Header’in degerinin Cowboy,
        ve status Line’in HTTP/1.1 200 OK
        ve response suresinin 5 sn’den kisa oldugunu manuel olarak test ediniz.
         */


        // 1- Request body ve end point hazirlama
        String url ="https://restful-booker.herokuapp.com/booking/10";


        // 2- Expected data hazirlama
        // donen response'nin niteliği belirtilmedigi icin pass gecildi


        // 3- Request gonderip, donen response'i kaydetme
        Response response= given()
                            .when()
                            .get(url);


        // 4- Assertion
        response
                .then()         // assert methodunu kullanmak icin then() methodu kullaniyoruz
                .assertThat()   // response' nin assert edilmesini saglar
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .header("server","Cowboy")
                .statusLine("HTTP/1.1 200 OK");

    }
}