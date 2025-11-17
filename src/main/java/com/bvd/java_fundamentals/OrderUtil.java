package com.bvd.java_fundamentals;

import com.bvd.java_fundamentals.model.Order;
import com.bvd.java_fundamentals.StoreAnalytics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static com.bvd.java_fundamentals.StoreAnalytics.CSV_ORDER;

public class OrderUtil {

    private OrderUtil() {
    }

    // retrieve orders from csv lines
    public static List<Order> parseCsvLines(final List<String> lines) {
//                    "O-1001,C-001,2025-10-01, USB-C Cable ,Accessories,9.99,2",
        List<Order> orders = Collections.singletonList(new Order());
        try(Scanner scanner = new Scanner(new InputStreamReader((InputStream) CSV_ORDER))) {
            while(scanner.hasNextLine()) {
            orders.add((Order) parseCsvLines(Collections.singletonList(scanner.nextLine())));
            }
        }
        return orders;
    }

    // calculate revenue by day
    // revenue = unitPrice * quantity   lambda
    public static Map<LocalDate, BigDecimal> revenueByDay(final List<Order> orders) {

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
