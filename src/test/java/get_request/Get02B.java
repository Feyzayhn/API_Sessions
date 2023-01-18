package get_request;

import base_url.ReqresBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get02B extends ReqresBaseUrl {
    
      /*
   Given
       https://reqres.in/api/users/23
   When
       User send a GET Request to the url
   Then
       HTTP Status code should be 404
   And
       Status Line should be HTTP/1.1 404 Not Found
   And
       Server is "cloudflare"
   And
       Response body should be empty

*/

    @Test
    public void get03() {

        spec.pathParams("first","users","second",23);

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint(); // sonucun { } bu sekilde bos body gelmesi gerekiyordu ve geldi

                                /* then den sonraki dogrulama asamalarini yapiyoruz
                                   bu dogrulamalarin hepsini bir onceki class da oldugu gibi
                                   response uzerinden de tek seferde yapabiliriz*/
        System.out.println(response.statusCode()); // 404 ü verir
        assertEquals(404,response.statusCode()); // burada dogrulama yaptik
        assertEquals("HTTP/1.1 404 Not Found",response.statusLine());
        assertEquals("cloudflare",response.getHeader("Server"));
        assertEquals(2,response.asString().replaceAll("\\s","").length()); //tum spaceleri sildik



    }
}
