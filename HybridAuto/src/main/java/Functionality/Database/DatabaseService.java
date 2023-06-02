package Functionality.Database;

import java.util.List;
import java.util.Optional;

public class DatabaseService {
    public static boolean executeQuery(String q) {
        return true;
    }

    public static <T> Optional<T> executeScalar(String queryStr) {
        return Optional.empty();
    }

    public static <T> Optional<List<T>> executeInline(String queryStr) {
        return Optional.empty();
    }

    public static Optional<List<String>> executeInlineStr(String queryStr) {
        return Optional.empty();
    }
}
