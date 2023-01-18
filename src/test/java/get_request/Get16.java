package get_request;

import base_url.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class Get16 extends DummyRestApiBaseUrl {

    /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language

           Assert:  i) Status code is 200

                   ii) There are 24 employees

                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees

                   iv) The greatest age is 66 // en yuksek yas 66

                    v) The name of the lowest age is "Tatyana Fitzpatrick"

                   vi) Total salary of all employees is 6,644,770
    */

    @Test
    public void get16() {


        Response response = given().when().get("https://dummy.restapiexample.com/api/v1/employees");
        response.prettyPrint();

        // There are 24 employees // "Tiger Nixon" and "Garrett Winters" are among the employees
        response.then().assertThat().body("data.id", hasSize(24),
                "data.employee_name", hasItems("Tiger Nixon", "Garrett Winters"));

        // The greatest age is 66 // en yuksek yas 66
        List<Integer> ages = response.jsonPath().getList("data.employee_age");
        System.out.println("ages = " + ages); // tum yaslari yazdiriyor
        Collections.sort(ages); // Collections'i siraladik
        System.out.println("Sorted ages = " + ages); // yaslarin siralanmis hali
        System.out.println("ages.get(ages.size()-1) = " + ages.get(ages.size() - 1)); // siralamadiki en yuksek eleman en sondaki elemandir ona gidiyoruz

        assertEquals(66, (int) (ages.get(ages.size() - 1)));

        // The name of the lowest age is "Tatyana Fitzpatrick" // en kucuk yasa ait isim
        String lowestAgedEmployee= response.jsonPath().getString("data.findAll{it.employee_age == "+ages.get(0)+"}.employee_name");
        System.out.println("lowestAgedEmployee = " + lowestAgedEmployee);

        assertEquals("[Tatyana Fitzpatrick]", lowestAgedEmployee);


        // Total salary of all employees is 6,644,770
        List<Integer> salaries = response.jsonPath().getList("data.employee_salary");
        System.out.println("salaries = " + salaries);

        // 1.Yol
        int sum = 0;
        for (int w : salaries
        ) {
            sum += w;
        }
        System.out.println("sum = " + sum);

        assertEquals(6644770, sum);

        // 2.Yol
        int sum2 = salaries.stream().reduce(0, Integer::sum);
        System.out.println("sum2 = " + sum2);
        assertEquals(6644770, sum2);

        // 3.Yol
        int sum3 = salaries.stream().reduce(0, Math::addExact);
        System.out.println("sum3 = " + sum3);
        assertEquals(6644770, sum3);


    }
}
