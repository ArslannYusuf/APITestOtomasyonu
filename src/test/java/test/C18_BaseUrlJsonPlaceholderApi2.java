package test;

import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class C18_BaseUrlJsonPlaceholderApi2 extends BaseUrlJsonPlaceholder {
    /*
    Class icinde 3 Test metodu olusturun ve asagidaki testleri yapin
    (ilk 2 task bir onceki class'da mevcut)

    3- https://jsonplaceholder.typicode.com/posts/50 endpointine bir DELETE request
    gonderdigimizde donen response’un status code’unun 200 oldugunu ve response body’sinin null oldugunu test edin
     */

    @Test
    public void test01() {
        //3- https://jsonplaceholder.typicode.com/posts/50 endpointine bir DELETE request
        //    gonderdigimizde donen response’un status code’unun 200 oldugunu ve response body’sinin null oldugunu test edin


        // 1- Request body ve end point hazirlama
        specJsonPlaceholder.pathParams("pp1","posts","pp2",50);


        // 2- Expected data hazirlama
        // body icerisinde sipesifik bir datayi test ediniz demedigi icin olusturmuyorum


        // 3- Request gonderip, donen response'i kaydetme
        Response response = given()
                            .when().spec(specJsonPlaceholder) //url'imi artik spec olarak kaydettigim icin spec() methodu icine yaziyorum
                            .delete("/{pp1}/{pp2}");  // yukarida pp1 ve pp2 olarak isimlendirdigim parametreleri "{ }" icerisinde belirtiyorum


        // 4- Assertions
        response
              .then()
              .assertThat()
              .statusCode(200)
              .body("title", Matchers.nullValue());

    }
}

/*
    NOTES:
    pathParam() methodu ile olusturdugum BaseUrlJsonPlaceholder class'indan baseURL olarak specJsonPlaceholder variablesini kullanarak
    parametre ekleyebiliyorum,
    pathParam(String s, Object o) seklinde key'i istedigim gibi isimlendirip Object value kismina da parametre (baseUrl'den sonra
    gelen "/" sonrasi parametreyi) ekliyorum

 */