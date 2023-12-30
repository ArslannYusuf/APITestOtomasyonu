import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class C10_JsonPathKullanimi {
    @Test
    public void method01() {

        JSONObject kisiBilgileriJsonObj = new JSONObject();

        JSONObject adresJsonObj = new JSONObject();

        JSONArray telefonBilgileriArr = new JSONArray();

        JSONObject cepTelJsonObj = new JSONObject();

        JSONObject evTelJsonObj = new JSONObject();

        adresJsonObj.put("streetAddress", "Yenimahalle kurtulus cad");
        adresJsonObj.put("city", "Ankara");
        adresJsonObj.put("postalCode", "06100");

        cepTelJsonObj.put("type", "iPhone");
        cepTelJsonObj.put("number", "0123-4567-8888");
        evTelJsonObj.put("type", "home");
        evTelJsonObj.put("number", "0123-4567-0910");

        telefonBilgileriArr.put(cepTelJsonObj);
        telefonBilgileriArr.put(evTelJsonObj);


        kisiBilgileriJsonObj.put("firstName", "John");
        kisiBilgileriJsonObj.put("lastName", "Doe");
        kisiBilgileriJsonObj.put("age", "50");
        kisiBilgileriJsonObj.put("adress", adresJsonObj);
        kisiBilgileriJsonObj.put("phoneNumbers", telefonBilgileriArr);

        // JSOnPath kullanmadan bu sekilde yazdim
        System.out.println(kisiBilgileriJsonObj);
        System.out.println("firstName = " + kisiBilgileriJsonObj.get("firstName"));
        System.out.println("lastname = " + kisiBilgileriJsonObj.get("lastName"));
        System.out.println("cadde = " + kisiBilgileriJsonObj.getJSONObject("adress").get("streetAddress"));
        System.out.println("city = " + kisiBilgileriJsonObj.getJSONObject("adress").get("city"));
        System.out.println("number = " + kisiBilgileriJsonObj.getJSONArray("phoneNumbers").getJSONObject(0).get("number"));



    }
}

/*
    System.out.println(kisiBilgileriJsonObj); ==> ile almis oldugum ciktiyi ;
    https://jsonpath.com/ sitesi uzerinden Jpath formatina cevirme yapabilirim

    JSONPath input'u icerisine;
    $  ==> yazdigim da (hersey) anlamina gelir ve tüm herseyi Json formatina göre dizayn eder
    firstName ==> yazdigimda John
 */
