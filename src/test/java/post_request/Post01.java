package post_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Post01 extends JsonplaceholderBaseUrl {

    /*
    Given
      1) https://jsonplaceholder.typicode.com/todos
      2)
   When
       I send POST Request to the Url
   Then
       Status code is 201 {   // burasi yeni kayit ettiğim yer
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
                         }
   And
       response body is like {    // bu da bize donmesini istedigimiz yer
                               "userId": 55,
                               "title": "Tidy your room",
                               "completed": false,
                               "id": 201
                               }
*/

    @Test
    public void post01() {

        // Set the Url
       spec.pathParams("1","todos");

       //Set the expected data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String,Object> expectedData = obj.expectedDataMethod(55,"Tidy your room",false);
                                            // java olarak oluşturuyoruz map olarak yani
                                            // bu obje bize map dondurdugu icin map in icine atiyorum
                                            // bu rada bir format olusturduk aslinda
              // bu site icin surekli map olusturmayalim diye hazir map methodunu tesData package inda olusturduk

        //Send the Request and Get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{1}");
        response.prettyPrint();                         // bu rada da json a dondurduk
                                                      // burada ise siteye post etme istegi gonderiyoruz
                            /*
                               body secmemiz postmandeki body secme asamamaizdir aslinda postmandeki adimlari yaziya dokuyoruz
                               body key ve value dan olusuyor  postman de alt kisimda çıkan sonucu bize burada donduruyor
                               bize donen cevap JSON olarak donuyor


                               bizim expected data miz map tir
                               bize donen response ise Json formatinda
                               bunlari karsilastirip assert yapabilmem icin json i map e cevirmem lazim
                               bunu da as() methodu ile yapiyorum
                             */


        //Do Assertion
        Assert.assertEquals(201,response.statusCode()); // postmen de olusunca 201 veriyor bunu dogruladik

        Map<String,Object> actualData = response.as(HashMap.class); //De-Serialization -> Gson json'dan javaya bu kütüphane kullaniliyor


        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));

    }
}
