package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class Get09 extends RestfulBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/91
    When
        I send GET Request to the url
    Then
        Response body should be like that;
  {
    "firstname": "Sally",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2013-02-23",
        "checkout": "2014-10-23"
    },
    "additionalneeds": "Breakfast"
  }
 */


    @Test
    public void get09() {

        // Set the URL
        spec.pathParams("first","booking","second",91);

        //Set The Expected Data
        Map<String,String> bookingdatesMap = new HashMap<>();
                                  // "bookingdates" kısmı icin olusturdugumuz map  "checkin" - "checkout" datalari icin
        bookingdatesMap.put( "checkin","2013-02-23");
        bookingdatesMap.put( "checkout","2014-10-23");
                                  /*
                                    "bookingdates": {
                                         "checkin": "2013-02-23",
                                         "checkout": "2014-10-23"
                                      },  bu kısmın map icini kucuk map olusturduk*/


        Map<String,Object> expectedData = new HashMap<>(); // buyuk map yukardaki JSON tablosunun tamami
        expectedData.put("firstname","Sally");
        expectedData.put("lastname", "Brown");
        expectedData.put("totalprice", 111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingdatesMap); // bookingdates'in value degeri kucuk map in icinden geliyor
        expectedData.put("additionalneeds","Breakfast");

        System.out.println(expectedData); // sonucta bize map icinde bir map daha verecek

        // Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        //Do Assertion
        Map<String,Object> actualData = response.as(HashMap.class); //JSON map e donusturuldu as() methodu ile
        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(bookingdatesMap.get("checkin"),((Map)(actualData.get("bookingdates"))).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"),((Map)(actualData.get("bookingdates"))).get("checkout"));
        //Key-Value ikilileri String-Object seklinde oldugundan Bookingdata value kısmını casting ile Map yaptık.
                                                   /*
                                                   burada ikinci veriyi girerken objeydi
                                                   1. kisim yani String kismi map oldugu icin
                                                   obje olan kisminida map e cevirdik ki karsilastirma yapabilsin
                                                   bunun icinde  basina map yazdik
                                                   */

        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));

    }
}

