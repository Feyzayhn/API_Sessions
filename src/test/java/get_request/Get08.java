package get_request;

import base_url.JsonplaceholderBaseUrl;
import groovy.xml.StreamingDOMBuilder;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get08 extends JsonplaceholderBaseUrl {

    //De-Serialization JSON data'yi Java objesine cevirme
    //Serialization: Java objesini Json formatina cevirme
    //De-Serialization: iki turlu yapacagiz
    //Gson: Google tarafindan uretilmistir
    //Object Mapper: Daha populer

    /*
    Given
       https://jsonplaceholder.typicode.com/todos/2
   When
       I send GET Request to the URL
   Then
       Status code is 200
       And "completed" is false
       And "userId" is 1
       And "title" is "quis ut nam facilis et officia qui"
       And header "Via" is "1.1 vegur"
       And header "Server" is "cloudflare"
       {
           "userId": 1,
           "id": 2,
           "title": "quis ut nam facilis et officia qui",
           "completed": false
       }

       //not javadan OBJE'nin dezavantaji yavastır bizi kisitlar her methodu bulamayiz
*/

    @Test
    public void get08() {

        // Set the URL
        spec.pathParams("first","todos","second",2);

        // Set The EXPECTED data ==> Payload ile karsilastirma
        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("userId",1);
        expectedData.put("id",2);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);
        System.out.println("expectedData " +expectedData);


        // Send the Request and Get the Response
        Response response =given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        // Do Assertion
         Map<String,Object> actualData = response.as(HashMap.class); // De-Serialization //JSON dan Map 'e donusturduk
           // String kismina id gibi keyleri                         // buradaki as() bu islemi yaptik burda siteden gelen kismi java sekline ceviriyor
           // Object kismina value degerleri atanir
         System.out.println("actualData " +actualData);
         assertEquals(expectedData.get("userId"),actualData.get("userId"));
         assertEquals(expectedData.get("id"),actualData.get("id"));
         assertEquals(expectedData.get("title"),actualData.get("title"));
         assertEquals(expectedData.get("completed"),actualData.get("completed"));
         assertEquals("1.1 vegur",response.header("Via"));
         assertEquals("cloudflare",response.header("Server"));
         assertEquals(200,response.statusCode());






    }

    @Test
    public void get08b() {

        // Set the URL
        spec.pathParams("first","todos","second",2);


        // Set The EXPECTED data ==> Payload ile karsilastirma
        JsonPlaceHolderTestData objJsonPlcHldr = new JsonPlaceHolderTestData(); // bu classdan bir obje olusturduk ve oradaki map'den verileir dondurduk
        Map<String,Object> expectedData =
                objJsonPlcHldr.expectedDataMethod(1,"quis ut nam facilis et officia qui",false);
                      /* Bu bize map dondurecegi icin bir map e atadik */
        System.out.println(expectedData);


        // Send the Request and Get the Response
        Response response =given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        // Do Assertion
        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData " +actualData);

        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals("1.1 vegur",response.header("Via"));
        assertEquals("cloudflare",response.header("Server"));
        assertEquals(200,response.statusCode());






    }
}
