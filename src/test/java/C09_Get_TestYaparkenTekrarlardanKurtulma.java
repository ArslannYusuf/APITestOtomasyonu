import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class C09_Get_TestYaparkenTekrarlardanKurtulma {
    @Test
    public void test01(){
        /*
        https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request gonderdigimizde donen Response’un,
            status code’unun 200,
            ve content type’inin application-json,
            ve response body’sindeki
            "firstname“in, "Susan",
            ve "lastname“in, "Jackson",
            ve "totalprice“in, 1000,
            ve "depositpaid“in, false,
            ve "additionalneeds“in, bos birakilmadigini
        oldugunu test edin
         */


        // 1- Request body ve end point hazirlama
        String url ="https://restful-booker.herokuapp.com/booking/10";
        //getRequest'in ody'e ihtiyacı yok


        // 2- Expected data hazirlama
        // donen response'nin niteliği belirtilmedigi icin pass gecildi


        // 3- Request gonderip, donen response'i kaydetme
        Response response = given().when().get(url);

        // 4- Assertion
        response.prettyPrint();

        /*
        ikinci ontem ile yaptigimda bu testtimdeki yazdiklerim degismesin diye yoruma aldim

        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", Matchers.equalTo("Mary"))
                .body("lastname", Matchers.equalTo("Ericsson"))
                .body("totalprice", Matchers.lessThan(1000))
                .body("depositpaid", Matchers.equalTo(true))
                .body("additionalneeds", Matchers.notNullValue());
        */


        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("Sally"),
                        "lastname", equalTo("Brown"),
                        "totalprice",lessThan(1000),
                        "depositpaid", equalTo(false),
                        "additionalneeds", notNullValue());

    }
}
