package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.*;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;
import static org.hamcrest.Matchers.*;

public class Get06 extends RestfulBaseUrl {

   /*
        Given
            https://restful-booker.herokuapp.com/booking/2325
        When
            User send a GET request to the URL    *** 3.ADIM BURDAN BASLİYOR COZUMU
        Then
            HTTP Status Code should be 200        *** 4.ADIM COZUMU BURDAN SONRASI
        And
            Response content type is "application/json"
        And
            Response body should be like;
         {
    "firstname": "Bradley",
    "lastname": "Pearson",
    "totalprice": 132,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2022-10-27",
        "checkout": "2022-11-07"
    },
    "additionalneeds": "None"
}
     */

    @Test
    public void get01() {

        //1.Adim Set the URL,
        spec.pathParams("first","booking","second",2325);

        //2.Adim Set The Expected Data (Put,Patch,Post) şuan olmadıgı icin bu adimi geciyoruz

        //3.Adim Send the request and Get Response (talep gonder ve cevap al)
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4.ADİM Do Assertion

        // 1.YOL
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",equalTo("Bradley"),
                        "lastname",equalTo("Pearson"),
                        "totalprice",equalTo(132),
                        "depositpaid",equalTo(false),
                        "bookingdates.checkin",equalTo("2022-10-27"),
                        "bookingdates.checkout",equalTo("2022-11-07"),
                        "additionalneeds",equalTo("None"));


        // 2.YOL  JsonPath class'inin kullanimi
        JsonPath json=response.jsonPath();
        assertEquals("Bradley",json.getString("firstname"));
        assertEquals("Pearson",json.getString("lastname"));
        assertEquals(132,json.getInt("totalprice"));
        assertFalse(json.getBoolean("depositpaid"));
        assertEquals("2022-10-27",json.getString("bookingdates.checkin"));
        assertEquals("2022-11-07",json.getString("bookingdates.checkout"));
        assertEquals("None",json.getString("additionalneeds"));


        // 3.Yol : Soft Assertion
        // softAssert class'i 3 adimda kullanilir

        // 1.Adim Obje olusturma
        SoftAssert softAssert = new SoftAssert();

        // 2.Adim Do Assertion ( dogrulama yapma)

        softAssert.assertEquals(json.getString("firstname"),"Bradleyx","First Name Hatali");
        softAssert.assertEquals(json.getString("lastname"),"Pearson","Last Name Hatali");
        softAssert.assertEquals(json.getInt("totalprice"),132,"Total Price Hatali");
        softAssert.assertEquals(json.getBoolean("depositpaid"),false,"depositpaid Hatali");
        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2022-10-27","Check In Tarihi Hatali");
        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2022-11-07","Check out Tarihi Hatali");
        softAssert.assertEquals(json.getString("additionalneeds"),"None","Additionalneeds Hatali");
        softAssert.assertAll();

        /*
         3.Adim Dogrulama islemleri sonunda softAssert.assertAll()
         diyerek yaptigimiz tum dogrulama islemlerinin kontrol edilmesini sagliyoruz

        Eger islemin sonunda softAssert.assertAll() kullanmaz isek taleplerimiz
        hatalı bile olsa testimiz pass olacaktir.

         */



    }
}
