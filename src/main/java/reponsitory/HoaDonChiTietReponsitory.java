/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponsitory;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import model.ChiTietSanPham;
import model.HoaDonChiTiet;
import model.HoaDonChiTiet1;
import model.SanPham;

/**
 *
 * @author Asus
 */
public class HoaDonChiTietReponsitory {

//    public ArrayList<HoaDonChiTiet> getAllHoaDonChiTiet(Object idHoaDon) throws Exception {
//        ArrayList<HoaDonChiTiet> lstHDCT = new ArrayList<>();
//        String sql = "SELECT kh.ten as tenkh,nd.ho_ten as tennv,sp.ten AS tensp ,kt.ten AS tenkt,hdct.so_luong as soluongmua ,hdct.gia_tien as giasp ,hd.tong_tien "
//                + "FROM hoa_don_chi_tiet hdct"
//                + "INNER JOIN chi_tiet_san_pham spct ON hdct.id_ctsp = spct.id"
//                + "INNER JOIN san_pham sp ON spct.id_san_pham = sp.id"
//                + "INNER JOIN kich_thuoc kt ON spct.id_kich_thuoc = kt.id"
//                + "INNER JOIN hoa_don hd ON hdct.id_hoa_don = hd.id"
//                + "INNER JOIN khach_hang kh ON hd.id_khach_hang = kh.id"
//                + "INNER JOIN nguoi_dung nd ON hd.id_nguoi_tao = nd.id"
//                + "WHERE hdct.id_hoa_don = '2a7d035d-706c-4a33-af1f-15fbab32833f';";
//
//        try (Connection conn = DBConnect.openConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
//            pst.setObject(1, idHoaDon);
//            try (ResultSet rs = pst.executeQuery()) {
//                while (rs.next()) {
//                    HoaDonChiTiet hdct = new HoaDonChiTiet();
//                    hdct.setTen(rs.getString("ten"));
//                    hdct.setKichthuoc(rs.getString("tenkt"));
//                    hdct.setSoLuong(rs.getInt("so_luong"));
//                    hdct.setGiaban(rs.getFloat("gia_tien"));
//                    hdct.setGiaTien(rs.getFloat("tong_gia_tien"));
//
//                    lstHDCT.add(hdct);
//                }
//            }
//        }
//
//        return lstHDCT;
//    }
    List<HoaDonChiTiet1> lst = new ArrayList<>();
    
    public ArrayList<HoaDonChiTiet1> getHDCT(String idHD) {
        String sql = "select hdct.id_hoa_don, sp.ma_san_pham, sp.ten, hdct.so_luong, ctsp.gia_ban, hdct.gia_tien, hdct.[id], hdct.[id_ctsp] from hoa_don_chi_tiet hdct\n"
                + "	inner join chi_tiet_san_pham ctsp on ctsp.id = hdct.id_ctsp\n"
                + "	inner join san_pham sp on sp.id = ctsp.id_san_pham\n"
                + "   where hdct.id_hoa_don = ?";
        try {
            Connection con = DBConnect.openConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, idHD);
            
            ArrayList<HoaDonChiTiet1> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet1 hdct = new HoaDonChiTiet1();
                
                hdct.setIdHD(rs.getString(1));
                hdct.setSp(new SanPham(rs.getString(2), rs.getString(3)));
                hdct.setSl(rs.getInt(4));
                hdct.setCtsp(new ChiTietSanPham(rs.getBigDecimal(5)));
                hdct.setThanhTien(rs.getBigDecimal(6));
                hdct.setIdHDCT(rs.getString(7));
                hdct.setIdCTSP(rs.getString(8));
                
                list.add(hdct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int addHDCT(HoaDonChiTiet1 hdct, int sl) {
        String sql = "Insert into hoa_don_chi_tiet values (?, ?, ?, ?, ?, getdate(), null, N'Hoạt động', 0)";
        
        try {
            Connection con = DBConnect.openConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, UUID.randomUUID().toString());
            ps.setString(2, hdct.getIdCTSP());
            ps.setString(3, hdct.getIdHD());
            ps.setInt(4, sl);
            ps.setBigDecimal(5, hdct.getThanhTien());
            
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
    
    public int Update(HoaDonChiTiet1 hdct, String idCtsp, int sl) {
        String sql = "update so_luong = ?, gia_tien = ? set hoa_don_chi_tiet where id_ctsp = ?";
        
        try {
            Connection con = DBConnect.openConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(3, idCtsp);
            ps.setInt(1, sl);
            ps.setBigDecimal(2, hdct.getThanhTien());
            
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
    
    public int deleteSP(String id) {
        String sql = "DELETE FROM [hoa_don_chi_tiet] where id = ?";
        
        try {
            Connection con = DBConnect.openConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, id);
            
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
    
    public HoaDonChiTiet getOneHDCT(String idHD, String idCTSP) {
        String sql = "SELECT * FROM hoa_don_chi_tiet WHere [id_hoa_don] = ? and [id_ctsp] = ?";
        try {
            Connection con = DBConnect.openConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, idHD);
            ps.setString(2, idCTSP);
            
            ArrayList<HoaDonChiTiet> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                
                hdct.setIdHDCT(UUID.fromString(rs.getString(1)));
                hdct.setIdCTSP(UUID.fromString(rs.getString(2)));
                hdct.setIdHD(UUID.fromString(rs.getString(3)));
                hdct.setSoluong(rs.getInt(4));
                hdct.setGiaTien(rs.getFloat(5));
                hdct.setNgayTao(rs.getDate(6));
                hdct.setNgaySua(rs.getDate(7));
                hdct.setTrangThai(rs.getString(8));
                hdct.setDelete(rs.getInt(9));
                
                list.add(hdct);
            }
            return list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public int UpdateSL(String idHD, String idCTSP, int sl) {
        String sql = "UPDATE hoa_don_chi_tiet SET so_luong = ? WHere [id_hoa_don] = ? and [id_ctsp] = ?";
        try {
            Connection con = DBConnect.openConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(2, idHD);
            ps.setString(3, idCTSP);
            ps.setInt(1, sl);
            
            if(getOneHDCT(idHD, idCTSP) != null) {
                return ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public static void main(String[] args) {
        HoaDonChiTietReponsitory hdctr = new HoaDonChiTietReponsitory();
        System.out.println(hdctr.UpdateSL("9A76BAFE-E9C3-4031-8AE1-0EF6FCA0848C", "797B8D50-7E0D-4EA2-977F-26CD24FAE91A", 20));
    }
}
