package Functionality.Database.DB;

public class DatabaseQueries {
    public static class READ_QUERIES {
        public static final String GET_ALL_DISTINCT_CAR_MODELS = queryGeneratorDistinct("model", "tbl_car");
        public static final String GET_ALL_DISTINCT_CAR_MAKES = queryGeneratorDistinct("make", "tbl_car");
        public static final String GET_ALL_DISTINCT_PRODUCT_TYPES = queryGeneratorDistinct("type", "tbl_product");
        public static final String GET_ALL_DISTINCT_PRODUCT_CONDITION = queryGeneratorDistinct("condition", "tbl_product");


        private static String queryGeneratorDistinct(String fieldName, String tableName) {
            return String.format("SELECT DISTINCT %s FROM %s", fieldName, tableName);
        }
    }

    public static class INSERT_QUERIES {
        public static final String INSERT_PRODUCT = insertQueryGenerator(
                //assuming inventory product id is auto-increment, it is not included here
                "tbl_product", "carId", "productId", "serialNumber", "cost",
                "description", "condition"
        );
        public static final String INSERT_CAR = insertQueryGenerator(
                //assuming car id is auto-increment
                "tbl_car", "manufacturerId", "make", "model", "year"
        );
        public static final String INSERT_CATEGORY = insertQueryGenerator(
                "tbl_category", "make", "model", "year", "product"
        );

        public static String insertQueryGenerator(String tableName, String... fieldNames) {
            StringBuilder query = new StringBuilder(String.format("INSERT INTO %s(*) VALUES (", tableName));
            for(int i = 0; i < fieldNames.length; i++) {
                query.replace(query.indexOf("*"), query.indexOf("*"), fieldNames[i]
                        + (i == fieldNames.length-1? "" : ", "));
                query.append("?");
            }
            query.append(")");

            return query.toString();
        }
    }

    public static class DELETE_QUERIES {
        // TODO: 6/4/2023 adapt PK to what is in DB e.g. composite keys
        public static final String DELETE_PRODUCT = deleteQueryGenerator("tbl_product", "pid");
        public static final String DELETE_CAR = deleteQueryGenerator("tbl_car", "cid");
        public static final String DELETE_CATEGORY = deleteQueryGenerator("tbl_category", "cid");

        public static String deleteQueryGenerator(String tblName, String pkName) {
            return String.format("DELETE FROM %s t WHERE t.%s = ?", tblName, pkName);
        }
    }

    public static class UPDATE_QUERIES {
        public static final String UPDATE_PRODUCT = updateQueryGenerator(
                "tbl_product", "carId", "productId", "serialNumber", "cost",
                "description", "condition"
        );
        public static final String UPDATE_CAR = updateQueryGenerator(
                "tbl_car", "manufacturerId", "make", "model", "year"
        );
        public static final String UPDATE_CATEGORY = updateQueryGenerator(
                "tbl_category", "make", "model", "year", "product"
        );

        public static String updateQueryGenerator(String tableName, String... fieldNames) {
            StringBuilder query = new StringBuilder(String.format("UPDATE %s SET *", tableName));
            for(int i = 0; i < fieldNames.length; i++) {
                query.replace(query.indexOf("*"), query.indexOf("*"), fieldNames[i] + " = ?"
                        + (i == fieldNames.length-1? "" : ", "));
            }
            query.append(")");

            return query.toString();
        }
    }
}
