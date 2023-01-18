package get_request;

import base_url.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class Get10 extends GoRestBaseUrl {

    /*
   Given
       https://gorest.co.in/public/v1/users/2986
   When
       User send GET Request to the URL
   Then
       Status Code should be 200
   And
       Response body should be like
    {
  "meta": null,
    "data": {
        "id": 2986,
        "name": "Navin Talwar",
        "email": "navin_talwar@mclaughlin.name",
        "gender": "male",
        "status": "inactive"
            }
     }
*/


    @Test
    public void get10() {

        // Set the URL
        spec.pathParams("first", "users", "second", 2986);

        // Expected Data

        GoRestTestData obj = new GoRestTestData(); //  GoRestTestData bu class dan obje olusturduk
        Map<String, String> dataKeyMap =
                obj.dataKeyMap("Navin Talwar", "navin_talwar@mclaughlin.name", "male", "inactive");
                                           // bu method (dataKeyMap()) bize map dondugu icin bir map e atiyoruz
                                           // icindeki datalari yaziyoruz bunu bize  GoRestTestData ya gonderiyoruz
        
        Map<String, Object> expectedData = obj.expectedDataMethod(null, dataKeyMap);
        System.out.println(expectedData);

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        Map<String,Object> actualData = response.as(HashMap.class); //JSON map e donusturuldu as() methodu ile
        System.out.println("actualData = " +actualData);
        assertEquals(expectedData.get("meta"),actualData.get("meta"));
        assertEquals(dataKeyMap.get("name"),((Map)actualData.get("data")).get("name"));
        assertEquals(dataKeyMap.get("email"),((Map)actualData.get("data")).get("email"));
        assertEquals(dataKeyMap.get("gender"),((Map)actualData.get("data")).get("gender"));
        assertEquals(dataKeyMap.get("status"),((Map)actualData.get("data")).get("status"));

    }
}
