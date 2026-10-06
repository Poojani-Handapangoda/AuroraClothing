/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package auroraclothing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // =========================================================
    // AURORA DATABASE CONFIGURATION
    // =========================================================

    private static final String URL =
            "jdbc:mysql://localhost:3306/aurora_clothing";

    private static final String USER =
            "root";

    private static final String PASSWORD =
            "";

    // =========================================================
    // GET DATABASE CONNECTION
    // =========================================================

    public static Connection getConnection() {

        try {

            // =================================================
            // LOAD MYSQL JDBC DRIVER
            // =================================================

            Class.forName(
                    "com.mysql.cj.jdbc.Driver"
            );

            // =================================================
            // CONNECT TO AURORA DATABASE
            // =================================================

            Connection connection =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD
                    );

            return connection;

        } catch (ClassNotFoundException e) {

            // JDBC DRIVER WAS NOT FOUND
            System.out.println(
                    "MYSQL DRIVER NOT FOUND: "
                    + e.getMessage()
            );

            return null;

        } catch (SQLException e) {

            // DATABASE CONNECTION ERROR
            System.out.println(
                    "Database connection failed: "
                    + e.getMessage()
            );

            return null;
        }
    }

    // =========================================================
    // TEST DATABASE CONNECTION
    // =========================================================

    public static void main(String[] args) {

        System.out.println(
                "Testing AURORA database connection..."
        );

        Connection connection =
                getConnection();

        if (connection != null) {

            System.out.println(
                    "AURORA DATABASE CONNECTED SUCCESSFULLY!"
            );

            try {

                connection.close();

                System.out.println(
                        "Database connection closed successfully."
                );

            } catch (SQLException e) {

                System.out.println(
                        "Error closing connection: "
                        + e.getMessage()
                );
            }

        } else {

            System.out.println(
                    "AURORA DATABASE CONNECTION FAILED!"
            );
        }
    }
}