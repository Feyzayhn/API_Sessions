package utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class ObjectMapperUtils {

    // new ObjectMapper().readValue(response.asString(), HashMap.class);

    private static ObjectMapper mapper;

    static { // static blog her seyden once calisir constructor dan bile once
        mapper = new ObjectMapper();
    }

    public static <T> T convertJsonToJava(String json, Class<T> cls){ // JSon i javaya donusturen method
                                               // JSon ı su class a cevir demis oluyoruz

        T javaResult = null;
        try {
            javaResult = mapper.readValue(json,cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return javaResult;

    }

}
