import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C11_Post_JsonPathIleBodyTesti {
    @Test
    public void test01() {

        /*
        https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST request gonderdigimizde

        {
        "firstname" : "Yusuf",
        "lastname" : “Leon",
        "totalprice" : 500,
        "depositpaid" : false,
        "bookingdates" : {
                "checkin" : "2023-01-10",
                "checkout" : "2023-01-20"},
        "additionalneeds" : "wi-fi"
        }

        donen Response’un,
            status code’unun 200,
            ve content type’inin application-json,
            ve response body’sindeki
            "firstname“in,"Ahmet",
            ve "lastname“in, "Bulut",
            ve "totalprice“in,500,
            ve "depositpaid“in,false,
            ve "checkin" tarihinin 2023-01-10
            ve "checkout" tarihinin 2023-01-20
            ve "additionalneeds“in,"wi-fi"
            oldugunu test edin
         */


        // 1- Request body ve end point hazirlama
        String url = "https://restful-booker.herokuapp.com/booking";

        JSONObject requestBody = new JSONObject();
        JSONObject rezervasyonTarihleriJson = new JSONObject();

        rezervasyonTarihleriJson.put("checkin", "2023-01-10");
        rezervasyonTarihleriJson.put("checkout", "2023-01-20");

        requestBody.put("firstname", "Yusuf");
        requestBody.put("lastname", "Leon");
        requestBody.put("totalprice", 500);
        requestBody.put("depositpaid", false);
        requestBody.put("bookingdates", rezervasyonTarihleriJson);
        requestBody.put("additionalneeds", "wi-fi");


        // 2- Expected data hazirlama
        // örnek body gibi olmasini test ediniz demedigi icin olusturmuyorum


        // 3- Request gonderip, donen response'i kaydetme
        Response response = given().contentType(ContentType.JSON)
                            .when().body(requestBody.toString())
                            .post(url);


        // 4- Assertion
        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("booking.firstname", equalTo("Yusuf"),
                        "booking.lastname", equalTo("Leon"),
                        "booking.totalprice", equalTo(500),
                        "booking.depositpaid", equalTo(false),
                        "booking.bookingdates.checkin",equalTo("2023-01-10"),
                        "booking.bookingdates.checkout",equalTo("2023-01-20"),
                        "booking.additionalneeds",equalTo("wi-fi"));

    }
}