package get_request;
import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get12Pojo extends RestfulBaseUrl {

    /*
     Given
         https://restful-booker.herokuapp.com/booking/18
     When
         I send GET Request to the URL
     Then
         Status code is 200
     And
         Response body is like:
                        {
                             "firstname": "Dane",
                             "lastname": "Combs",
                             "totalprice": 111,
                             "depositpaid": true,
                             "bookingdates": {
                                 "checkin": "2018-01-01",
                                 "checkout": "2019-01-01"
                             },
                             "additionalneeds": "Breakfast"
                         }
  */

    @Test
    public void get12Pojo() {
        //Set the url
        spec.pathParams("1","booking","2",18);

        //Set the Expected Data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01","2019-01-01");
                                                          // Inner pojo dan obje olusturup degerlerini atadik

        BookingPojo expectedData = new BookingPojo("Dane","Combs",111,true,bookingDatesPojo,"Breakfast");
        System.out.println("expectedData = " + expectedData); // Outer pojo dan obje olusturup degerlerini atadik

        //Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{1}/{2}");
        response.prettyPrint();

        // DO ASSERTION
        BookingPojo actualData=response.as(BookingPojo.class); // RESPONSE'I BookingPojo tipine cevirdi ve BookingPojo data turune atadi
        System.out.println("actualData = " + actualData);      //UNUTMA HER CLASS BİR DATA TURUDUR
                                                               // alinda gene JSON formatini Java da bir data turune cevirdi
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());

        // 1.YOL
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());


        // 2.YOL ==>Tavsiye edilen
        assertEquals(bookingDatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());
                     // bu kismi direk inner dan aldik
    }
}
