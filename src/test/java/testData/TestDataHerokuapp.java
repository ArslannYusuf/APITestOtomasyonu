package testData;

import org.json.JSONObject;

public class TestDataHerokuapp {

    public static JSONObject jsonRequestBodyOlustur() {

        JSONObject requestBody = new JSONObject();
        JSONObject bookingDatesBody = new JSONObject();

        bookingDatesBody.put("checkin", "2021-06-01");
        bookingDatesBody.put("checkout", "2021-06-10");

        requestBody.put("firstname", "Yusuf");
        requestBody.put("lastname", "Leon");
        requestBody.put("totalprice", 3500);
        requestBody.put("depositpaid", false);
        requestBody.put("bookingdates", bookingDatesBody);
        requestBody.put("additionalneeds", "wi-fi");

        return requestBody;
    }


/*
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
 */

    public static JSONObject jsonResponseBodyOlustur() {

        JSONObject responseBody = new JSONObject();
        JSONObject bookingBody = jsonRequestBodyOlustur();

        responseBody.put("bookingid", 24);
        responseBody.put("booking", bookingBody);

        return responseBody;
    }
/*
        Response Body
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


}