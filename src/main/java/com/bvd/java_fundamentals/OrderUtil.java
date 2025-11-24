package com.bvd.java_fundamentals;

import com.bvd.java_fundamentals.model.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class OrderUtil {

    private OrderUtil() {
    }

    // retrieve orders from csv lines
    public static List<Order> parseCsvLines(final List<String> lines) {
        if (lines == null || lines.isEmpty()) {
            return Collections.emptyList();
        }

        List<Order> result = new ArrayList<>();
        for (String line : lines) {
            try {
                String[] parts = line.split(",");
                if (parts.length != 7) continue;

                String orderId = parts[0].trim();
                String customerId = parts[1].trim();
                LocalDate orderDate = LocalDate.parse(parts[2].trim());
                String productName = parts[3].trim();
                String category = parts[4].trim();
                BigDecimal unitPrice = new BigDecimal(parts[5].trim());
                int quantity = Integer.parseInt(parts[6].trim());

                result.add(new Order(orderId, customerId, orderDate, productName, category, unitPrice, quantity));
            } catch (Exception ignored) {
            }
        }
        return result;
    }

    // calculate revenue by day
    // revenue = unitPrice * quantity   lambda
    public static Map<LocalDate, BigDecimal> revenueByDay(final List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<LocalDate, BigDecimal> revenue = new HashMap<>();
        for (Order order : orders) {
            LocalDate date = order.getOrderDate();
            BigDecimal value = order.getUnitPrice().multiply(BigDecimal.valueOf(order.getQuantity()));
            revenue.merge(date, value, BigDecimal::add);
        }
        return revenue;
    }

    // get top "n" products by revenue
    public static List<Map.Entry<String, BigDecimal>> topProductsByRevenue(final List<Order> orders, final int n) {
        if (orders == null || orders.isEmpty() || n <= 0) {
            return Collections.emptyList();
        }

        Map<String, BigDecimal> totals = new HashMap<>();
        for (Order order : orders) {
            BigDecimal revenue = order.getUnitPrice().multiply(BigDecimal.valueOf(order.getQuantity()));
            totals.merge(order.getProductName(), revenue, BigDecimal::add);
        }

        List<Map.Entry<String, BigDecimal>> sorted = new ArrayList<>(totals.entrySet());
        sorted.sort(Map.Entry.<String, BigDecimal>comparingByValue().reversed());
        return sorted.subList(0, Math.min(n, sorted.size()));
    }

    // get customers who ordered products from at least "minCategories" different categories
    public static List<String> customersWithCategoryDiversity(final List<Order> orders, final int minCategories) {
        if (orders == null || orders.isEmpty() || minCategories <= 0) {
            return Collections.emptyList();
        }

        Map<String, Set<String>> map = new HashMap<>();
        for (Order order : orders) {
            map.computeIfAbsent(order.getCustomerId(), k -> new HashSet<>()).add(order.getCategory());
        }

        List<String> diverseCustomers = new ArrayList<>();
        for (Map.Entry<String, Set<String>> e : map.entrySet()) {
            if (e.getValue().size() >= minCategories) {
                diverseCustomers.add(e.getKey());
            }
        }
        return diverseCustomers;
    }

    // find the first product containing a given substring (case-insensitive)
    public static Optional<Order> findFirstProductContaining(final List<Order> orders, final String product) {
        if (orders == null || orders.isEmpty() || product == null || product.isBlank()) {
            return Optional.empty();
        }

        String keyword = product.toLowerCase();
        for (Order order : orders) {
            if (order.getProductName().toLowerCase().contains(keyword)) {
                return Optional.of(order);
            }
        }
        return Optional.empty();
    }
}
