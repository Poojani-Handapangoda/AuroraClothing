/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auroraclothing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    // =========================================================
    // GET ALL CUSTOMERS
    // =========================================================

    public static List<Object[]> getAllCustomers() {

        List<Object[]> customerList = new ArrayList<>();

        String sql =
                "SELECT customer_id, "
                + "customer_name, "
                + "phone, "
                + "email, "
                + "status, "
                + "created_at "
                + "FROM customers "
                + "ORDER BY customer_id ASC";

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

                    rs.getString("customer_id"),

                    rs.getString("customer_name"),

                    rs.getString("phone"),

                    rs.getString("email"),

                    rs.getString("status"),

                    rs.getTimestamp("created_at")
                };

                customerList.add(row);
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR LOADING CUSTOMERS: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return customerList;
    }


    // =========================================================
    // GET ONE CUSTOMER
    // =========================================================

    public static Object[] getCustomerById(
            String customerId
    ) {

        String sql =
                "SELECT customer_id, "
                + "customer_name, "
                + "phone, "
                + "email, "
                + "status, "
                + "created_at "
                + "FROM customers "
                + "WHERE customer_id = ?";

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

            try (
                    ResultSet rs =
                            pst.executeQuery()
            ) {

                if (rs.next()) {

                    return new Object[]{

                        rs.getString(
                                "customer_id"
                        ),

                        rs.getString(
                                "customer_name"
                        ),

                        rs.getString(
                                "phone"
                        ),

                        rs.getString(
                                "email"
                        ),

                        rs.getString(
                                "status"
                        ),

                        rs.getTimestamp(
                                "created_at"
                        )
                    };
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR GETTING CUSTOMER: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return null;
    }


    // =========================================================
    // ADD CUSTOMER
    // =========================================================

    public static boolean addCustomer(
            String customerId,
            String customerName,
            String phone,
            String email,
            String status
    ) {

        String sql =
                "INSERT INTO customers "
                + "(customer_id, customer_name, phone, email, status, created_at) "
                + "VALUES (?, ?, ?, ?, ?, NOW())";

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

            pst.setString(
                    2,
                    customerName
            );

            pst.setString(
                    3,
                    phone
            );

            pst.setString(
                    4,
                    email
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
                    "ERROR ADDING CUSTOMER: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // UPDATE CUSTOMER
    // =========================================================

    public static boolean updateCustomer(
            String customerId,
            String customerName,
            String phone,
            String email,
            String status
    ) {

        String sql =
                "UPDATE customers "
                + "SET customer_name = ?, "
                + "phone = ?, "
                + "email = ?, "
                + "status = ? "
                + "WHERE customer_id = ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    customerName
            );

            pst.setString(
                    2,
                    phone
            );

            pst.setString(
                    3,
                    email
            );

            pst.setString(
                    4,
                    status
            );

            pst.setString(
                    5,
                    customerId
            );


            int rows =
                    pst.executeUpdate();


            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "ERROR UPDATING CUSTOMER: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // DELETE CUSTOMER
    // =========================================================

    public static boolean deleteCustomer(
            String customerId
    ) {

        String sql =
                "DELETE FROM customers "
                + "WHERE customer_id = ?";

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


            int rows =
                    pst.executeUpdate();


            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "ERROR DELETING CUSTOMER: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // CHECK WHETHER CUSTOMER ID EXISTS
    // =========================================================

    public static boolean customerExists(
            String customerId
    ) {

        String sql =
                "SELECT customer_id "
                + "FROM customers "
                + "WHERE customer_id = ?";

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

            try (
                    ResultSet rs =
                            pst.executeQuery()
            ) {

                return rs.next();
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR CHECKING CUSTOMER ID: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // CHECK WHETHER EMAIL ALREADY EXISTS
    // =========================================================

    public static boolean emailExists(
            String email
    ) {

        String sql =
                "SELECT customer_id "
                + "FROM customers "
                + "WHERE LOWER(email) = LOWER(?)";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    email
            );

            try (
                    ResultSet rs =
                            pst.executeQuery()
            ) {

                return rs.next();
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR CHECKING CUSTOMER EMAIL: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // CHECK EMAIL WHEN EDITING
    // Allows customer's own current email
    // =========================================================

    public static boolean emailExistsForAnotherCustomer(
            String email,
            String customerId
    ) {

        String sql =
                "SELECT customer_id "
                + "FROM customers "
                + "WHERE LOWER(email) = LOWER(?) "
                + "AND customer_id <> ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    email
            );

            pst.setString(
                    2,
                    customerId
            );

            try (
                    ResultSet rs =
                            pst.executeQuery()
            ) {

                return rs.next();
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR CHECKING CUSTOMER EMAIL: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // CHECK WHETHER PHONE ALREADY EXISTS
    // =========================================================

    public static boolean phoneExists(
            String phone
    ) {

        String sql =
                "SELECT customer_id "
                + "FROM customers "
                + "WHERE phone = ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    phone
            );

            try (
                    ResultSet rs =
                            pst.executeQuery()
            ) {

                return rs.next();
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR CHECKING CUSTOMER PHONE: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // CHECK PHONE WHEN EDITING
    // Allows customer's own current phone
    // =========================================================

    public static boolean phoneExistsForAnotherCustomer(
            String phone,
            String customerId
    ) {

        String sql =
                "SELECT customer_id "
                + "FROM customers "
                + "WHERE phone = ? "
                + "AND customer_id <> ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    phone
            );

            pst.setString(
                    2,
                    customerId
            );

            try (
                    ResultSet rs =
                            pst.executeQuery()
            ) {

                return rs.next();
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR CHECKING CUSTOMER PHONE: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }
}