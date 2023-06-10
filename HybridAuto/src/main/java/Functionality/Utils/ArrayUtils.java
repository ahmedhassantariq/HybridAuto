package Functionality.Utils;

import java.util.Arrays;
import java.util.List;

public class ArrayUtils {

    public static <T> T[] from(T... values) {
        List<T> toReturn = Arrays.asList(values);
        return (T[]) toReturn.toArray();
    }
    public static String[] fromString(String... values) {
        List<String> toReturn = Arrays.asList(values);
        return toReturn.toArray(new String[0]);
    }
}
