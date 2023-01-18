package get_request;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01 {
     /*
    1) Postman, manuel API testleri icin kullandik
    2) Otomasyon testleri icin de Rest Assured Library kullanacagiz // bunu pom.xml e ekledik
    3) Otomasyon testlerimizi yaparken asagidaki adimlari izliyoruz;
       a) Geeksinimleri anlamak,
       b) Test Case yaziyoruz
          i)Test Case yaziminda "Gherkin" dilini kullanacagiz.Bizler yazilim diline hakim olsak da, karsimizdaki
          kisiler hakim olmayabilir ama Gherkin ile yazilan testleri anlamakta zorluk cekmeyeceklerdir.
          Gherkin dilinde kullanacagimiz keywordler;

          -Given : On kosullar,
          -When : Yapilacak aksiyonlar icin ( get(), put(), post(), patch() ve delete() )
          -Then : Istek yaptiktan (reguster gonderdıkten sonra) dogrulama(Assert...),
          -And : Coklu islemlerde kullancagiz

        c) Test kodlarimizi yazmaya baslayacagiz

           i) Set the URL,
           ii) Set the expected Data (beklenen datanin olusturulmasi, Post, Put, Patch)
           iii) Type code to send request ( Talep gondermek icin kod yazimi)
           iv) Do Assertion (dogrulama yapmak)
     */

    /*
    Given
        https://restful-booker.herokuapp.com/booking/101
    When
        User sends a GET Request to the url
    Then
        HTTP Status Code should be 200
    And
        Content Type should be JSON  (Not: contentType JSON yazarsak fail olur bu yuzden (application/json) yazmamiz lazim yerine)
    And
        Status Line should be HTTP/1.1 200 OK
 */


    @Test
    public void get01() {

        // i) Set the URL,
        String url="https://restful-booker.herokuapp.com/booking/101"; //burasi given dir


        //ii) Set the expected Data (beklenen datanin olusturulmasi, Post, Put, Patch)
        //Bizden POST, PUT, YA DA PATCH istenmedigi icin bu case de kullanmayacagiz soruda GET istemis


        //iii) Type code to send request ( Talep gondermek icin kod yazimi)
        Response response =given().when().get(url); /*sorudaki sıraya gore gittik given,when ve get kismini --get when'in icindeki bir adimdi*/
        response.prettyPrint();
                                         /*
                                            Response geri verilen dosya bu klasorden geliyor
                                            prettyPrint() bize datayi yazdirir
                                            */
                                           /* when e kadar bu asamada response un icine atadik */

        //iv) Do Assertion (dogrulama yapmak)

        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

           /* burada ise 3. adimda icine atadigimiz response dan devam edip then asamasina gectik
               ve and then 'den sonra and ile ona bagli olan kisimlari devam ettirdik
                  response.then()
                        .assertThat().
                        statusCode(200).
                        and().
                        contentType("application/json").
                        and().
                       statusLine("HTTP/1.1 200 OK");
                 bu sekilde aralarina and() koyabilirdim */




        // Status Code konsola yazdiralim
        System.out.println("Status Code : " +response.getStatusCode());

        // Content Type konsola yazdiralim
        System.out.println("Content Type : "+response.getContentType());

        //Status Line konsola yazdiralim
        System.out.println("Status Line :" +response.getStatusLine());

        //Header konsola yazdiralim
        System.out.println("Header : " +response.getHeader("Server")); // header in icinden sectiğim bir tanesini getirdik

        //Headers konsola yazdiralim
        System.out.println("Headers : " +response.getHeaders()); // tamamını yazdiracak

        // Time konsolda yazdi,ralim
        System.out.println("Time :" +response.getTime());

    }
}
