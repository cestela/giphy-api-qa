package resources;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matcher;

import java.lang.reflect.Field;
import java.util.Objects;

import static org.hamcrest.Matchers.*;

public class Utils {
    public static String getJsonPath(Response response, String key){
        String responseString = response.asString();
        JsonPath js = new JsonPath(responseString);
        return js.get(key).toString();
    }

    public static Object findAttributeByName(String object, Object o) throws IllegalAccessException {
        Class<?> c = o.getClass();
        Field f = null;
        try {
            f = c.getDeclaredField(object);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        Objects.requireNonNull(f).setAccessible(true);


        return f.get(o);
    }

    public static Matcher<Integer> selectMatcher(String comparator, int size) {
        Matcher<Integer> matcher = null;
        switch (comparator) {
            case "greater than":
                matcher = greaterThan(size);
                break;
            case "equal to":
                matcher = equalTo(size);
                break;
            case "less than":
                matcher = lessThan(size);
                break;
        }

        return matcher;
    }

}
