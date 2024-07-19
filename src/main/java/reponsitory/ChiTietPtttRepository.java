/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponsitory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import model.ChiTietPhuongThuc;
import model.PhuongThucThanhToan;

/**
 *
 * @author Lenovo IdeapadGaming
 */
public class ChiTietPtttRepository {
//    public int insertPttt(ChiTietPhuongThuc ctpt) {
//        
//    }
    
    public List<PhuongThucThanhToan> getALl() {
        String sql = "select [id], [phuong_thuc] from [phuong_thuc_thanh_toan]";
        
        try {
            Connection con = DBConnect.openConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ArrayList<PhuongThucThanhToan> lst = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { 
                PhuongThucThanhToan pttt = new PhuongThucThanhToan();
                
                pttt.setIdPttt(rs.getString("id"));
                pttt.setTenPttt(rs.getString("phuong_thuc"));
                
                lst.add(pttt);
            }
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public PhuongThucThanhToan getModel(String tenPttt) {
        String sql = "select [id], [phuong_thuc] from [phuong_thuc_thanh_toan] where [phuong_thuc] = ?";
        
        try {
            Connection con = DBConnect.openConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, tenPttt);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                PhuongThucThanhToan pttt = new PhuongThucThanhToan();
                
                pttt.setIdPttt(rs.getString(1));
                pttt.setTenPttt(rs.getString(2));
                
                return pttt;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int ThanhToan(ChiTietPhuongThuc ctpt) {
        String sql = "insert into [pttt_chi_tiet] values (?, ?, ?, ?, ?, ?, getdate(), null, N'Hoạt động', 0)";
        
        try {
            Connection con = DBConnect.openConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, UUID.randomUUID().toString());
            ps.setString(2, ctpt.getIdHD());
            ps.setString(3, ctpt.getIdPttt());
            ps.setBigDecimal(4, ctpt.getSoTienMat());
            ps.setBigDecimal(5, ctpt.getChuyenKhoan());
            ps.setBigDecimal(6, ctpt.getTongTien());
            
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
}
