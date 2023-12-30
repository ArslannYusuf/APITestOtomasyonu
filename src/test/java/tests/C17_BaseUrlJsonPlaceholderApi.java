package tests;

import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C17_BaseUrlJsonPlaceholderApi extends BaseUrlJsonPlaceholder {
    /*
    Class icinde 3 Test metodu olusturun ve asagidaki testleri yapin
    1- https://jsonplaceholder.typicode.com/posts endpointine bir GET request gonderdigimizde
    donen response’un status code’unun 200 oldugunu ve Response’ta 100 kayit oldugunu test edin

    2- https://jsonplaceholder.typicode.com/posts/44 endpointine bir GET request gonderdigimizde
    donen response’un status code’unun 200 oldugunu ve “title” degerinin  “optio dolor molestias sit” oldugunu test edin

    3- https://jsonplaceholder.typicode.com/posts/50 endpointine bir DELETE request
    gonderdigimizde donen response’un status code’unun 200 oldugunu ve response body’sinin
    null oldugunu test edin
     */

    @Test
    public void test01() {
        //1- https://jsonplaceholder.typicode.com/posts endpointine bir GET request gonderdigimizde
        //    donen response’un status code’unun 200 oldugunu ve Response’ta 100 kayit oldugunu test edin


        // 1- Request body ve end point hazirlama
        specJsonPlaceholder.pathParam("pp1","posts");


        // 2- Expected data hazirlama
        // body icerisinde sipesifik bir datayi test ediniz demedigi icin olusturmuyorum


        // 3- Request gonderip, donen response'i kaydetme
        Response response = given()
                            .when().spec(specJsonPlaceholder) //url'imi artik spec olarak kaydettigim icin spec() methodu icine yaziyorum
                            .get("/{pp1}");  // yukarida pp1 olarak isimlendirdigim parametreyi "{ }" icerisinde belirtiyorum


        // 4- Assertions
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("title",hasSize(100));

    }

    @Test
    public void test02() {
        //2- https://jsonplaceholder.typicode.com/posts/44 endpointine bir GET request gonderdigimizde
        //   donen response’un status code’unun 200 oldugunu ve “title” degerinin  “optio dolor molestias sit” oldugunu test edin

        // 1- Request body ve end point hazirlama
        specJsonPlaceholder.pathParams("pp1","posts","pp2",44);  // birden fazla parametre icin pathParams() methodunu kullandim

        // 2- Expected data hazirlama
        // body icerisinde sipesifik bir datayi test ediniz demedigi icin olusturmuyorum


        // 3- Request gonderip, donen response'i kaydetme
        Response response = given()
                            .when().spec(specJsonPlaceholder)
                            .get("/{pp1}/{pp2}");


        // 4- Assertions
        response.then().assertThat().statusCode(200).body("title",equalTo("optio dolor molestias sit"));

    }






}

/*
    NOTES:
    pathParam() methodu ile olusturdugum BaseUrlJsonPlaceholder class'indan baseURL olarak specJsonPlaceholder variablesini kullanarak
    parametre ekleyebiliyorum,
    pathParam(String s, Object o) seklinde key'i istedigim gibi isimlendirip Object value kismina da parametre (baseUrl'den sonra
    gelen "/" sonrasi parametreyi) ekliyorum

 */