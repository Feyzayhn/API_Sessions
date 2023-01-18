package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class Get05 extends RestfulBaseUrl {

    //     URI : URI, internette bir kaynak üzerine işaret edilmiş
    //           resim veya belge gibi klasik formata uygun bir karakter dizisidir.
    //     URL : URL, bir kaynağın örnek konumlayıcı veya tek halde kaynak bulucu olarak tanımlanabilir.

    /*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        User sends get request to the URL
    Then
        Status code is 200
    And
     Among the data there should be someone whose firstname is "Johhny" and lastname is "Dear"
 */


    @Test
    public void get01() {
        //1.Adim Set the URL,
        //https://restful-booker.herokuapp.com/booking?firstname=Ali&lastname=Cengiz bu URL 'le ulaşmak icin queryParams kullandik
        spec.pathParam("first","booking").queryParams("firstname","Ali","lastname","Cengiz");
                        /*
                     1.spec kismi-->https://restful-booker.herokuapp.com
                     2.spec kismina,pathparam() ile booking'i ekledi,queryParams() ile sorgulama parametrelerini ekledik
                          */
        //2.Adim Set The Expected Data (Put,Patch,Post) şuan olmadıgı icin bu adimi geciyoruz

        //3.Adim Send the request and Get Response
        Response response =given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // 4.ADİM Do Assertion
        assertEquals(200,response.getStatusCode());
        assertTrue(response.asString().contains("bookingid"));





    }
}
