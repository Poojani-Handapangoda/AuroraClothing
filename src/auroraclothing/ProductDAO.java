/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auroraclothing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // =========================================================
    // CREATE - ADD PRODUCT
    // =========================================================
    public static boolean addProduct(
            String productId,
            String productName,
            String category,
            double price,
            int stock,
            String status
    ) {

        String sql =
                "INSERT INTO products "
                + "(product_id, product_name, category, price, stock, status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, productId);
            statement.setString(2, productName);
            statement.setString(3, category);
            statement.setDouble(4, price);
            statement.setInt(5, stock);
            statement.setString(6, status);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error adding product: "
                    + e.getMessage()
            );

            return false;
        }
    }


    // =========================================================
    // READ - GET ALL PRODUCTS
    // =========================================================
    public static List<Object[]> getAllProducts() {

        List<Object[]> products = new ArrayList<>();

        String sql =
                "SELECT product_id, product_name, category, "
                + "price, stock, status "
                + "FROM products "
                + "ORDER BY product_id ASC";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet result = statement.executeQuery()
        ) {

            while (result.next()) {

                Object[] row = {
                    result.getString("product_id"),
                    result.getString("product_name"),
                    result.getString("category"),
                    result.getDouble("price"),
                    result.getInt("stock"),
                    result.getString("status")
                };

                products.add(row);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error loading products: "
                    + e.getMessage()
            );
        }

        return products;
    }


    // =========================================================
    // READ ONE PRODUCT
    // =========================================================
    public static Object[] getProductById(
            String productId
    ) {

        String sql =
                "SELECT product_id, product_name, category, "
                + "price, stock, status "
                + "FROM products "
                + "WHERE product_id = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, productId);

            try (ResultSet result = statement.executeQuery()) {

                if (result.next()) {

                    return new Object[]{
                        result.getString("product_id"),
                        result.getString("product_name"),
                        result.getString("category"),
                        result.getDouble("price"),
                        result.getInt("stock"),
                        result.getString("status")
                    };
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error loading product: "
                    + e.getMessage()
            );
        }

        return null;
    }


    // =========================================================
    // UPDATE PRODUCT
    // =========================================================
    public static boolean updateProduct(
            String productId,
            String productName,
            String category,
            double price,
            int stock,
            String status
    ) {

        String sql =
                "UPDATE products SET "
                + "product_name = ?, "
                + "category = ?, "
                + "price = ?, "
                + "stock = ?, "
                + "status = ? "
                + "WHERE product_id = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, productName);
            statement.setString(2, category);
            statement.setDouble(3, price);
            statement.setInt(4, stock);
            statement.setString(5, status);
            statement.setString(6, productId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error updating product: "
                    + e.getMessage()
            );

            return false;
        }
    }


    // =========================================================
    // DELETE PRODUCT
    // =========================================================
    public static boolean deleteProduct(
            String productId
    ) {

        String sql =
                "DELETE FROM products "
                + "WHERE product_id = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, productId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error deleting product: "
                    + e.getMessage()
            );

            return false;
        }
    }


    // =========================================================
    // CHECK DUPLICATE PRODUCT ID
    // =========================================================
    public static boolean productExists(
            String productId
    ) {

        String sql =
                "SELECT product_id "
                + "FROM products "
                + "WHERE product_id = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, productId);

            try (ResultSet result = statement.executeQuery()) {

                return result.next();
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error checking product: "
                    + e.getMessage()
            );

            return false;
        }
    }
}