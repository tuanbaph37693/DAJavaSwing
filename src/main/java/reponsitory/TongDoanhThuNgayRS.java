/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponsitory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.TongDoanhThuNgay;

/**
 *
 * @author Asus
 */
public class TongDoanhThuNgayRS {

    public TongDoanhThuNgay getDoanhThuNgay(int ngaysua) throws Exception {
        Connection conn = DBConnect.openConnection();
        String sql = "SELECT "
                + "    COUNT(id) AS SOHOADON,"
                + "    SUM(tong_tien) AS tong_doanh_thu "
                + "FROM hoa_don "
                + "WHERE trang_thai = N'Đã thanh toán' AND DAY(ngay_sua) = ? ";
        try (PreparedStatement prt = conn.prepareStatement(sql)) {
            prt.setInt(1, ngaysua);
            try (ResultSet rs = prt.executeQuery()) {
                while (rs.next()) {
                    TongDoanhThuNgay tdtn = new TongDoanhThuNgay();
                    tdtn.setNgay(ngaysua);
                    tdtn.setSoHoaDon(rs.getInt("SOHOADON"));
                    tdtn.setTongDoanhThu(rs.getFloat("tong_doanh_thu"));
                    return tdtn;
                }
            }
        }
        return null;
    }

    public TongDoanhThuNgay getDoanhThuThang(int ngaysua) throws Exception {
        Connection conn = DBConnect.openConnection();
        String sql = "SELECT "
                + "    COUNT(id) AS SOHOADON,"
                + "    SUM(tong_tien) AS tong_doanh_thu "
                + " FROM hoa_don "
                + " WHERE trang_thai = N'Đã thanh toán' AND MONTH(ngay_sua) = ? ";
        try (PreparedStatement prt = conn.prepareStatement(sql)) {
            prt.setInt(1, ngaysua);
            try (ResultSet rs = prt.executeQuery()) {
                while (rs.next()) {
                    TongDoanhThuNgay tdtn = new TongDoanhThuNgay();
                    tdtn.setNgay(ngaysua);
                    tdtn.setSoHoaDon(rs.getInt("SOHOADON"));
                    tdtn.setTongDoanhThu(rs.getFloat("tong_doanh_thu"));
                    return tdtn;
                }
            }
        }
        return null;
    }

    public TongDoanhThuNgay getDoanhThuNam(int ngaysua) throws Exception {
        Connection conn = DBConnect.openConnection();
        String sql = "SELECT "
                + "    COUNT(id) AS SOHOADON,"
                + "    SUM(tong_tien) AS tong_doanh_thu "
                + " FROM hoa_don "
                + " WHERE trang_thai = N'Đã thanh toán' AND YEAR(ngay_sua) = ? ";
        try (PreparedStatement prt = conn.prepareStatement(sql)) {
            prt.setInt(1, ngaysua);
            try (ResultSet rs = prt.executeQuery()) {
                while (rs.next()) {
                    TongDoanhThuNgay tdtn = new TongDoanhThuNgay();
                    tdtn.setNgay(ngaysua);
                    tdtn.setSoHoaDon(rs.getInt("SOHOADON"));
                    tdtn.setTongDoanhThu(rs.getFloat("tong_doanh_thu"));
                    return tdtn;
                }
            }
        }
        return null;
    }
}
