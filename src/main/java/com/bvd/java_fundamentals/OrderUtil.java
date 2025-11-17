package com.bvd.java_fundamentals;

import com.bvd.java_fundamentals.model.Order;
import com.bvd.java_fundamentals.StoreAnalytics;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static com.bvd.java_fundamentals.StoreAnalytics.CSV_ORDER;

public class OrderUtil {

    private OrderUtil() {
    }

    // retrieve orders from csv lines
    public static List<Order> parseCsvLines(final List<String> lines) {
        List<String> orders = new ArrayList<>();

        final String COMMA_DELIMITER = ", ";

        try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(StoreAnalytics.CSV_ORDER)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                orders.add(String.valueOf(Arrays.asList(values)));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // calculate revenue by day
    // revenue = unitPrice * quantity   lambda
    public static Map<LocalDate, BigDecimal> revenueByDay(final List<Order> orders) {
        int result = orders.stream().reduce(1, (unitPrice, quantity) -> {
            return unitPrice * quantity;
        }).intValue();
        return Collections.emptyMap();
    }

    // get top "n" products by revenue
    public static List<Map.Entry<String, BigDecimal>> topProductsByRevenue(final List<Order> orders, final int n) {
        // Write your code here and replace the return statement
        return Collections.emptyList();
    }

    // get customers who ordered products from at least "minCategories" different categories
    public static List<String> customersWithCategoryDiversity(final List<Order> orders, final int minCategories) {
        // Write your code here and replace the return statement
        return Collections.emptyList();
    }

    // find the first product containing a given substring (case-insensitive)
    public static Optional<Order> findFirstProductContaining(final List<Order> orders, final String product) {
        // Write your code here and replace the return statement
        return Optional.empty();
    }
}
