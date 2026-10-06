/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auroraclothing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {

    // =========================================================
    // GET ALL INVENTORY RECORDS
    // =========================================================

    public static List<Object[]> getAllInventory() {

        List<Object[]> inventoryList = new ArrayList<>();

        String sql =
                "SELECT i.item_id, "
                + "i.product_id, "
                + "p.product_name, "
                + "p.category, "
                + "i.quantity, "
                + "i.reorder_level, "
                + "i.status "
                + "FROM inventory i "
                + "INNER JOIN products p "
                + "ON i.product_id = p.product_id "
                + "ORDER BY i.item_id ASC";

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

                    rs.getString("item_id"),

                    rs.getString("product_id"),

                    rs.getString("product_name"),

                    rs.getString("category"),

                    rs.getInt("quantity"),

                    rs.getInt("reorder_level"),

                    rs.getString("status")
                };

                inventoryList.add(row);
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR LOADING INVENTORY: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return inventoryList;
    }


    // =========================================================
    // GET ONE INVENTORY RECORD
    // =========================================================

    public static Object[] getInventoryById(
            String itemId
    ) {

        String sql =
                "SELECT i.item_id, "
                + "i.product_id, "
                + "p.product_name, "
                + "p.category, "
                + "i.quantity, "
                + "i.reorder_level, "
                + "i.status "
                + "FROM inventory i "
                + "INNER JOIN products p "
                + "ON i.product_id = p.product_id "
                + "WHERE i.item_id = ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    itemId
            );

            try (
                    ResultSet rs =
                            pst.executeQuery()
            ) {

                if (rs.next()) {

                    return new Object[]{

                        rs.getString("item_id"),

                        rs.getString("product_id"),

                        rs.getString("product_name"),

                        rs.getString("category"),

                        rs.getInt("quantity"),

                        rs.getInt("reorder_level"),

                        rs.getString("status")
                    };
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR GETTING INVENTORY RECORD: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return null;
    }


    // =========================================================
    // ADD INVENTORY RECORD
    // =========================================================

    public static boolean addInventory(
            String itemId,
            String productId,
            int quantity,
            int reorderLevel,
            String status
    ) {

        String sql =
                "INSERT INTO inventory "
                + "(item_id, product_id, quantity, reorder_level, status) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    itemId
            );

            pst.setString(
                    2,
                    productId
            );

            pst.setInt(
                    3,
                    quantity
            );

            pst.setInt(
                    4,
                    reorderLevel
            );

            pst.setString(
                    5,
                    status
            );

            int rows =
                    pst.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "ERROR ADDING INVENTORY: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // UPDATE INVENTORY RECORD
    // =========================================================

    public static boolean updateInventory(
            String itemId,
            String productId,
            int quantity,
            int reorderLevel,
            String status
    ) {

        String sql =
                "UPDATE inventory "
                + "SET product_id = ?, "
                + "quantity = ?, "
                + "reorder_level = ?, "
                + "status = ? "
                + "WHERE item_id = ?";

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

            pst.setInt(
                    3,
                    reorderLevel
            );

            pst.setString(
                    4,
                    status
            );

            pst.setString(
                    5,
                    itemId
            );

            int rows =
                    pst.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "ERROR UPDATING INVENTORY: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // DELETE INVENTORY RECORD
    // =========================================================

    public static boolean deleteInventory(
            String itemId
    ) {

        String sql =
                "DELETE FROM inventory "
                + "WHERE item_id = ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    itemId
            );

            int rows =
                    pst.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "ERROR DELETING INVENTORY: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // CHECK WHETHER ITEM ID ALREADY EXISTS
    // =========================================================

    public static boolean inventoryExists(
            String itemId
    ) {

        String sql =
                "SELECT item_id "
                + "FROM inventory "
                + "WHERE item_id = ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    itemId
            );

            try (
                    ResultSet rs =
                            pst.executeQuery()
            ) {

                return rs.next();
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR CHECKING INVENTORY ID: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // CHECK WHETHER PRODUCT ALREADY HAS INVENTORY RECORD
    // =========================================================

    public static boolean productAlreadyInInventory(
            String productId
    ) {

        String sql =
                "SELECT product_id "
                + "FROM inventory "
                + "WHERE product_id = ?";

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

            try (
                    ResultSet rs =
                            pst.executeQuery()
            ) {

                return rs.next();
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR CHECKING INVENTORY PRODUCT: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // GET PRODUCTS FOR PRODUCT DROPDOWN
    // =========================================================

    public static List<Object[]> getProducts() {

        List<Object[]> productList =
                new ArrayList<>();

        String sql =
                "SELECT product_id, product_name, category "
                + "FROM products "
                + "ORDER BY product_name ASC";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        pst.executeQuery()
        ) {

            while (rs.next()) {

                productList.add(
                        new Object[]{

                            rs.getString(
                                    "product_id"
                            ),

                            rs.getString(
                                    "product_name"
                            ),

                            rs.getString(
                                    "category"
                            )
                        }
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR LOADING PRODUCTS FOR INVENTORY: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return productList;
    }


    // =========================================================
    // AUTOMATIC STATUS
    // =========================================================

    public static String calculateStatus(
            int quantity,
            int reorderLevel
    ) {

        if (quantity <= 0) {

            return "Out of Stock";

        } else if (
                quantity <= reorderLevel
        ) {

            return "Low Stock";

        } else {

            return "In Stock";
        }
    }
        // =========================================================
    // GET AVAILABLE STOCK FOR PRODUCT
    // =========================================================

    public static int getAvailableStock(
            String productId
    ) {

        String sql =
                "SELECT quantity "
                + "FROM inventory "
                + "WHERE product_id = ?";

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

            try (
                    ResultSet rs =
                            pst.executeQuery()
            ) {

                if (rs.next()) {

                    return rs.getInt(
                            "quantity"
                    );
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR GETTING AVAILABLE STOCK: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return -1;
    }


    // =========================================================
    // CHECK ENOUGH STOCK
    // =========================================================

    public static boolean hasEnoughStock(
            String productId,
            int requiredQuantity
    ) {

        int available =
                getAvailableStock(
                        productId
                );

        return available >= requiredQuantity;
    }


    // =========================================================
    // REDUCE STOCK AFTER COMPLETED SALE
    // =========================================================

    public static boolean reduceStockForSale(
            String productId,
            int soldQuantity
    ) {

        if (soldQuantity <= 0) {

            return false;
        }

        Connection conn = null;

        try {

            conn =
                    DatabaseConnection.getConnection();

            conn.setAutoCommit(false);


            // ================================================
            // GET CURRENT INVENTORY
            // ================================================

            String selectSql =
                    "SELECT quantity, reorder_level "
                    + "FROM inventory "
                    + "WHERE product_id = ? "
                    + "FOR UPDATE";


            int currentQuantity;
            int reorderLevel;


            try (
                    PreparedStatement pst =
                            conn.prepareStatement(
                                    selectSql
                            )
            ) {

                pst.setString(
                        1,
                        productId
                );

                try (
                        ResultSet rs =
                                pst.executeQuery()
                ) {

                    if (!rs.next()) {

                        System.out.println(
                                "NO INVENTORY RECORD FOUND FOR PRODUCT: "
                                + productId
                        );

                        conn.rollback();

                        return false;
                    }


                    currentQuantity =
                            rs.getInt(
                                    "quantity"
                            );


                    reorderLevel =
                            rs.getInt(
                                    "reorder_level"
                            );
                }
            }


            // ================================================
            // CHECK STOCK
            // ================================================

            if (
                    currentQuantity
                    < soldQuantity
            ) {

                System.out.println(
                        "INSUFFICIENT STOCK FOR PRODUCT: "
                        + productId
                );

                conn.rollback();

                return false;
            }


            // ================================================
            // CALCULATE NEW QUANTITY
            // ================================================

            int newQuantity =
                    currentQuantity
                    - soldQuantity;


            String newStatus =
                    calculateStatus(
                            newQuantity,
                            reorderLevel
                    );


            // ================================================
            // UPDATE INVENTORY TABLE
            // ================================================

            String inventorySql =
                    "UPDATE inventory "
                    + "SET quantity = ?, "
                    + "status = ? "
                    + "WHERE product_id = ?";


            try (
                    PreparedStatement pst =
                            conn.prepareStatement(
                                    inventorySql
                            )
            ) {

                pst.setInt(
                        1,
                        newQuantity
                );

                pst.setString(
                        2,
                        newStatus
                );

                pst.setString(
                        3,
                        productId
                );


                int rows =
                        pst.executeUpdate();


                if (rows == 0) {

                    conn.rollback();

                    return false;
                }
            }


            // ================================================
            // KEEP products.stock SYNCHRONIZED
            // ================================================

            String productSql =
                    "UPDATE products "
                    + "SET stock = ? "
                    + "WHERE product_id = ?";


            try (
                    PreparedStatement pst =
                            conn.prepareStatement(
                                    productSql
                            )
            ) {

                pst.setInt(
                        1,
                        newQuantity
                );

                pst.setString(
                        2,
                        productId
                );


                int rows =
                        pst.executeUpdate();


                if (rows == 0) {

                    conn.rollback();

                    return false;
                }
            }


            // ================================================
            // SAVE BOTH UPDATES
            // ================================================

            conn.commit();


            System.out.println(
                    "STOCK REDUCED: "
                    + productId
                    + " | "
                    + currentQuantity
                    + " -> "
                    + newQuantity
            );


            return true;


        } catch (SQLException e) {

            try {

                if (conn != null) {

                    conn.rollback();
                }

            } catch (SQLException rollbackError) {

                rollbackError.printStackTrace();
            }


            System.out.println(
                    "ERROR REDUCING STOCK: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;


        } finally {

            try {

                if (conn != null) {

                    conn.setAutoCommit(true);

                    conn.close();
                }

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }


    // =========================================================
    // RESTORE STOCK
    // USED WHEN COMPLETED SALE IS EDITED OR DELETED
    // =========================================================

    public static boolean restoreStockFromSale(
            String productId,
            int quantityToRestore
    ) {

        if (quantityToRestore <= 0) {

            return false;
        }


        Connection conn = null;


        try {

            conn =
                    DatabaseConnection.getConnection();

            conn.setAutoCommit(false);


            // ================================================
            // GET CURRENT INVENTORY
            // ================================================

            String selectSql =
                    "SELECT quantity, reorder_level "
                    + "FROM inventory "
                    + "WHERE product_id = ? "
                    + "FOR UPDATE";


            int currentQuantity;
            int reorderLevel;


            try (
                    PreparedStatement pst =
                            conn.prepareStatement(
                                    selectSql
                            )
            ) {

                pst.setString(
                        1,
                        productId
                );


                try (
                        ResultSet rs =
                                pst.executeQuery()
                ) {

                    if (!rs.next()) {

                        System.out.println(
                                "NO INVENTORY RECORD FOUND FOR PRODUCT: "
                                + productId
                        );

                        conn.rollback();

                        return false;
                    }


                    currentQuantity =
                            rs.getInt(
                                    "quantity"
                            );


                    reorderLevel =
                            rs.getInt(
                                    "reorder_level"
                            );
                }
            }


            // ================================================
            // RESTORE QUANTITY
            // ================================================

            int newQuantity =
                    currentQuantity
                    + quantityToRestore;


            String newStatus =
                    calculateStatus(
                            newQuantity,
                            reorderLevel
                    );


            // ================================================
            // UPDATE INVENTORY
            // ================================================

            String inventorySql =
                    "UPDATE inventory "
                    + "SET quantity = ?, "
                    + "status = ? "
                    + "WHERE product_id = ?";


            try (
                    PreparedStatement pst =
                            conn.prepareStatement(
                                    inventorySql
                            )
            ) {

                pst.setInt(
                        1,
                        newQuantity
                );

                pst.setString(
                        2,
                        newStatus
                );

                pst.setString(
                        3,
                        productId
                );


                int rows =
                        pst.executeUpdate();


                if (rows == 0) {

                    conn.rollback();

                    return false;
                }
            }


            // ================================================
            // SYNCHRONIZE PRODUCTS STOCK
            // ================================================

            String productSql =
                    "UPDATE products "
                    + "SET stock = ? "
                    + "WHERE product_id = ?";


            try (
                    PreparedStatement pst =
                            conn.prepareStatement(
                                    productSql
                            )
            ) {

                pst.setInt(
                        1,
                        newQuantity
                );

                pst.setString(
                        2,
                        productId
                );


                int rows =
                        pst.executeUpdate();


                if (rows == 0) {

                    conn.rollback();

                    return false;
                }
            }


            conn.commit();


            System.out.println(
                    "STOCK RESTORED: "
                    + productId
                    + " | "
                    + currentQuantity
                    + " -> "
                    + newQuantity
            );


            return true;


        } catch (SQLException e) {

            try {

                if (conn != null) {

                    conn.rollback();
                }

            } catch (SQLException rollbackError) {

                rollbackError.printStackTrace();
            }


            System.out.println(
                    "ERROR RESTORING STOCK: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;


        } finally {

            try {

                if (conn != null) {

                    conn.setAutoCommit(true);

                    conn.close();
                }

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }
}