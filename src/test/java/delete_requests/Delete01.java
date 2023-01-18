package delete_requests;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Delete01 extends JsonplaceholderBaseUrl {

    /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */

    @Test
    public void delete01() {

        // Set the url
        spec.pathParams("1", "todos", "2", 198);

        // Set The Expected Data
        Map<String, String> expectedData = new HashMap<>();
        // Send the request and get the Responsa
        Response response = given().spec(spec).when().delete("/{1}/{2}");

        // Do Assertion

        Map actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);

        assertEquals(expectedData, actualData);

        // 1.YOL
        assertEquals(expectedData, actualData);

        // 2.YOL
        assertTrue(actualData.isEmpty());

        // 3. YOL
        assertEquals(0, actualData.size());
    }
}
