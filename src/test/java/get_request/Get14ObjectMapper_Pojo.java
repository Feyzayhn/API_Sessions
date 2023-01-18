package get_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get14ObjectMapper_Pojo extends JsonplaceholderBaseUrl {

    /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */

    // ObjectMapper + Pojo = En iyi Yontem

    @Test
    public void get14pojo() {

        // Set the url
        spec.pathParams("1", "todos", "2", 198);

        // Set the expected data
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(10, "quis eius est sint explicabo", true);

        // Send the Request and Ger the Response
        Response response = given().spec(spec).when().get("/{1}/{2}");

        // Do Assertion
       JsonPlaceHolderPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),JsonPlaceHolderPojo.class);

       assertEquals(200,response.getStatusCode());
       assertEquals(expectedData.getUserId(),actualData.getUserId());
       assertEquals(expectedData.getTitle(),actualData.getTitle());
       assertEquals(expectedData.getCompleted(),actualData.getCompleted());

    }
}
