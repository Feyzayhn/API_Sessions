package post_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05ObjectMapper_Map extends JsonplaceholderBaseUrl {
    /*
             Given
               1) https://jsonplaceholder.typicode.com/todos
               2) {
                     "userId": 55,
                     "title": "Tidy your room",
                     "completed": false
                   }
                I send POST Request to the Url
            Then
                Status code is 201
            And
                response body is like {
                                        "userId": 55,
                                        "title": "Tidy your room",
                                        "completed": false,
                                        "id": 201
                                        }
         */
    @Test
    public void post05ObjectMapper() throws IOException {

        // set the url
        spec.pathParam("1", "todos");

        // set the expected data
        //String jsonInString = "{\n" +
        //        "                                        \"userId\": 55,\n" +
        //        "                                        \"title\": \"Tidy your room\",\n" +
        //        "                                        \"completed\": false,\n" +
        //        "                                        \"id\": 201\n" +
        //        "                                        }";

        // string icine bir data koyduk


        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        String jsonInString = obj.expectedDataInString(55, "Tidy your room", false);
              // yukarda kapptigim yerin yerine bunu yaptik


        Map<String, Object> expectedData = new ObjectMapper().readValue(jsonInString, HashMap.class);
        //ObjectMapper() burada da String bir data giriyoruz onu HahMap e cevirdi readValue bunu okuyor

        System.out.println("expectedData = " + expectedData);


        // send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{1}");
        response.prettyPrint();

        // Do Assertion

        HashMap actualData = new ObjectMapper().readValue(response.asString(), HashMap.class); // json formatindaki datamı map e cevirdik
        System.out.println("actualData = " + actualData);
        assertEquals(201, response.getStatusCode());
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
    }
}