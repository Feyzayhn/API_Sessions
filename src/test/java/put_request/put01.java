package put_request;
import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;


public class put01 extends JsonplaceholderBaseUrl {

    /*
    Given
     1) https://jsonplaceholder.typicode.com/todos/198   // bu id git postmandeki kayitli halini asagidaki gibi
     2) {
             "userId": 21,
             "title": "Wash the dishes",    // bu sekilde guncelle
             "completed": false
           }
    When
I send PUT Request to the Url
 Then
      Status code is 200
      And response body is like   {
                   "userId": 21,
                   "title": "Wash the dishes",  // bize bu sekilde donmesi gerekiyor bunu da dogrulayacagiz
                   "completed": false
                   "id": 198
                  }
 */

    @Test
    public void put01() {

        //Set the url
        spec.pathParams("1","todos","2",198);

        //Set the expected data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String,Object> expectedData =obj.expectedDataMethod(21,"Wash the dishes",false); // bu bilgileirn old bir map getirecek
        System.out.println("expectedData = " +expectedData);

        // Send the request and Get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{1}/{2}");
        response.prettyPrint();                                                                     // bu url de put yapacagiz

        // Do Assertion
        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData : " +actualData);

        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));


    }
}
