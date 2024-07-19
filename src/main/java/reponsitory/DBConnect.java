/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponsitory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public class DBConnect {

    private static String driver = "localhost";
    private static String port = "1433";
    private static String dbname = "jdbc:sqlserver://localhost;databaseName= AMITYC_SHOP_EMPTY";
    private static String user = "sa";
    private static String password = "123456";

    public static Connection openConnection() {

        // Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://" + driver + ":" + port + ";"
                + "databaseName=" + dbname + ";encrypt=true;trustServerCertificate=true;";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(connectionUrl, user, password);
        } // Handle any errors that may have occurred.
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static PreparedStatement getstmt(String sql, Object... args) throws SQLException {
        Connection con = DriverManager.getConnection(dbname, user, password);
        PreparedStatement psmt = null;
        if (sql.trim().startsWith("{")) {
            psmt = con.prepareCall(sql);
        } else {
            psmt = con.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            psmt.setObject(i + 1, args[i]);
        }
        return psmt;
    }

    public static int Update(String sql, Object... args) {
        try {
            PreparedStatement stmt = getstmt(sql, args);
            try {
                return stmt.executeUpdate();
            } finally {
                stmt.getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet Query(String sql, Object... args) {
        try {
            PreparedStatement stmt = getstmt(sql, args);
            return stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
