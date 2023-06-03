package Functionality.Database.DB;

import Functionality.Utils.ArrayUtils;

public class DatabaseQueries {
    public static class SEARCH_QUERIES {
        public static final String GET_ALL_DISTINCT_CAR_MODELS = queryGeneratorDistinct("model", "tbl_car");
        public static final String GET_ALL_DISTINCT_CAR_MAKES = queryGeneratorDistinct("make", "tbl_car");
        public static final String GET_ALL_DISTINCT_CAR_YEARS = queryGeneratorDistinct("year", "tbl_car");
        public static final String GET_ALL_DISTINCT_PRODUCT_TYPES = queryGeneratorDistinct("type", "tbl_product");
        public static final String GET_ALL_DISTINCT_PRODUCT_CONDITION = queryGeneratorDistinct("condition", "tbl_product");


        public static final String GET_ALL_PRODUCTS = queryGeneratorGetAll("tbl_product");
        public static final String GET_ALL_CARS = queryGeneratorGetAll("tbl_car");
        public static final String GET_ALL_CATEGORIES = queryGeneratorGetAll("tbl_category");


        private static String queryGeneratorDistinct(String fieldName, String tableName) {
            return String.format("SELECT DISTINCT %s FROM %s", fieldName, tableName);
        }
        private static String queryGeneratorGetAll(String tableName) {
            return String.format("SELECT * FROM %s", tableName);
        }

        public static class AGGREGATES {
            public static class FUNCTION_NAMES {
                public static final String MAX = "MAX";
                public static final String COUNT = "COUNT";
            }

            public static class MAX {
                public static final String GET_MAX_INVENTORY_PRODUCT_ID = queryGeneratorAggregate(
                        FUNCTION_NAMES.MAX, "tbl_product", "inventoryProductId"
                );

            }

            public static class COUNT {
                public static final String GET_COUNT_INVENTORY_PRODUCT_ID = queryGeneratorAggregate(
                        FUNCTION_NAMES.COUNT, "tbl_product", "*"
                );
            }

            private static String queryGeneratorAggregate(String aggregateFunction, String tableName, String fieldName) {
                return String.format("SELECT %s(%s) FROM %s", aggregateFunction, fieldName, tableName);
            }
        }

        public static class WITH_CONDITION {
            public static final String SEARCH_PRODUCT_WITH_SERIAL = queryGeneratorSearch(
                    "tbl_product", ArrayUtils.from("*"), "serialNumber"
            );
            public static final String SEARCH_CAR_WITH_ID = queryGeneratorSearch(
                    "tbl_car", ArrayUtils.from("*"), "carId"
            );
            public static final String SEARCH_CAR_WITH_MAKE_MODEL_AND_YEAR = queryGeneratorSearch(
                    "tbl_car", ArrayUtils.from("*"), "make", "model", "year"
            );
            public static final String SEARCH_CATEGORY_WITH_TYPE_AND_CONDITION = queryGeneratorSearch(
                    "tbl_category", ArrayUtils.from("*"), "type", "condition"
            );


            public static final String SEARCH_MODELS_WITH_MAKE = queryGeneratorSearch(
                    "tbl_car", ArrayUtils.from("*"),"make"
            );


            private static String queryGeneratorSearch(String tableName, String[] fieldNamesToGet, String... fieldsInWhereClause) {
                StringBuilder query = new StringBuilder(String.format("SELECT - FROM %s where *", tableName));
                for (int i = 0; i < fieldNamesToGet.length; i++) {
                    query.replace(query.indexOf("-"), query.indexOf("-"), fieldNamesToGet[i]
                            + (i == fieldNamesToGet.length-1? "" : ", "));
                }
                for(int i = 0; i < fieldsInWhereClause.length; i++) {
                    query.replace(query.indexOf("*"), query.indexOf("*"), fieldsInWhereClause[i] + " = ?"
                            + (i == fieldsInWhereClause.length-1? "" : " AND "));
                }

                return query.toString();
            }
        }
    }

    public static class INSERT_QUERIES {
        public static final String INSERT_PRODUCT = insertQueryGenerator(
                //assuming inventory product id is auto-increment, it is not included here
                "tbl_product", "carId", "productId", "serialNumber", "cost",
                "description", "created_datetime", "condition", "display"
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

            return query.toString();
        }
    }
}
