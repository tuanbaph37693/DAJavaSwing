/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import reponsitory.DBConnect;

/**
 *
 * @author Admin
 */
public class JDBCHelper {

    public static PreparedStatement preparedStatement(String sql, Object... args) {
        try {
            Connection c = DBConnect.openConnection();
            PreparedStatement ps = null;
            if (sql.trim().startsWith("{")) {
                ps = c.prepareCall(sql);
            }
            ps = c.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            return ps;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ResultSet executeQuery(String sql, Object... args) {
        try {
            PreparedStatement ps = preparedStatement(sql, args);
            try {
                return ps.executeQuery();
            } finally {
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void executeUpdate(String sql, Object... args) {
        try {
            PreparedStatement ps = preparedStatement(sql, args);
            try {
                ps.executeUpdate();
            } finally {
                ps.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
