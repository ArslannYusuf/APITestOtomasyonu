package tests;

import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.PojoJsonPlaceholder;
import testData.TestDataJsonPlaceholder;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C31_Put_PojoClass extends BaseUrlJsonPlaceholder {
    /*
    https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir PUT
    request yolladigimizda donen response’in
    status kodunun 200,
    content type'nin "application/json; charset=utf-8",
    Connection header degerinin "keep-alive"
    response body’sinin asagida verilen ile ayni oldugunu test ediniz

    Request Body
        {
        "title": "Ahmet",
        "body": "Merhaba",
        "userId": 10,
        "id": 70
        }


    Expected Data :
        {
        "title": "Yusuf",
        "body": "never back down",
        "userId": 10,
        "id": 70
        }
     */

    @Test
    public void test01() {

        // 1- Request body ve end point hazirlama
        specJsonPlaceholder.pathParams("pp1", "posts", "pp2", "70");

        PojoJsonPlaceholder requestBodyPojo = new PojoJsonPlaceholder("Yusuf","never back down",10,70);

        // 2- Expected data hazirlama
        PojoJsonPlaceholder expectedDataPojo = new PojoJsonPlaceholder("Yusuf","never back down",10,70);

        // 3- Request gonderip, donen response'i kaydetme
        Response response = given().spec(specJsonPlaceholder).contentType(ContentType.JSON)
                            .when().body(requestBodyPojo)
                            .put("{pp1}/{pp2}");


        // 4- Assertions

        // expected data (Pojo) <====> response (Pojo)
        //   expectedDataPojo           responsePojo

        // --> donen response'yi pojo class'a cevirmem gerekli
        PojoJsonPlaceholder responsePojo = response.as(PojoJsonPlaceholder.class);

        //    status kodunun 200,
        assertEquals(TestDataJsonPlaceholder.basariliStatusKodu,response.statusCode());

        //    content type'nin "application/json; charset=utf-8",
        assertEquals(TestDataJsonPlaceholder.contentType,response.contentType());

        //    Connection header degerinin "keep-alive"
        assertEquals(TestDataJsonPlaceholder.connectionHeader,response.header("Connection"));

        //    response body’sinin asagida verilen ile ayni oldugunu test ediniz
        assertEquals(expectedDataPojo.getTitle(),responsePojo.getTitle());
        assertEquals(expectedDataPojo.getBody(),responsePojo.getBody());
        assertEquals(expectedDataPojo.getUserId(),responsePojo.getUserId());
        assertEquals(expectedDataPojo.getId(),responsePojo.getId());
    }
}

