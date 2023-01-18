package test_data;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {


    public Map<String,Object> expectedDataMethod(Integer userId,String title,Boolean completed){

        Map<String,Object> expectedDataMap = new HashMap<>();

        if (userId!=null) {
            expectedDataMap.put("userId", userId);
        }

        if (title!=null) {
            expectedDataMap.put("title", title);
        }

        if (completed!=null) {
            expectedDataMap.put("completed", completed);
        }
               /* tek bir yerde degisiklik yapacaksan ya da 1-2 fark etmez bu sebeple if e koyduk
               eger null ise onu calistirmayacak patch classin da bu sekilde kullanmamiz gerekti
               bu hale cevirdik bu sekliyle patch put post hepsinde kullanabiliyorum
                */
        return expectedDataMap;
    }


    public String expectedDataInString(Integer userId,String title,Boolean completed) { // dinamik expected data methodu: json datayi string hale getirir

        String expectedData = "{\n" +
                "                     \"userId\": "+userId+",\n" +
                "                     \"title\": \""+title+"\",\n" +
                "                     \"completed\": "+completed+"\n" +
                "                   }";

        return expectedData;
    }


}
/*
{
                     "userId": 55,
                     "title": "Tidy your room",
                     "completed": false
                   }
 */