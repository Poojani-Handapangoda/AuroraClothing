/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auroraclothing;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesItemDAO {

    // =========================================================
    // ADD SALES ITEM
    // =========================================================

    public static boolean addSalesItem(
            String orderId,
            String productId,
            int quantity,
            BigDecimal unitPrice,
            BigDecimal subtotal
    ) {

        String sql =
                "INSERT INTO sale_items "
                + "(order_id, product_id, quantity, unit_price, subtotal) "
                + "VALUES (?, ?, ?, ?, ?)";

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
                    productId
            );

            pst.setInt(
                    3,
                    quantity
            );

            pst.setBigDecimal(
                    4,
                    unitPrice
            );

            pst.setBigDecimal(
                    5,
                    subtotal
            );

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "ERROR ADDING SALES ITEM: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }

// =========================================================
// GET ALL ITEMS FOR ONE ORDER
// =========================================================

public static List<Object[]> getItemsByOrderId(
        String orderId
) {

    List<Object[]> items =
            new ArrayList<>();


    String sql =
            "SELECT order_id, "
            + "product_id, "
            + "quantity, "
            + "unit_price, "
            + "subtotal "
            + "FROM sale_items "
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

            while (rs.next()) {

                /*
                 * Keep the same array positions expected
                 * by SalesPanel:
                 *
                 * [0] unused item ID
                 * [1] order ID
                 * [2] product ID
                 * [3] quantity
                 * [4] unit price
                 * [5] subtotal
                 */

                Object[] item = {

                    null,

                    rs.getString(
                            "order_id"
                    ),

                    rs.getString(
                            "product_id"
                    ),

                    rs.getInt(
                            "quantity"
                    ),

                    rs.getBigDecimal(
                            "unit_price"
                    ),

                    rs.getBigDecimal(
                            "subtotal"
                    )
                };


                items.add(
                        item
                );
            }
        }


        System.out.println(
                "FOUND "
                + items.size()
                + " ITEM(S) FOR ORDER "
                + orderId
        );


    } catch (SQLException e) {

        System.out.println(
                "ERROR LOADING ORDER ITEMS: "
                + e.getMessage()
        );

        e.printStackTrace();
    }


    return items;
}

    // =========================================================
    // UPDATE SALES ITEM
    // =========================================================

    public static boolean updateSalesItem(
            int salesItemId,
            String productId,
            int quantity,
            BigDecimal unitPrice,
            BigDecimal subtotal
    ) {

        String sql =
                "UPDATE sale_items SET "
                + "product_id = ?, "
                + "quantity = ?, "
                + "unit_price = ?, "
                + "subtotal = ? "
                + "WHERE sales_item_id = ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    productId
            );

            pst.setInt(
                    2,
                    quantity
            );

            pst.setBigDecimal(
                    3,
                    unitPrice
            );

            pst.setBigDecimal(
                    4,
                    subtotal
            );

            pst.setInt(
                    5,
                    salesItemId
            );

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "ERROR UPDATING SALES ITEM: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // DELETE ONE SALES ITEM
    // =========================================================

    public static boolean deleteSalesItem(
            int salesItemId
    ) {

        String sql =
                "DELETE FROM sale_items "
                + "WHERE sales_item_id = ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql)
        ) {

            pst.setInt(
                    1,
                    salesItemId
            );

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "ERROR DELETING SALES ITEM: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // DELETE ALL ITEMS BELONGING TO AN ORDER
    // =========================================================

    public static boolean deleteItemsByOrderId(
            String orderId
    ) {

        String sql =
                "DELETE FROM sale_items "
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

            pst.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "ERROR DELETING ORDER ITEMS: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // CALCULATE ORDER TOTAL FROM SALES ITEMS
    // =========================================================

    public static BigDecimal getOrderTotal(
            String orderId
    ) {

        String sql =
                "SELECT COALESCE(SUM(subtotal), 0) AS total "
                + "FROM sale_items "
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

                    BigDecimal total =
                            rs.getBigDecimal(
                                    "total"
                            );

                    return total != null
                            ? total
                            : BigDecimal.ZERO;
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR CALCULATING ORDER TOTAL: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return BigDecimal.ZERO;
    }
}