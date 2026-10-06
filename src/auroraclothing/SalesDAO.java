/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auroraclothing;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesDAO {

    // =========================================================
    // GET ALL SALES
    // =========================================================

    public static List<Object[]> getAllSales() {

        List<Object[]> salesList = new ArrayList<>();

        String sql =
                "SELECT order_id, "
                + "customer_id, "
                + "sale_date, "
                + "payment_method, "
                + "total_amount, "
                + "status, "
                + "stock_applied "
                + "FROM sales "
                + "ORDER BY sale_date DESC, order_id ASC";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        pst.executeQuery()
        ) {

            while (rs.next()) {

                Object[] row = {

                    rs.getString(
                            "order_id"
                    ),

                    rs.getString(
                            "customer_id"
                    ),

                    rs.getDate(
                            "sale_date"
                    ),

                    rs.getString(
                            "payment_method"
                    ),

                    rs.getBigDecimal(
                            "total_amount"
                    ),

                    rs.getString(
                            "status"
                    ),

                    rs.getBoolean(
                            "stock_applied"
                    )
                };

                salesList.add(row);
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR LOADING SALES: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return salesList;
    }


    // =========================================================
    // GET ONE SALE BY ORDER ID
    // =========================================================

    public static Object[] getSaleById(
            String orderId
    ) {

        String sql =
                "SELECT order_id, "
                + "customer_id, "
                + "sale_date, "
                + "payment_method, "
                + "total_amount, "
                + "status, "
                + "stock_applied "
                + "FROM sales "
                + "WHERE order_id = ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    orderId
            );

            try (
                    ResultSet rs =
                            pst.executeQuery()
            ) {

                if (rs.next()) {

                    return new Object[]{

                        rs.getString(
                                "order_id"
                        ),

                        rs.getString(
                                "customer_id"
                        ),

                        rs.getDate(
                                "sale_date"
                        ),

                        rs.getString(
                                "payment_method"
                        ),

                        rs.getBigDecimal(
                                "total_amount"
                        ),

                        rs.getString(
                                "status"
                        ),

                        rs.getBoolean(
                                "stock_applied"
                        )
                    };
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR GETTING SALE: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return null;
    }


    // =========================================================
    // ADD SALE
    // =========================================================

    public static boolean addSale(
            String orderId,
            String customerId,
            Date saleDate,
            String paymentMethod,
            BigDecimal totalAmount,
            String status
    ) {

        String sql =
                "INSERT INTO sales "
                + "(order_id, customer_id, sale_date, "
                + "payment_method, total_amount, status, stock_applied) "
                + "VALUES (?, ?, ?, ?, ?, ?, 0)";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    orderId
            );

            pst.setString(
                    2,
                    customerId
            );

            pst.setDate(
                    3,
                    saleDate
            );

            pst.setString(
                    4,
                    paymentMethod
            );

            pst.setBigDecimal(
                    5,
                    totalAmount
            );

            pst.setString(
                    6,
                    status
            );

            int rows =
                    pst.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "ERROR ADDING SALE: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // UPDATE SALE
    // =========================================================

    public static boolean updateSale(
            String orderId,
            String customerId,
            Date saleDate,
            String paymentMethod,
            BigDecimal totalAmount,
            String status
    ) {

        String sql =
                "UPDATE sales "
                + "SET customer_id = ?, "
                + "sale_date = ?, "
                + "payment_method = ?, "
                + "total_amount = ?, "
                + "status = ? "
                + "WHERE order_id = ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    customerId
            );

            pst.setDate(
                    2,
                    saleDate
            );

            pst.setString(
                    3,
                    paymentMethod
            );

            pst.setBigDecimal(
                    4,
                    totalAmount
            );

            pst.setString(
                    5,
                    status
            );

            pst.setString(
                    6,
                    orderId
            );

            int rows =
                    pst.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "ERROR UPDATING SALE: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // DELETE SALE
    // =========================================================

    public static boolean deleteSale(
            String orderId
    ) {

        String sql =
                "DELETE FROM sales "
                + "WHERE order_id = ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    orderId
            );

            int rows =
                    pst.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "ERROR DELETING SALE: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // CHECK WHETHER ORDER ID ALREADY EXISTS
    // =========================================================

    public static boolean orderExists(
            String orderId
    ) {

        String sql =
                "SELECT order_id "
                + "FROM sales "
                + "WHERE order_id = ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    orderId
            );

            try (
                    ResultSet rs =
                            pst.executeQuery()
            ) {

                return rs.next();
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR CHECKING ORDER ID: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // CHECK WHETHER STOCK HAS ALREADY BEEN APPLIED
    // =========================================================

    public static boolean isStockApplied(
            String orderId
    ) {

        String sql =
                "SELECT stock_applied "
                + "FROM sales "
                + "WHERE order_id = ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    orderId
            );

            try (
                    ResultSet rs =
                            pst.executeQuery()
            ) {

                if (rs.next()) {

                    return rs.getBoolean(
                            "stock_applied"
                    );
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR CHECKING STOCK STATUS: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return false;
    }


    // =========================================================
    // SET STOCK APPLIED VALUE
    // =========================================================

    public static boolean setStockApplied(
            String orderId,
            boolean applied
    ) {

        String sql =
                "UPDATE sales "
                + "SET stock_applied = ? "
                + "WHERE order_id = ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql)
        ) {

            pst.setBoolean(
                    1,
                    applied
            );

            pst.setString(
                    2,
                    orderId
            );

            int rows =
                    pst.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "ERROR UPDATING STOCK APPLIED STATUS: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // MARK STOCK AS APPLIED
    // =========================================================

    public static boolean markStockApplied(
            String orderId
    ) {

        return setStockApplied(
                orderId,
                true
        );
    }


    // =========================================================
    // MARK STOCK AS NOT APPLIED
    // =========================================================

    public static boolean markStockNotApplied(
            String orderId
    ) {

        return setStockApplied(
                orderId,
                false
        );
    }


    // =========================================================
    // GET TOTAL NUMBER OF SALES
    // =========================================================

    public static int getTotalSalesCount() {

        String sql =
                "SELECT COUNT(*) AS total "
                + "FROM sales";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        pst.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getInt(
                        "total"
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR GETTING SALES COUNT: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return 0;
    }


    // =========================================================
    // GET TOTAL REVENUE
    // =========================================================

    public static BigDecimal getTotalRevenue() {

        String sql =
                "SELECT COALESCE(SUM(total_amount), 0) AS total "
                + "FROM sales";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        pst.executeQuery()
        ) {

            if (rs.next()) {

                BigDecimal total =
                        rs.getBigDecimal(
                                "total"
                        );

                if (total != null) {

                    return total;
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR GETTING TOTAL REVENUE: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return BigDecimal.ZERO;
    }


    // =========================================================
    // GET COMPLETED SALES COUNT
    // =========================================================

    public static int getCompletedSalesCount() {

        String sql =
                "SELECT COUNT(*) AS total "
                + "FROM sales "
                + "WHERE LOWER(status) = 'completed'";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        pst.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getInt(
                        "total"
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR GETTING COMPLETED SALES: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return 0;
    }


    // =========================================================
    // GET PENDING SALES COUNT
    // =========================================================

    public static int getPendingSalesCount() {

        String sql =
                "SELECT COUNT(*) AS total "
                + "FROM sales "
                + "WHERE LOWER(status) = 'pending'";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        pst.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getInt(
                        "total"
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR GETTING PENDING SALES: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return 0;
    }


    // =========================================================
    // GET THIS MONTH'S REVENUE
    // =========================================================

    public static BigDecimal getThisMonthRevenue() {

        String sql =
                "SELECT COALESCE(SUM(total_amount), 0) AS total "
                + "FROM sales "
                + "WHERE YEAR(sale_date) = YEAR(CURDATE()) "
                + "AND MONTH(sale_date) = MONTH(CURDATE())";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        pst.executeQuery()
        ) {

            if (rs.next()) {

                BigDecimal total =
                        rs.getBigDecimal(
                                "total"
                        );

                if (total != null) {

                    return total;
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR GETTING MONTHLY REVENUE: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return BigDecimal.ZERO;
    }
}