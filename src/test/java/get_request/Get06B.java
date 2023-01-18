package get_request;

import base_url.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get06B extends ReqresBaseUrl {

     /*
   Given
          https://reqres.in/api/unknown/
   When
        I send GET Request to the URL
   Then

        1)Status code is 200
        2)Print all pantone_values
        3)Print all ids greater than 3 on the console
          Assert that there are 3 ids greater than 3
        4)Print all names whose ids are less than 3 on the console
          Assert that the number of names whose ids are less than 3 is 2

        1) Durum kodu 200
        2) Tüm pantone_values yazdır
        3) Konsolda 3'ten büyük tüm ids yazdırın
          3'ten büyük 3 ids olduğunu iddia edin
        4) ids 3'ten küçük olan tüm names konsolda yazdırın
          ids 3'ten küçük olan names sayısının 2 olduğunu iddia edin.
*/

    // NOT==> JSON daki datalari disari cikarmak icin json.Path() kullaniyoruz

    @Test
    public void get06() {

        spec.pathParams("first","unknown");

        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // Do Assertion
        assertEquals(200,response.statusCode()); // Durum kodu 200
        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.getList("data.pantone_value").size()); // Tüm pantone_values yazdır
        System.out.println(jsonPath.getList("data.pantone_value").get(0)); // 1. yi verir

        // not konsolda yazdirdigimizda datalar [ ] bunun icindeydi bu da listte oldugunu gosterir

        // 1) Status code is 200
        assertEquals(200,response.getStatusCode());

        // 2)Print all pantone_values
        System.out.println(jsonPath.getList("data.pantone_value"));

        // 3)Print all ids greater than 3 on the console // findAll hepsini bul demek
        List<Integer> ids = jsonPath.getList("data.findAll{it.id>3}.id");
        System.out.println("ids = " +ids);

              /* Not findAll collection in basladigi yerden itibaren alinir. Burada liste data dan sonra ulasabiliyoruz
              collection dan once bir sey olmasaydi direk findAll diye baslardim ama burda oncesinde data
              oldugu icin data.findALL SEKLİNDE BASLADİK
              Collection dan kastimiz [ bu isaret
               */
        //  Assert that there are 3 ids greater than 3
        assertEquals(3,ids.size());

        //  4)Print all names whose ids are less than 3 on the console
        List<String> names = jsonPath.getList("data.findAll{it.id<3}.name");
        System.out.println("names = " +names);

        //  Assert that the number of names whose ids are less than 3 is 2
        assertEquals(2,names.size());
    }
}
