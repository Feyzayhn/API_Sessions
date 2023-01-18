package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // Bu annotation ayni levelde bilinmeyen verileri gormezden gelerek diger verilerin bu class tipinde Pojo clas'a cevrilmesine yariyor
public class BookingResponseBodyPojo {

    private Integer bookingid;
    public BookingPojo booking;

    public BookingResponseBodyPojo(Integer bookingid, BookingPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public BookingResponseBodyPojo() {
    }

    public Integer getBookingid() {

        return bookingid;
    }

    public void setBookingid(Integer bookingid) {

        this.bookingid = bookingid;
    }

    public BookingPojo getBooking() {

        return booking;
    }

    public void setBooking(BookingPojo booking) {

        this.booking = booking;
    }

    @Override
    public String toString() {
        return "BookingResponseBodyPojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
/*
"bookingid": 16,
   "booking" :{

   booking ve bookingdates pojo classlarini olusturmustuk asagidaki json icin hepsini kapsamasi icin
   bookingid class'inida olusturuyoruz bu class i ilk Post04Pojo class icin olusturduk
 */


/*
                                   "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        }
                                        "additionalneeds": "Breakfast"
                                        }
 */