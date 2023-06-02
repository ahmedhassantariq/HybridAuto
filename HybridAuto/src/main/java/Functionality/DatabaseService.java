package Functionality;

import Entities.Product;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class DatabaseService {
    public static boolean executeQuery(String q) {
        return true;
    }

    public static <T> Optional<T> executeScalar(String queryStr) {
        return Optional.empty();
    }

    public static <T> Optional<List<T>> executeInline() {
        return Optional.empty();
    }
}
