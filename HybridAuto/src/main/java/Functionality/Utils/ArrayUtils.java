package Functionality.Utils;

import java.util.Arrays;

public class ArrayUtils {

    public static <T> T[] from(T... values) {
        return (T[]) Arrays.asList(values).toArray(new Object[0]);
    }
}
