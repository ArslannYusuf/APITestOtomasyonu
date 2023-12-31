package pojos;


public class PojoDummyExampleResponse {

    /*
       NOTES:
   --> bu class https://www.jsonschema2pojo.org/ sitesi uzerinden hazir olarak  olusturuldu

   --> 1- sitede package ve class ismini girmeliyim
   --> 2- response body'i ilgili kisma yapistirmaliyim
   --> 3- Source Type ==> JSON secmeliyim
   --> 4- Annotation style ==> None secmeliyim
   --> 5- Validatiton annotation ==> None secmeliyim
   --> 6- diger seceneklerden;
           - Include getter and setter
           - Include constructors
           - Include toString
           - Allow additional properties (bazi endPoint'lerde bazi key'leri verir bazende vermez ise bu secenek kullanilmali)

   -->  response body yukaridaki gibi ilgili siteye verildiginde key olan degerleri variable'a cevirirken camelCase kuralina gore olusturdu

   --> Response Body
           {
           "status": "success",
           "data": {
               "id": 3,
               "employee_name": "Ashton Cox",
               "employee_salary": 86000,
               "employee_age": 66,
               "profile_image": ""
               },
           "message": "Successfully! Record has been fetched."
           }
    */

    private String status;
    private PojoDummyExampleData data;
    private String message;

    /**
     * No args constructor for use in serialization
     *
     */
    public PojoDummyExampleResponse() {
    }

    /**
     *
     * @param data
     * @param message
     * @param status
     */
    public PojoDummyExampleResponse(String status, PojoDummyExampleData data, String message) {
        super();
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PojoDummyExampleData getData() {
        return data;
    }

    public void setData(PojoDummyExampleData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PojoDummyExampleResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("data");
        sb.append('=');
        sb.append(((this.data == null)?"<null>":this.data));
        sb.append(',');
        sb.append("message");
        sb.append('=');
        sb.append(((this.message == null)?"<null>":this.message));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}