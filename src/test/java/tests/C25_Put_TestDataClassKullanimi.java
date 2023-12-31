package tests;

import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import testData.TestDataJsonPlaceholder;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C25_Put_TestDataClassKullanimi extends BaseUrlJsonPlaceholder {
    /*
    https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir PUT request yolladigimizda
    donen response’in;
    status kodunun 200,
    content type’inin “application/json; charset=utf-8”,
    Connection header degerinin “keep-alive”
    ve response body’sinin asagida verilen ile ayni oldugunu test ediniz

    Request Body
        {
        "title": "Yusuf",
        "body": "never back down",
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
    public void test01(){

        // 1- Request body ve end point hazirlama
        specJsonPlaceholder.pathParams("pp1","posts","pp2","70");

        JSONObject resquestBody = TestDataJsonPlaceholder.JsonBodyOlustur(10,70,"Yusuf","never back down");


        // 2- Expected data hazirlama
        JSONObject expectedData = TestDataJsonPlaceholder.JsonBodyOlustur(10,70,"Yusuf","never back down");

        // 3- Request gonderip, donen response'i kaydetme
        Response response = given().spec(specJsonPlaceholder)
                            .when().body(resquestBody.toString()).contentType(ContentType.JSON)
                            .put("{pp1}/{pp2}");


        // 4- Assertions

        // status kodunun 200
        assertEquals(TestDataJsonPlaceholder.basariliStatusKodu,response.statusCode());

        // content type’inin “application/json; charset=utf-8”
        assertEquals(TestDataJsonPlaceholder.contentType,response.contentType());

        // Connection header degerinin “keep-alive”
        assertEquals(TestDataJsonPlaceholder.connectionHeader,response.header("Connection"));

        // response body’sinin asagida verilen ile ayni oldugunu test ediniz
        JsonPath responseJsonPath = response.jsonPath();
        assertEquals(expectedData.getInt("id"),responseJsonPath.getInt("id"));
        assertEquals(expectedData.getInt("userId"),responseJsonPath.getInt("userId"));
        assertEquals(expectedData.getString("title"),responseJsonPath.getString("title"));
        assertEquals(expectedData.getString("body"),responseJsonPath.getString("body"));
    }
}
