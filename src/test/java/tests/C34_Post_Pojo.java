package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.pojosHavaDurumu.*;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C34_Post_Pojo {
    /*
        https://api.openweathermap.org/data/2.5/weather?q=London&appid=f4ffe3b2ef1fcb3600ab1d7fbc88c2f0 url’ine
        bir POST request gonderdigimizde donen response’un asagidaki body’ye sahip oldugunu test ediniz

        {
         "coord": {
             "lon": -0.1257,
             "lat": 51.5085
         },
         "weather": [
             {
                 "id": 803,
                 "main": "Clouds",
                 "description": "broken clouds",
                 "icon": "04n"
             }
         ],
         "base": "stations",
         "main": {
             "temp": 281.14,
             "feels_like": 277.78,
             "temp_min": 279.84,
             "temp_max": 282.18,
             "pressure": 990,
             "humidity": 84
         },
         "visibility": 10000,
         "wind": {
             "speed": 6.17,
             "deg": 240
         },
         "clouds": {
             "all": 75
         },
         "dt": 1704053997,
         "sys": {
             "type": 2,
             "id": 2075535,
             "country": "GB",
             "sunrise": 1704009974,
             "sunset": 1704038403
         },
         "timezone": 0,
         "id": 2643743,
         "name": "London",
         "cod": 200
        }

        NOTES --> Toplamda 7 adet JSon Object mevcut, yani 7 adet Pojo class olsuturmaliyim (pojos.pojosHavaDurumu)
        https://www.jsonschema2pojo.org/  sitesini kullaniyorum
     */

    @Test
    public void test01() {
        // 1- Request body ve end point hazirlama
        String url = "https://api.openweathermap.org/data/2.5/weather?q=London&appid=f4ffe3b2ef1fcb3600ab1d7fbc88c2f0";

        // 2- Expected data hazirlama

        // Alt Pojo'lari hazirlamaliyim
        Coord coordPojo = new Coord(-0.1257f,51.5085f);  // float data type old. icin "f" eklemeyi unutma ;)

        List<Weather> weatherList = new ArrayList<>();  // Weather bir List old. icin once List yapip sonra icine Weather datalarini eklemeliyim
        Weather weatherPojo = new Weather(803,"Clouds","broken clouds","04n");
        weatherList.add(weatherPojo);

        Main mainPojo = new Main(281.14f,277.78f,279.84f,282.18f,990,84);

        Wind windPojo = new Wind(6.17f,240);

        Clouds cloudsPojo = new Clouds(75);

        Sys sysPojo= new Sys(2,2075535,"GB",1704009974,1704038403);


        // Ana Pojo'mu hazirlamaliyim
        PojoHavaDurumu expectedResponseBody =
                new PojoHavaDurumu(coordPojo,
                                    weatherList,
                                    "stations",
                                    mainPojo,
                                    10000,
                                    windPojo,
                                    cloudsPojo,
                                    1704053997,
                                    sysPojo,
                                    0,
                                    2643743,
                                    "London",
                                    200);


        // 3- Request gonderip, donen response'i kaydetme
        Response response = given()
                            .when()
                            .post(url);

        // site canli old. icin degerler degismekte...


        // 4- Assertions
        // Expected Data Pojo       <======>    response Pojo
        // expectedResponseBody     <======>    responsePojo

        PojoHavaDurumu responsePojo = response.as(PojoHavaDurumu.class);
        // response'i Pojo'ya cevirdigimde tum bilgileri tam getirirse
        // responsePojo'yu assertion'da kullanabilirim
        // Eger donen degerler icerisinde "null" donerse, response'i JsonPath yapip assertion'da kullanabilirim

        /*
        System.out.println(responsePojo);
        pojos.pojosHavaDurumu.PojoHavaDurumu@e8ea697[
            coord=pojos.pojosHavaDurumu.Coord@74dbb1ee[lon=-0.1257,lat=51.5085],
            weather=[pojos.pojosHavaDurumu.Weather@3efedc6f[
            id=500,main=Rain,description=light rain,icon=10n]],base=stations,
            main=pojos.pojosHavaDurumu.Main@45bf6f39[temp=281.28,feelsLike=<null>,tempMin=<null>,tempMax=<null>,
            pressure=990,humidity=83],visibility=10000,wind=pojos.pojosHavaDurumu.Wind@6c42f2a1[speed=7.2,deg=240],
            clouds=pojos.pojosHavaDurumu.Clouds@17a703f5[all=75],dt=1704056147,
            sys=pojos.pojosHavaDurumu.Sys@5ff2b8ca[type=2,id=2075535,country=GB,sunrise=1704009974,sunset=1704038403],
            timezone=0,id=2643743,name=London,cod=200]

            NOTES:
            goruldugu gibi <null> degerler mevcut, dolayisiyla jsonPath() kullanmaliyim !!!
 */

        JsonPath responseJsonPath = response.jsonPath();
        // Expected Data Pojo  <======> responseJsonPath
        assertEquals(expectedResponseBody.getCoord().getLon(),responseJsonPath.get("coord.lon"));
        assertEquals(expectedResponseBody.getCoord().getLat(),responseJsonPath.get("coord.lat"));
        assertEquals(expectedResponseBody.getBase(),responseJsonPath.get("base"));
        assertEquals(expectedResponseBody.getSys().getCountry(),responseJsonPath.get("sys.country"));
        assertEquals(expectedResponseBody.getName(),responseJsonPath.get("name"));


        // site canli hava durumu degerlerini dondurdugu icin degismeyen degerler uzerinden assertion'lari yaptim
    }
}
