package pojos;

public class PojoDummyExampleData {

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

    private Integer id;
    private String employeeName;
    private Integer employeeSalary;
    private Integer employeeAge;
    private String profileImage;

    /**
     * No args constructor for use in serialization
     *
     */
    public PojoDummyExampleData() {
    }

    /**
     *
     * @param employeeName
     * @param employeeAge
     * @param id
     * @param profileImage
     * @param employeeSalary
     */
    public PojoDummyExampleData(Integer id, String employeeName, Integer employeeSalary, Integer employeeAge, String profileImage) {
        super();
        this.id = id;
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
        this.employeeAge = employeeAge;
        this.profileImage = profileImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(Integer employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public Integer getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(Integer employeeAge) {
        this.employeeAge = employeeAge;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PojoDummyExampleData.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("employeeName");
        sb.append('=');
        sb.append(((this.employeeName == null)?"<null>":this.employeeName));
        sb.append(',');
        sb.append("employeeSalary");
        sb.append('=');
        sb.append(((this.employeeSalary == null)?"<null>":this.employeeSalary));
        sb.append(',');
        sb.append("employeeAge");
        sb.append('=');
        sb.append(((this.employeeAge == null)?"<null>":this.employeeAge));
        sb.append(',');
        sb.append("profileImage");
        sb.append('=');
        sb.append(((this.profileImage == null)?"<null>":this.profileImage));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}