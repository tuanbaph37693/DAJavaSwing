/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empty;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;
import model.PhieuGiamGia;
import reponsitory.DBConnect;
/**
 *
 * @author Asus
 */
public class VoucherReponsitory {

    public ArrayList<PhieuGiamGia> getAllKhuyenMai() throws SQLException, Exception {
        ArrayList<PhieuGiamGia> lstKM = new ArrayList<>();
        String sql = "select * from voucher";
        Connection conn = DBConnect.openConnection();
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                PhieuGiamGia k = new PhieuGiamGia();
                k.setMaVoucher(rs.getString("ma_voucher"));
                k.setTenVoucher(rs.getString("ten"));
                k.setSoLuong(rs.getInt("so_luong"));
                k.setMoTa(rs.getString("mo_ta"));
                k.setGiaTri(rs.getFloat("gia_tri"));
                k.setTrangThai(rs.getString("trang_thai"));
                java.sql.Date ngayBD = rs.getDate("ngay_bat_dau");
                java.sql.Date ngayKT = rs.getDate("ngay_ket_thuc");
                k.setNgayBatdau(ngayBD != null ? new Date(ngayBD.getTime()) : null);
                k.setNgayKetthuc(ngayKT != null ? new Date(ngayKT.getTime()) : null);
                lstKM.add(k);
            }
        }
        return lstKM;
    }

    public boolean addKhuyenMai(PhieuGiamGia k) throws Exception {

        String sql = "insert into voucher(Id, ma_voucher, so_luong, ten, gia_tri, ngay_bat_dau, ngay_ket_thuc, trang_thai, mo_ta) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnect.openConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {
            UUID id = UUID.randomUUID();
            pstm.setObject(1, id);
            pstm.setString(2, k.getMaVoucher());
            pstm.setInt(3, k.getSoLuong());
            pstm.setString(4, k.getTenVoucher());
            pstm.setFloat(5, k.getGiaTri());
            pstm.setDate(6, k.getNgayBatdau());
            pstm.setDate(7, k.getNgayKetthuc());
            pstm.setObject(8, k.getTrangThai());
            pstm.setString(9, k.getMoTa());
            return pstm.executeUpdate() > 0;
        }

    }

    public boolean updateKhuyenMai(PhieuGiamGia k) throws Exception {
        String sql = "UPDATE [dbo].[voucher] "
                + " SET [ten] = ?, [gia_tri] = ?, [so_luong] = ?, [ngay_bat_dau] = ?, [ngay_ket_thuc] = ?, [trang_thai] = ?, [mo_ta] = ? "
                + " WHERE [ma_voucher] = ?";
        try (Connection conn = DBConnect.openConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ps.setString(1, k.getTenVoucher());
            ps.setFloat(2, k.getGiaTri());
            ps.setInt(3, k.getSoLuong());
            ps.setDate(4, k.getNgayBatdau());
            ps.setDate(5, k.getNgayKetthuc());
            ps.setObject(6, k.getTrangThai());
            ps.setString(7, k.getMoTa());
            ps.setString(8, k.getMaVoucher());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteKhuyenMai(String maKhuyenMai) throws Exception {

        String sql = "delete from voucher "
                + " where ma_voucher = ?";
        try (Connection conn = DBConnect.openConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ps.setString(1, maKhuyenMai);
            return ps.executeUpdate() > 0;
        }

    }
}
