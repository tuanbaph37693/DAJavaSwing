/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponsitory;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

import model.KhuyenMai;
import model.KhuyenMai1;


/**
 *
 * @author Admin
 */
public class PhieuGiamGiaRepositoy {

    public ArrayList<KhuyenMai> getAllKhuyenMai() {
        String sql = "select * from voucher";

        try {
            Connection conn = DBConnect.openConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ArrayList<KhuyenMai> lis = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMai k = new KhuyenMai();
                k.setMaKM(rs.getString("ma_voucher"));
                k.setTenKM(rs.getString("ten"));
                k.setSoluong(rs.getInt("so_luong"));
                k.setGiaTriGiam(rs.getString("gia_tri"));
                k.setTrangThai(rs.getString("trang_thai"));
                java.sql.Date ngayBD = rs.getDate("ngay_bat_dau");
                java.sql.Date ngayKT = rs.getDate("ngay_ket_thuc");
                k.setNgayBD(ngayBD != null ? new Date(ngayBD.getTime()) : null);
                k.setNgayKT(ngayKT != null ? new Date(ngayKT.getTime()) : null);
                lis.add(k);
            }
            return lis;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addKhuyenMai(KhuyenMai k) throws Exception {

        String sql = "insert into voucher(Id, ma_voucher, so_luong, ten, gia_tri, ngay_bat_dau, ngay_ket_thuc, trang_thai, mo_ta) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try ( Connection conn = DBConnect.openConnection();  PreparedStatement pstm = conn.prepareStatement(sql)) {
            UUID id = UUID.randomUUID();
            pstm.setObject(1, id);
            pstm.setString(2, k.getMaKM());
            pstm.setInt(3, k.getSoluong());
            pstm.setString(4, k.getTenKM());
            pstm.setString(5, k.getGiaTriGiam());
            pstm.setDate(6, k.getNgayBD());
            pstm.setDate(7, k.getNgayKT());
            pstm.setString(8, k.getTrangThai());
            pstm.setString(9, k.getMoTa());

            return pstm.executeUpdate() > 0;
        }

    }

    public boolean updateKhuyenMai(KhuyenMai k) throws Exception {
        String sql = "UPDATE [dbo].[voucher] "
                + " SET [ten] = ?, [gia_tri] = ?, [so_luong] = ?, [ngay_bat_dau] = ?, [ngay_ket_thuc] = ?, [trang_thai] = ?, [mo_ta] = ? "
                + " WHERE [ma_voucher] = ?";
        try ( Connection conn = DBConnect.openConnection();  PreparedStatement ps = conn.prepareCall(sql)) {
            ps.setString(1, k.getTenKM());
            ps.setString(2, k.getGiaTriGiam());
            ps.setInt(3, k.getSoluong());
            ps.setDate(4, k.getNgayBD());
            ps.setDate(5, k.getNgayKT());
            ps.setString(6, k.getTrangThai());
            ps.setString(7, k.getMoTa());
            ps.setString(8, k.getMaKM());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteKhuyenMai(String maKhuyenMai) throws Exception {

        String sql = "delete from voucher "
                + " where ma_voucher = ?";
        try ( Connection conn = DBConnect.openConnection();  PreparedStatement ps = conn.prepareCall(sql)) {
            ps.setString(1, maKhuyenMai);
            return ps.executeUpdate() > 0;
        }

    }
    
    public KhuyenMai1 getModel(String maVoucher){
        String sql = "select [id], [ma_voucher], [gia_tri] from voucher where [ma_voucher] = ? ";
        
        try {
            Connection conn = DBConnect.openConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, maVoucher);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                KhuyenMai1 km = new KhuyenMai1();
                
                km.setId(UUID.fromString(rs.getObject("id").toString()));
                km.setMaKM(rs.getString("ma_voucher"));
                km.setGiaTriGiam(rs.getString("gia_tri"));
                
                return km;
            }
        } catch (Exception e) {
            e.printStackTrace();;
        }
        return null;
    }
    
}
