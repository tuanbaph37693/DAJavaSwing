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
import model.NguoiDung;

/**
 *
 * @author Asus
 */
public  class NguoiDungReponsitory {

    public NguoiDung checkLogin(String manguoidung, String matkhau) throws Exception {

        String sql = "SELECT id,ho_ten, ma_nguoi_dung, mat_khau, vai_tro FROM nguoi_dung WHERE ma_nguoi_dung = ? AND mat_khau = ?";

        try (Connection conn = DBConnect.openConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, manguoidung);
            st.setString(2, matkhau);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    NguoiDung nd = new NguoiDung();
                    String idND = (String) rs.getObject("id");
                    nd.setIdND(UUID.fromString(idND));
                    nd.setHoTen(rs.getString("ho_ten"));
                    nd.setMaNguoidung(manguoidung);
                    nd.setMatKhau(rs.getString("mat_khau"));
                    nd.setVaiTro(rs.getInt("vai_tro"));
                    return nd;
                }
            }
        }
        return null;
    }

    public ArrayList<NguoiDung> getAllNguoiDung() throws Exception {
        ArrayList<NguoiDung> listNV = new ArrayList<>();
        String sql = "select * from [nguoi_dung]";
        Connection con = DBConnect.openConnection();
        Statement stm;
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                NguoiDung nd = new NguoiDung();
                nd.setMaNguoidung(rs.getString("ma_nguoi_dung"));
                nd.setHoTen(rs.getString("ho_ten"));
                nd.setGioitinh(rs.getInt("gioi_tinh"));
                nd.setMatKhau(rs.getString("mat_khau"));
                nd.setVaiTro(rs.getInt("vai_tro"));
                nd.setSoDienthoai(rs.getString("so_dien_thoai"));
                nd.setNgaySinh(rs.getDate("ngay_sinh"));
                nd.seteMail(rs.getString("email"));
                nd.setTrangThai(rs.getString("trang_thai"));
                listNV.add(nd);
            }
            con.close();
            pstm.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNV;
    }

    public Integer addNguoiDung(NguoiDung nd) throws SQLException, Exception {
        Integer row = null;
        String sql = "INSERT INTO nguoi_dung (id, ma_nguoi_dung, ho_ten, gioi_tinh, mat_khau, vai_tro, so_dien_thoai, ngay_sinh, email, trang_thai) VALUES (newid(), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect.openConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nd.getMaNguoidung());
            ps.setString(2, nd.getHoTen());
            ps.setInt(3, nd.getGioitinh());
            ps.setString(4, nd.getMatKhau());
            ps.setInt(5, nd.getVaiTro());
            ps.setString(6, nd.getSoDienthoai());
            ps.setDate(7, nd.getNgaySinh());
            ps.setString(8, nd.geteMail());
            ps.setString(9, nd.getTrangThai());
            row = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return row;
    }

    public Integer updateNguoiDung(NguoiDung nd) throws Exception {
        Integer row = null;
        String sql = "UPDATE nguoi_dung SET ho_ten = ?, gioi_tinh = ?, mat_khau = ?, vai_tro = ?, so_dien_thoai = ?, ngay_sinh = ?, email = ?, trang_thai = ? WHERE ma_nguoi_dung = ?";
        try (Connection conn = DBConnect.openConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(9, nd.getMaNguoidung());
            ps.setString(1, nd.getHoTen());
            ps.setInt(2, nd.getGioitinh());
            ps.setString(3, nd.getMatKhau());
            ps.setInt(4, nd.getVaiTro());
            ps.setString(5, nd.getSoDienthoai());
            ps.setDate(6, nd.getNgaySinh());
            ps.setString(7, nd.geteMail());
            ps.setString(8, nd.getTrangThai());
            row = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return row;
    }

    public boolean DoiMatKhau(NguoiDung nd) throws Exception {
        String sql = "UPDATE nguoi_dung SET mat_khau = ? WHERE ma_nguoi_dung = ?";

        try (Connection conn = DBConnect.openConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(2, nd.getMaNguoidung());
            st.setString(1, nd.getMatKhau());
            return st.executeUpdate() > 0;
        }
    }

}
