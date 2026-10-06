/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package auroraclothing;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // =========================================================
    // ADD USER
    // user_id is AUTO_INCREMENT, so we do NOT insert it.
    // =========================================================

    public static boolean addUser(
            String fullName,
            String username,
            String password,
            String role,
            String status
    ) {

        String sql =
                "INSERT INTO users "
                + "(full_name, username, password, role, status) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql)
        ) {

            pst.setString(1, fullName);
            pst.setString(2, username);
            pst.setString(3, password);
            pst.setString(4, role);
            pst.setString(5, status);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "ERROR ADDING USER: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // GET ALL USERS
    // Password is intentionally NOT returned to the table.
    // =========================================================

    public static List<Object[]> getAllUsers() {

        List<Object[]> users =
                new ArrayList<>();

        String sql =
                "SELECT user_id, "
                + "full_name, "
                + "username, "
                + "role, "
                + "status "
                + "FROM users "
                + "ORDER BY user_id ASC";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        pst.executeQuery()
        ) {

            while (rs.next()) {

                users.add(
                        new Object[]{

                            rs.getInt(
                                    "user_id"
                            ),

                            rs.getString(
                                    "full_name"
                            ),

                            rs.getString(
                                    "username"
                            ),

                            rs.getString(
                                    "role"
                            ),

                            rs.getString(
                                    "status"
                            )
                        }
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR LOADING USERS: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return users;
    }


    // =========================================================
    // GET ONE USER BY ID
    // =========================================================

    public static Object[] getUserById(
            int userId
    ) {

        String sql =
                "SELECT user_id, "
                + "full_name, "
                + "username, "
                + "role, "
                + "status "
                + "FROM users "
                + "WHERE user_id = ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql)
        ) {

            pst.setInt(
                    1,
                    userId
            );

            try (
                    ResultSet rs =
                            pst.executeQuery()
            ) {

                if (rs.next()) {

                    return new Object[]{

                        rs.getInt(
                                "user_id"
                        ),

                        rs.getString(
                                "full_name"
                        ),

                        rs.getString(
                                "username"
                        ),

                        rs.getString(
                                "role"
                        ),

                        rs.getString(
                                "status"
                        )
                    };
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR GETTING USER: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return null;
    }


    // =========================================================
    // UPDATE USER
    // Password is NOT changed here.
    // =========================================================

    public static boolean updateUser(
            int userId,
            String fullName,
            String username,
            String role,
            String status
    ) {

        String sql =
                "UPDATE users SET "
                + "full_name = ?, "
                + "username = ?, "
                + "role = ?, "
                + "status = ? "
                + "WHERE user_id = ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    fullName
            );

            pst.setString(
                    2,
                    username
            );

            pst.setString(
                    3,
                    role
            );

            pst.setString(
                    4,
                    status
            );

            pst.setInt(
                    5,
                    userId
            );

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "ERROR UPDATING USER: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // UPDATE PASSWORD
    // =========================================================

    public static boolean updatePassword(
            int userId,
            String newPassword
    ) {

        String sql =
                "UPDATE users "
                + "SET password = ? "
                + "WHERE user_id = ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    newPassword
            );

            pst.setInt(
                    2,
                    userId
            );

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "ERROR UPDATING PASSWORD: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // DELETE USER
    // =========================================================

    public static boolean deleteUser(
            int userId
    ) {

        String sql =
                "DELETE FROM users "
                + "WHERE user_id = ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql)
        ) {

            pst.setInt(
                    1,
                    userId
            );

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "ERROR DELETING USER: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // CHECK WHETHER USERNAME EXISTS
    // Used when creating a new user.
    // =========================================================

    public static boolean usernameExists(
            String username
    ) {

        String sql =
                "SELECT user_id "
                + "FROM users "
                + "WHERE LOWER(username) = LOWER(?)";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    username
            );

            try (
                    ResultSet rs =
                            pst.executeQuery()
            ) {

                return rs.next();
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR CHECKING USERNAME: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // CHECK USERNAME WHILE EDITING
    // Ignores the currently selected user.
    // =========================================================

    public static boolean usernameExistsForOtherUser(
            String username,
            int userId
    ) {

        String sql =
                "SELECT user_id "
                + "FROM users "
                + "WHERE LOWER(username) = LOWER(?) "
                + "AND user_id <> ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    username
            );

            pst.setInt(
                    2,
                    userId
            );

            try (
                    ResultSet rs =
                            pst.executeQuery()
            ) {

                return rs.next();
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR CHECKING USERNAME: "
                    + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // LOGIN / AUTHENTICATE USER
    //
    // Returns:
    // [0] user_id
    // [1] full_name
    // [2] username
    // [3] role
    // [4] status
    // =========================================================

    public static Object[] authenticateUser(
            String username,
            String password
    ) {

        String sql =
                "SELECT user_id, "
                + "full_name, "
                + "username, "
                + "role, "
                + "status "
                + "FROM users "
                + "WHERE username = ? "
                + "AND password = ? "
                + "LIMIT 1";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement pst =
                        conn.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    username
            );

            pst.setString(
                    2,
                    password
            );

            try (
                    ResultSet rs =
                            pst.executeQuery()
            ) {

                if (rs.next()) {

                    return new Object[]{

                        rs.getInt(
                                "user_id"
                        ),

                        rs.getString(
                                "full_name"
                        ),

                        rs.getString(
                                "username"
                        ),

                        rs.getString(
                                "role"
                        ),

                        rs.getString(
                                "status"
                        )
                    };
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "LOGIN DATABASE ERROR: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return null;
    }


    // =========================================================
    // GET TOTAL USERS
    // Useful later for dashboard / reports.
    // =========================================================

    public static int getTotalUsers() {

        String sql =
                "SELECT COUNT(*) AS total "
                + "FROM users";

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
                    "ERROR GETTING USER COUNT: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return 0;
    }
    // =========================================================
// CHANGE USER PASSWORD
// =========================================================
public static boolean changePassword(
        int userId,
        String newPassword
) {

    String sql =
            "UPDATE users "
            + "SET password = ? "
            + "WHERE user_id = ?";

    try (
            Connection conn =
                    DatabaseConnection.getConnection();

            PreparedStatement pst =
                    conn.prepareStatement(sql)
    ) {

        pst.setString(
                1,
                newPassword
        );

        pst.setInt(
                2,
                userId
        );

        int rows =
                pst.executeUpdate();

        return rows > 0;

    } catch (SQLException e) {

        System.out.println(
                "ERROR CHANGING PASSWORD: "
                + e.getMessage()
        );

        e.printStackTrace();

        return false;
    }
}
// =========================================================
// UPDATE LOGGED-IN USER PROFILE
// =========================================================
public static boolean updateOwnProfile(
        int userId,
        String fullName,
        String username
) {

    String sql =
            "UPDATE users "
            + "SET full_name = ?, "
            + "username = ? "
            + "WHERE user_id = ?";


    try (
            Connection conn =
                    DatabaseConnection.getConnection();

            PreparedStatement pst =
                    conn.prepareStatement(sql)
    ) {

        pst.setString(
                1,
                fullName
        );

        pst.setString(
                2,
                username
        );

        pst.setInt(
                3,
                userId
        );


        int rows =
                pst.executeUpdate();


        return rows > 0;


    } catch (SQLException e) {

        System.out.println(
                "ERROR UPDATING PROFILE: "
                + e.getMessage()
        );

        e.printStackTrace();

        return false;
    }
}
}