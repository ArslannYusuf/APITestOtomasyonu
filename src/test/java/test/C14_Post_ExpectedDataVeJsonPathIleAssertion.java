package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C14_Post_ExpectedDataVeJsonPathIleAssertion {
    @Test
    public void test01() {

    /*
    https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST request
    gonderdigimizde donen response’un id haric asagidaki gibi oldugunu test edin.

    Request body
        {
        "firstname" : "Yusuf",
        "lastname" : “Leon",
        "totalprice" : 500,
        "depositpaid" : false,
        "bookingdates" : {
                "checkin" : "2021-06-01",
                "checkout" : "2021-06-10"},
        "additionalneeds" : "wi-fi"
        }

        Expected Response Body
        {
            "bookingid": 24,
            "booking": {
            "firstname": "Yusuf",
            "lastname": "Leon",
            "totalprice": 500,
            "depositpaid": false,
            "bookingdates": {
                    "checkin": "2021-06-01",
                    "checkout": "2021-06-10"},
            "additionalneeds": "wi-fi"
            }
        }
     */

        // 1- Request body ve end point hazirlama
        String url = "https://restful-booker.herokuapp.com/booking";

        JSONObject requestBody = new JSONObject();          // tüm body'i icerir
        JSONObject rezTarihleriJson = new JSONObject();     // body icerisinde ayrı Json objesi olan bookingdates datalarini icerir

        rezTarihleriJson.put("checkin", "2021-06-01");
        rezTarihleriJson.put("checkout", "2021-06-10");
        requestBody.put("firstname", "Yusuf");
        requestBody.put("lastname", "Leon");
        requestBody.put("totalprice", 500);
        requestBody.put("depositpaid", false);
        requestBody.put("bookingdates", rezTarihleriJson);
        requestBody.put("additionalneeds", "wi-fi");

        System.out.println(requestBody);


        // 2- Expected data hazirlama
        JSONObject expectedData = new JSONObject();
        expectedData.put("bookingid", 24);
        expectedData.put("booking", requestBody);

        System.out.println(expectedData);


        // 3- Request gonderip, donen response'i kaydetme
        Response response = given().contentType(ContentType.JSON)
                            .when().body(requestBody.toString())
                            .post(url);
        response.prettyPrint();

        // 4- Assertion
        JsonPath responseJsonPath = response.jsonPath();
        // ilk yazilan expected     ===> olusturdugum JSonObject --> expectedData
        // ikinci yazilan actual    ===> response --> responseJsonPath
        assertEquals(expectedData.getJSONObject("booking").get("firstname"),
                    responseJsonPath.get("booking.firstname"));

        assertEquals(expectedData.getJSONObject("booking").get("lastname"),
                    responseJsonPath.get("booking.lastname"));

        assertEquals(expectedData.getJSONObject("booking").get("totalprice"),
                    responseJsonPath.get("booking.totalprice"));

        assertEquals(expectedData.getJSONObject("booking").get("depositpaid"),
                    responseJsonPath.get("booking.depositpaid"));

        assertEquals(expectedData.getJSONObject("booking").get("additionalneeds"),
                    responseJsonPath.get("booking.additionalneeds"));

        assertEquals(expectedData.getJSONObject("booking").get("firstname"),
                    responseJsonPath.get("booking.firstname"));

        assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),
                    responseJsonPath.get("booking.bookingdates.checkin"));

        assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),
                    responseJsonPath.get("booking.bookingdates.checkout"));


/*
    NOTES:
    ExpectedData'lar birden fazla JSONObject icerisinde ise getJSONObject methodu ile son key'e ulasincaya kadar gidiyorum, son key'de get() methodu ile "value" yaziyorum
    Actualdata ise JsonPath() methodu sayesinde key'ler arasina .(nokta) koyarak tüm key'leri sirasiyla yaziyorum
 */

    }
}
