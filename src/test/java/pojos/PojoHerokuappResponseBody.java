package pojos;

public class PojoHerokuappResponseBody {
    /*
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

    // 1) Tum variable’lari "private" olarak olusturalim
    //   booking icin PojoHerokuappRequestBody class'inda datalarimi olusturmustum
    private int bookingid;
    private PojoHerokuappRequestBody booking;



    // 2) Tum variable’lar icin getter( ) and setter( ) metodlari olusturalim
    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public PojoHerokuappRequestBody getBooking() {
        return booking;
    }

    public void setBooking(PojoHerokuappRequestBody booking) {
        this.booking = booking;
    }



    // 3) Tum parametreleri iceren bir constructor olusturalim
    public PojoHerokuappResponseBody(int bookingid, PojoHerokuappRequestBody booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }



    // 4) Default constructor (parametresiz) olusturalim
    public PojoHerokuappResponseBody() {
    }



    // 5) toString( ) metodu olusturalim
    @Override
    public String toString() {
        return "PojoHerokuappResponse{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
