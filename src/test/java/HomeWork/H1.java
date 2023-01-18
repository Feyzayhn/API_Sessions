package HomeWork;
import base_url.AutoExerciseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class H1 extends AutoExerciseBaseUrl {

        /*
     Given
         https://automationexercise.com/api/productsList
     When
         User sends a GET Request to the url
     Then
         HTTP Status Code should be 200
     And
         Content Type should be "text/html; charset=utf-8"
     And
         Status Line should be HTTP/1.1 200 OK
     And
          There must be 12 Women, 9 Men, 13 Kids usertype in products
          //Urunlerde 12 Kadın, 9 Erkek, 13 Çocuk kullanıcı tipi bulunmalıdır.
       */


    /*
     "responseCode": 200,
    "products": [
        {
            "id": 1,
            "name": "Blue Top",
            "price": "Rs. 500",
            "brand": "Polo",
            "category": {
                "usertype": {
                    "usertype": "Women"
                },
                "category": "Tops"
            }
        }


            POSTMAN'DE BU SEKİLDE DEVAM EDEN BİR CIKTISI VAR BU [ ISARET OLDUGU LISTE ATABILIYORUZ

           "products.category.usertype.usertype"  KULLANICI TIPINI bu parent'tan child seklinde gide gide
                                                  usertype altındaki women,men,kids basliklarini getirir



     */



    @Test
    public void odev01() {

        spec.pathParam("first", "productsList");

        Response response = given().spec(spec).when().get("/{first}");
        //response.prettyPrint();

        JsonPath json = response.jsonPath();
        //json.prettyPrint();

        assertEquals(200, response.statusCode());  //HTTP Status Code should be 200
        System.out.println(response.statusCode());
        assertEquals("text/html; charset=utf-8", response.contentType()); //Content Type should be "text/html; charset=utf-8"
        System.out.println(response.contentType());
        assertEquals("HTTP/1.1 200 OK", response.statusLine());// Status Line should be HTTP/1.1 200 OK
        System.out.println(response.statusLine());

        //There must be 12 Women, 9 Men, 13 Kids usertype in products
        //Unlerde 12 Kadın, 9 Erkek, 13 Çocuk kullanıcı tipi bulunmalıdır.
        List<String> categories = json.getList("products.category.usertype.usertype"); // bu kisimda json formatinda usertype a ulasana kadar olan parent basliklari yazidik
        List<String> category = json.getList("products.category.usertype.findAll{it.usertype}.usertype"); // yukardaki list ile ayni sadece bu findAll kullanilarak yapildi
        System.out.println(category);
        System.out.println(categories);

        //1.YONTEM
        int womenCount = 0;
        int menCount = 0;
        int kidsCount = 0;
        for (String w : categories) {
            switch (w) {
                case "Women":
                    womenCount++;
                    break;
                case "Men":
                    menCount++;
                    break;
                case "Kids":
                    kidsCount++;
                    break;
            }
        }


        System.out.println("women : " + womenCount);
        System.out.println("men : " + menCount);
        System.out.println("kids : " + kidsCount);

        assertEquals(12, womenCount);
        assertEquals(9, menCount);
        assertEquals(13, kidsCount);

        //2.YONTEM
        List<String> women = json.getList("products.category.usertype.findAll{it.usertype=='Women'}.usertype");
        System.out.println("women : " + women);
        List<String> men = json.getList("products.category.usertype.findAll{it.usertype=='Men'}.usertype");
        System.out.println("men : " + men);
        List<String> kids = json.getList("products.category.usertype.findAll{it.usertype=='Kids'}.usertype");
        System.out.println("kids : " + kids);

        assertEquals(12, women.size());
        assertEquals(9, men.size());
        assertEquals(13, kids.size());


        //3.LAMBDA ILE COZUM
        List<String> usertype = json.getList("products.category.usertype.usertype");
        assertEquals(12, usertype.stream().filter(t -> t.contains("Women")).count());
        assertEquals(9, usertype.stream().filter(t -> t.contains("Men")).count());
        assertEquals(13, usertype.stream().filter(t -> t.contains("Kids")).count());


    }
}



