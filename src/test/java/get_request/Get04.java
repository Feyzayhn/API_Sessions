package get_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class Get04 extends JsonplaceholderBaseUrl {

    /*
 Given
      https://jsonplaceholder.typicode.com/todos
 When
   I send a GET request to the Url
 And
     Accept type is "application/json"
 Then
     HTTP Status Code should be 200
 And
     Response format should be "application/json"
 And
     There should be 200 todos
 And
     "quis eius est sint explicabo" should be one of the todos title
 And
     2, 7, and 9 should be among the userIds
 */



    // not tek parametre oldugun da URL de  pathParam() kullanilir
    // birden fazla parametre oldugunda URL de pathParams() kullanilir

    @Test
    public void get01() {

        //1.Adim Set the URL,

        spec.pathParam("first","todos");

        //2.Adim Set The Expected Data (Put,Patch,Post) şuan olmadıgı icin bu adimi geciyoruz

        //3.Adim Send the request and Get Response
        Response response = given().spec(spec).when().accept(ContentType.JSON).get("/{first}");
        response.prettyPrint();

        // 4.ADİM Do Assertion
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("id",hasSize(200),  // id ye gore al kac adet varsa dogrula 200 adet mi
                        "title",hasItem("quis eius est sint explicabo"), // bir basligin icinde bu yazi geciyor mu
                        "userId",hasItems(2,7,9));




    }
}
