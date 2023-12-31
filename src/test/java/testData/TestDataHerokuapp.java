package testData;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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


    public static Map<String, Object> requestBodyMapOlustur() {
        Map<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("firstname", "Yusuf");
        requestBodyMap.put("lastname", "Leon");
        requestBodyMap.put("totalprice", 3500.0);
        requestBodyMap.put("depositpaid", false);
        requestBodyMap.put("bookingdates", bookingDatesMapOlustur());
        requestBodyMap.put("additionalneeds", "wi-fi");

        return requestBodyMap;
    }


    // inner map icin method olsuturuyorum --> yukaridaki methodda direkt kullanmak icin
    public static Map<String, Object> bookingDatesMapOlustur() {
        Map<String, Object> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin", "2021-06-01");
        bookingdatesMap.put("checkout", "2021-06-10");

        return bookingdatesMap;
    }
    /*
    Request body
        {
        "firstname" : "Ahmet",
        "lastname" : “Bulut",
        "totalprice" : 500,
        "depositpaid" : false,
        "bookingdates" : {
            "checkin" : "2021-06-01",
            "checkout" : "2021-06-10"
            },
        "additionalneeds" : "wi-fi"
        }
     */


    public static Map<String,Object> responseBodyMapOlustur(){
        Map<String ,Object> responseBodyMap = new HashMap<>();
        responseBodyMap.put("bookingid", 24);
        responseBodyMap.put("booking", requestBodyMapOlustur());

        return responseBodyMap;
    }
    /*
    Response Body // expected data
        {
        "bookingid": 24,
        "booking": {
            "firstname": "Ahmet",
            "lastname": "Bulut",
            "totalprice": 500,
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