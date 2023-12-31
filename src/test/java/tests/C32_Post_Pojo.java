package tests;

import baseUrl.BaseUrlHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.PojoHerokuappBookingdates;
import pojos.PojoHerokuappRequestBody;
import pojos.PojoHerokuappResponseBody;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C32_Post_Pojo extends BaseUrlHerokuapp {
    /*
        https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST request
gonderdigimizde donen response’un id haric asagidaki gibi oldugunu test edin.

        Request body
            {
            "firstname" : "Yusuf",
            "lastname" : “Leon",
            "totalprice" : 3500,
            "depositpaid" : false,
            "bookingdates" : {
                "checkin" : "2021-06-01",
                "checkout" : "2021-06-10"
                },
            "additionalneeds" : "wi-fi"
            }


        Response Body // expected data
            {
            "bookingid": 24,
            "booking": {
                "firstname": "Yusuf",
                "lastname": "Leon",
                "totalprice": 3500,
                "depositpaid": false,
                "bookingdates": {
                    "checkin": "2021-06-01",
                    "checkout": "2021-06-10"
                    },
                "additionalneeds": "wi-fi"
                }
            }
     */

    @Test
    public void test01(){

        // 1- Request body ve end point hazirlama
        specHerokuapp.pathParam("pp1","booking");

        PojoHerokuappBookingdates bookingdatesPojo = new PojoHerokuappBookingdates("2021-06-01","2021-06-10");
        PojoHerokuappRequestBody requestBodyPojo = new PojoHerokuappRequestBody("Yusuf","Leon",
                                                3500,false,bookingdatesPojo,"wi-fi");

        // 2- Expected data hazirlama
        bookingdatesPojo= new PojoHerokuappBookingdates("2021-06-01","2021-06-10");

        PojoHerokuappRequestBody bookingPojo = new PojoHerokuappRequestBody("Yusuf","Leon",
                                                3500,false,bookingdatesPojo,"wi-fi");


        PojoHerokuappResponseBody expectedResponseBodyPojo = new PojoHerokuappResponseBody(24,bookingPojo);

       /*
        PojoHerokuappResponseBody{
            bookingid=24,
            booking=PojoHerokuappRequestBody{
                        firstname='Yusuf',
                        lastname='Leon',
                        totalprice=3500,
                        depositpaid=false,
                        bookingdates=PojoHerokuappBookingdates{
                                        checkin='2021-06-01',
                                        checkout='2021-06-10'},
                        additionalneeds='wi-fi'}
                        }
        */

        // 3- Request gonderip, donen response'i kaydetme
        Response response = given().spec(specHerokuapp).contentType(ContentType.JSON)
                            .when().body(requestBodyPojo)
                            .post("{pp1}");

        PojoHerokuappResponseBody responsePojo = response.as(PojoHerokuappResponseBody.class);


        // 4- Assertions
        // expectedResponseBodyPojo     <====>   responsePojo

        assertEquals(expectedResponseBodyPojo.getBooking().getFirstname(),
                responsePojo.getBooking().getFirstname());

        assertEquals(expectedResponseBodyPojo.getBooking().getLastname(),
                responsePojo.getBooking().getLastname());

        assertEquals(expectedResponseBodyPojo.getBooking().getTotalprice(),
                responsePojo.getBooking().getTotalprice());

        assertEquals(expectedResponseBodyPojo.getBooking().isDepositpaid(),
                responsePojo.getBooking().isDepositpaid()); // !! boolean variable'larin getter methodu "is" ile baslar

        assertEquals(expectedResponseBodyPojo.getBooking().getAdditionalneeds(),
                responsePojo.getBooking().getAdditionalneeds());

        assertEquals(expectedResponseBodyPojo.getBooking().getBookingdates().getCheckin(),
                responsePojo.getBooking().getBookingdates().getCheckin());

        assertEquals(expectedResponseBodyPojo.getBooking().getBookingdates().getCheckout(),
                responsePojo.getBooking().getBookingdates().getCheckout());
    }
}
