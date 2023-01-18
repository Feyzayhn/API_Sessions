package get_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.*;

public class Get03 extends JsonplaceholderBaseUrl {


    // spec deyince ab-klimiza BaseURL gelsin yani .com a kadar olan kisim
    // when ve get ile de devaminda eklenen bir data varsa onlari ekleyerek yaziyoruz

 /*
      Given
          https://jsonplaceholder.typicode.com/todos/23
      When
          User send GET Request to the URL
      Then
          HTTP Status Code should be 200
      And
          Response format should be "application/json"
      And
          "title" is "et itaque necessitatibus maxime molestiae qui quas velit",
      And
          "completed" is false
      And
          "userId" is 2
   */

    @Test
    public void get01() {

        // i) Set the URL,
        spec.pathParams("first","todos","second",23); // baseUrl class'indan url cagirdik fakat
                                                      // son kisim todos ve 23 url de yok bunları dabu sekilde ekliyoruz

        //Set The Expected Data (Put,Patch,Post) şuan olmadıgı icin bu adimi geciyoruz

        //Send the request and Get Response
        Response response = given().spec(spec).when().get("/{first}/{second}"); // BaseURL in devamindaki datalari ekliyoruz
        response.prettyPrint(); //prettyPrint() bize icindeki datalari yazdirir

        // Do Assertion
        // 1. Yol (Hard Assert)
        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed",equalTo(false)).
                body("userId",equalTo(2));
                       //not bu çözümde bir body de hata aldiysak digerleri calismaz

        // 2. Yol Sadece body icerisinde gecerli bir (Soft Assert)
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed",equalTo(false),"userId",equalTo(2));
                       //not bu cözümde ise body tamamen calisir ve icindeki hatalari gosterir





         /*

           Soft assert testi gerçekleştirir ve assert başarısız olursa hata fırlatma gerçekleştirmez.
           Hard assert anında hata fırlatır, sonrasında test işlemine devam eder

         */
    }
}
