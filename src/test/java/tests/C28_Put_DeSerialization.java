package tests;

import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import testData.TestDataJsonPlaceholder;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C28_Put_DeSerialization extends BaseUrlJsonPlaceholder {
    /*
    https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir PUT
    request yolladigimizda donen response’in response body’sinin asagida verilen ile ayni oldugunu test ediniz

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
    public void test01() {

        // 1- Request body ve end point hazirlama
        specJsonPlaceholder.pathParams("pp1", "posts", "pp2", "70");

        // Resquest Body'sini Map olarak olusuturuyorum
        Map<String, Object> requestBodyMap = TestDataJsonPlaceholder.bodyOlusturMap();


        // 2- Expected data hazirlama
        Map<String, Object> expectedData = TestDataJsonPlaceholder.bodyOlusturMap();


        // 3- Request gonderip, donen response'i kaydetme
        Response response = given().spec(specJsonPlaceholder).contentType(ContentType.JSON)
                            .when().body(requestBodyMap)
                            .put("{pp1}/{pp2}");


        // 4- Assertions

        // expected : Response body <=======> actual : response
        //         Map              <=======>      Response

        // Assertion yapabilmemiz icin response'i Map'e cevirmem gerek (De-Serialization)
        Map<String, Object> responseMap = response.as(HashMap.class);
        assertEquals(expectedData.get("title"),responseMap.get("title"));
        assertEquals(expectedData.get("body"),responseMap.get("body"));
        assertEquals(expectedData.get("userId"),responseMap.get("userId"));
        assertEquals(expectedData.get("id"),responseMap.get("id"));


    }
}

/*
    REST API’da istenen veri türüyle işlem yapabilir, ancak JSON veri tipi ile diger data turlerine gore çok
    daha düşük boyutlarda veri kullanildigindan genellikle JSONObject kullanimi tercih edilir.

    Java objelerini API sorgulari yapmak uzere Json objesine cevirmeye **Serialization** denir.

    Işlem yaptığımız nesneyi, sınıfı saklamak yada transfer etmek istediğimiz formata dönüştürme
    işlemine **Serialization** denir.

    Verilen Json objesini testlerimizde kullanmak uzere Java objesine cevirmeye ise **De-Serialization**
    denir.

    JSONObject key-value ikililerini kullandigi icin De-Serialization islemi icin Java’dan kullanacagimiz en
    uygun data turu Map’tir.

    Olusturacagimiz Request body veya expected datayi direk Map olarak olusturabiliriz.

    Sorgumuz sonucunda donen response objesini De-Serialization ile Map’e cevirmek icin Gson
    kutuphanesinden yararlanabiliriz. (Bunun icin Gson dependency’yi pom xml’e eklemeliyiz).

    Map<String , Object> responseMap = response.as(HashMap.class);


    Map’i JSONObject’e cevirmek icin de Gson Class’indan yardim aliriz. Gson Class’indaki metodlari
    kullanmak icin once o Class’dan bir object olustururuz.

    Gson gson = new Gson();
    String jsonFromJavaObject = gson.toJson(actualDataMap);

    Olusturdugumuz gson objesi ile gson.toJson(actualDataMap); metodunu kullanarak, map’i
    JSONObject olarak kullanabilecegimiz String formatina ceviririz.

 */