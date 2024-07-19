/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;
import model.KhachHang;

/**
 *
 * @author Asus
 */
public class KhachHangReponsitory {

    public ArrayList<KhachHang> getAllKhachHang() throws SQLException, Exception {
        ArrayList<KhachHang> lstKH = new ArrayList<>();
        String sql = "SELECT * FROM khach_hang";
        Connection conn = DBConnect.openConnection();
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                KhachHang kh = CreatKhachHang(rs);
                lstKH.add(kh);
            }
        }
        return lstKH;
    }

    private KhachHang CreatKhachHang(final ResultSet rs) throws SQLException {
        KhachHang kh = new KhachHang();
        String id = String.valueOf(rs.getObject("id"));
        kh.setIdKH(UUID.fromString(id));
        kh.setTenKhachHang(rs.getString("ten"));
        kh.setSoDienthoai(rs.getString("so_dien_thoai"));
        kh.setDiaChi(rs.getString("dia_chi"));
        kh.setNgayTao(rs.getDate("ngay_tao"));
        kh.setNgaySua(rs.getDate("ngay_sua"));
        kh.setNguoiTao(rs.getString("nguoi_tao"));
        kh.setTrangThai(rs.getString("trang_thai"));
        return kh;
    }

    public boolean updateKhachHang(KhachHang kh) throws Exception {
        String sql = "UPDATE khach_hang SET ten = ?, so_dien_thoai = ?, dia_chi = ?, trang_thai = ?, ngay_sua = GETDATE(),nguoi_tao = ? WHERE id = ?";
        try (Connection conn = DBConnect.openConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, kh.getTenKhachHang());
            st.setString(2, kh.getSoDienthoai());
            st.setString(3, kh.getDiaChi());
            st.setString(4, kh.getTrangThai());
            st.setString(5, kh.getNguoiTao());
            String id = String.valueOf(kh.getIdKH());
            st.setObject(6, UUID.fromString(id));
            return st.executeUpdate() > 0;
        }
    }

    public boolean deleteKhachHang(Object id) throws Exception {
        String sql = "DELETE khach_hang WHERE id = ?";
        try (Connection conn = DBConnect.openConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
            st.setObject(1, id);
            return st.executeUpdate() > 0;
        }
    }

    public KhachHang timKhachHang(String soDienThoai) throws SQLException, Exception {
        Connection conn = DBConnect.openConnection();
        String sql = "SELECT* FROM khach_hang "
                + " WHERE so_dien_thoai = ? ";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, soDienThoai);
            try (ResultSet rs = st.executeQuery();) {
                if (rs.next()) {
                    KhachHang kh = CreatKhachHang(rs);
                    return kh;
                }
            }
        }
        return null;
    }

}
