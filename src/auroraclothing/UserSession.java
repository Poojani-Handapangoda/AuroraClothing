/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auroraclothing;

public class UserSession {

    private static int userId;
    private static String fullName;
    private static String username;
    private static String role;

    private UserSession() {
    }

    public static void setUser(
            int id,
            String name,
            String user,
            String userRole
    ) {

        userId = id;
        fullName = name;
        username = user;
        role = userRole;
    }

    public static int getUserId() {
        return userId;
    }

    public static String getFullName() {
        return fullName;
    }

    public static String getUsername() {
        return username;
    }

    public static String getRole() {
        return role;
    }

    public static void clear() {

        userId = 0;
        fullName = null;
        username = null;
        role = null;
    }
    public static void clearSession() {
    userId = 0;
    username = null;
    fullName = null;
    role = null;
}
    // =========================================================
// CHECK MANAGEMENT PERMISSION
// Admin / Administrator / Manager only
// =========================================================
public static boolean canManageSensitiveRecords() {

    if (role == null) {
        return false;
    }

    String currentRole =
            role.trim();

    return currentRole.equalsIgnoreCase("Admin")
            || currentRole.equalsIgnoreCase("Administrator")
            || currentRole.equalsIgnoreCase("Manager");
}
}