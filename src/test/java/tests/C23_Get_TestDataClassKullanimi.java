package tests;

import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataJsonPlaceholder;

import static io.restassured.RestAssured.given;

public class C23_Get_TestDataClassKullanimi extends BaseUrlJsonPlaceholder {

    /*
    https://jsonplaceholder.typicode.com/posts/22 url'ine bir GET request yolladigimizda donen
    response’in status kodunun 200 ve response body’sinin asagida verilen ile ayni oldugunutest ediniz
    Response body :
        {
        "userId": 3,
        "id": 22,
        "title": "dolor sint quo a velit explicabo quia nam",
        "body": "eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
        um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
        }
     */

    @Test
    public void test01(){

        // 1- Request body ve end point hazirlama
        specJsonPlaceholder.pathParams("pp1","posts","pp2","22");


        // 2- Expected data hazirlama
        JSONObject expectedData = TestDataJsonPlaceholder.responseBodyOlustur22();
        // TestDataJsonPlaceholder class'inda olsuturdugum respponse body'sini
        // responseBodyOlustur22() methodu'ndan cagırarak expectedData objesi'ne assigne edip assert isleminde kullanabilirim


        // 3- Request gonderip, donen response'i kaydetme
        Response response = given()
                            .when().spec(specJsonPlaceholder)
                            .get("/{pp1}/{pp2}");


        // 4- Assertions
        JsonPath responseJsonPath = response.jsonPath();
        Assert.assertEquals(TestDataJsonPlaceholder.basariliStatusKodu,response.statusCode());
        Assert.assertEquals(expectedData.getInt("userId"),responseJsonPath.getInt("userId"));
        Assert.assertEquals(expectedData.getInt("id"),responseJsonPath.getInt("id"));
        Assert.assertEquals(expectedData.getString("title"),responseJsonPath.getString("title"));
        Assert.assertEquals(expectedData.getString("body"),responseJsonPath.getString("body"));

    }
}
