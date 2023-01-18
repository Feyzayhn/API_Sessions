package post_request;

import base_url.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.RestfulTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Post02 extends RestfulBaseUrl {

     /*
   Given
       1) https://restful-booker.herokuapp.com/booking   bu id ye git
       2) {
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 11111,
            "depositpaid": true,         // burasi expectedData olacak ve assert yaparken direk firstname... gibi yazacaz
            "bookingdates": {
                "checkin": "2021-09-09",
                "checkout": "2021-09-21"
             }
          }
   When
       I send POST Request to the Url
   Then
       Status code is 200
       And response body should be like
       {
                       "bookingid": 5315,
                       "booking": {               // burasi actualData oluyor ve alttakilere ulasmak icin booking deyip sonra firstname..derim
                           "firstname": "John",
                           "lastname": "Doe",
                           "totalprice": 11111,
                           "depositpaid": true,
                           "bookingdates": {
                                    "checkin": "2021-09-09",
                                    "checkout": "2021-09-21"
                                               }
                                           }
                                        }




           BU SORU TARZI ICIN BIR OUTER MAP BIRDE INNER MAP OLUSTURULUR TEST DATA package inda olusturuyorum
*/

    @Test
    public void post02() {

        // Set the url
        spec.pathParams("1", "booking");

        //Set the expected data
        RestfulTestData obj = new RestfulTestData();
        Map<String, String> bookingdatesMap = obj.bookingdatesMethod("2021-09-09", "2021-09-21");
        // once inner map olusturduk ve gonderdik

        Map<String, Object> expectedData =
                obj.expectedDataMethod("John", "Doe", 11111, true, bookingdatesMap);
        // burada outer map i olusturdum
        System.out.println("expectedData :" +expectedData);

        //Send the Request and Get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{1}");
        // api ye post u gonderdik ve bize response ile dondu JSON formatinda dondu
        response.prettyPrint();

        // Do ASSERTİON

        Map<String, Object> actualData = response.as(HashMap.class); //JSON map e donusturuldu as() methodu ile
        System.out.println("actualData :" +actualData);

        assertEquals(expectedData.get("firstname"), ((Map) actualData.get("booking")).get("firstname"));
        assertEquals(expectedData.get("lastname"), ((Map) actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"), ((Map) actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), ((Map) actualData.get("booking")).get("depositpaid"));
                                                        //booking ile gitmeliyim   // bu kisimlara gidebilmek icin

        assertEquals(bookingdatesMap.get("checkin"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));



           /*
           bookingdatesMap.get("checkin") BURASİ EXPECTED DATA
           DİGER ASAMADA İSE ONCE İCERDEKİ objeyi map e cevirdik
           sonra checkin kismi ile yeni bir  obje oldugu icin hepsini birlikte tekrar map e cevirdik


           actualData.get("booking") ==>> bu obje
           ((Map)actualData.get("booking")) ==>> bu sekilde map
           ((Map)actualData.get("booking")).get("bookingdates")) ==>> bu hali ile yeni bir obje oldu
           ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout")) ==>> bu sekilde ise tekrar map oldu

           get()  degerleri bize hep obje doner bu sebeple ic ice istedigimiz kadar map yapabiliriz
            */


        assertEquals(expectedData.get("bookingdates.checkin"),((Map) actualData.get("booking")).get("bookingdates.checkin"));
        assertEquals(expectedData.get("bookingdates.checkout"),((Map) actualData.get("booking")).get("bookingdates.checkout"));
                                                               // bu sekilde de calisiyor

    }
}
