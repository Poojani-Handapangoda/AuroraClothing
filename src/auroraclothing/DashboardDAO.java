/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auroraclothing;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DashboardDAO {

    // =========================================================
    // TOTAL NUMBER OF PRODUCTS
    // =========================================================

    public static int getTotalProducts() {

        String sql =
                "SELECT COUNT(*) AS total "
                + "FROM products";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet result =
                        statement.executeQuery()
        ) {

            if (result.next()) {

                return result.getInt(
                        "total"
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Dashboard product count error: "
                    + e.getMessage()
            );
        }

        return 0;
    }


    // =========================================================
    // TOTAL INVENTORY QUANTITY
    // =========================================================

    public static int getTotalInventoryQuantity() {

        String sql =
                "SELECT COALESCE(SUM(quantity), 0) AS total "
                + "FROM inventory";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet result =
                        statement.executeQuery()
        ) {

            if (result.next()) {

                return result.getInt(
                        "total"
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Dashboard inventory total error: "
                    + e.getMessage()
            );
        }

        return 0;
    }


    // =========================================================
    // TOTAL CUSTOMERS
    // =========================================================

    public static int getTotalCustomers() {

        String sql =
                "SELECT COUNT(*) AS total "
                + "FROM customers";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet result =
                        statement.executeQuery()
        ) {

            if (result.next()) {

                return result.getInt(
                        "total"
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Dashboard customer count error: "
                    + e.getMessage()
            );
        }

        return 0;
    }


    // =========================================================
    // TOTAL COMPLETED SALES REVENUE
    // =========================================================

    public static BigDecimal getTotalSalesRevenue() {

        String sql =
                "SELECT COALESCE(SUM(total_amount), 0) AS total "
                + "FROM sales "
                + "WHERE status = 'Completed'";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet result =
                        statement.executeQuery()
        ) {

            if (result.next()) {

                BigDecimal total =
                        result.getBigDecimal(
                                "total"
                        );

                return total != null
                        ? total
                        : BigDecimal.ZERO;
            }

        } catch (SQLException e) {

            System.out.println(
                    "Dashboard sales revenue error: "
                    + e.getMessage()
            );
        }

        return BigDecimal.ZERO;
    }


    // =========================================================
    // TOTAL SALES ORDERS
    // =========================================================

    public static int getTotalOrders() {

        String sql =
                "SELECT COUNT(*) AS total "
                + "FROM sales";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet result =
                        statement.executeQuery()
        ) {

            if (result.next()) {

                return result.getInt(
                        "total"
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Dashboard order count error: "
                    + e.getMessage()
            );
        }

        return 0;
    }


    // =========================================================
    // IN STOCK COUNT
    // quantity greater than reorder level
    // =========================================================

    public static int getInStockCount() {

        String sql =
                "SELECT COUNT(*) AS total "
                + "FROM inventory "
                + "WHERE quantity > reorder_level";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet result =
                        statement.executeQuery()
        ) {

            if (result.next()) {

                return result.getInt(
                        "total"
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Dashboard in-stock count error: "
                    + e.getMessage()
            );
        }

        return 0;
    }


    // =========================================================
    // LOW STOCK COUNT
    // quantity above zero but at/below reorder level
    // =========================================================

    public static int getLowStockCount() {

        String sql =
                "SELECT COUNT(*) AS total "
                + "FROM inventory "
                + "WHERE quantity > 0 "
                + "AND quantity <= reorder_level";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet result =
                        statement.executeQuery()
        ) {

            if (result.next()) {

                return result.getInt(
                        "total"
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Dashboard low-stock count error: "
                    + e.getMessage()
            );
        }

        return 0;
    }


    // =========================================================
    // OUT OF STOCK COUNT
    // =========================================================

    public static int getOutOfStockCount() {

        String sql =
                "SELECT COUNT(*) AS total "
                + "FROM inventory "
                + "WHERE quantity <= 0";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet result =
                        statement.executeQuery()
        ) {

            if (result.next()) {

                return result.getInt(
                        "total"
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Dashboard out-of-stock count error: "
                    + e.getMessage()
            );
        }

        return 0;
    }


    // =========================================================
    // RECENT SALES ACTIVITY
    // Returns:
    // [0] order_id
    // [1] product display
    // [2] status
    // =========================================================

    public static List<Object[]> getRecentSales() {

        List<Object[]> sales =
                new ArrayList<>();


        String sql =
                "SELECT "
                + "s.order_id, "
                + "s.status, "
                + "COUNT(si.product_id) AS item_count, "
                + "GROUP_CONCAT("
                + "DISTINCT p.product_name "
                + "ORDER BY p.product_name "
                + "SEPARATOR ', '"
                + ") AS product_names "
                + "FROM sales s "
                + "LEFT JOIN sale_items si "
                + "ON s.order_id = si.order_id "
                + "LEFT JOIN products p "
                + "ON si.product_id = p.product_id "
                + "GROUP BY "
                + "s.order_id, "
                + "s.status, "
                + "s.sale_date "
                + "ORDER BY "
                + "s.sale_date DESC, "
                + "s.order_id DESC "
                + "LIMIT 3";


        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet result =
                        statement.executeQuery()
        ) {

            while (result.next()) {

                String orderId =
                        result.getString(
                                "order_id"
                        );


                String status =
                        result.getString(
                                "status"
                        );


                int itemCount =
                        result.getInt(
                                "item_count"
                        );


                String productNames =
                        result.getString(
                                "product_names"
                        );


                String displayText;


                if (
                        productNames == null
                        || productNames.trim().isEmpty()
                ) {

                    displayText =
                            "No items";

                } else if (itemCount > 1) {

                    displayText =
                            itemCount
                            + " products";

                } else {

                    displayText =
                            productNames;
                }


                sales.add(
                        new Object[]{
                            orderId,
                            displayText,
                            status
                        }
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Dashboard recent sales error: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }


        return sales;
    }
}
