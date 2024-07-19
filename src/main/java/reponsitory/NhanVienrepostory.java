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
import java.util.List;
import java.util.UUID;
import model.ChiTietSanPham;
import model.NhanVien;
import util.JDBCHelper;

public class NhanVienrepostory {

    public ArrayList<NhanVien> getAllNhanVien() throws Exception {
        ArrayList<NhanVien> listNV = new ArrayList<>();
        String sql = "select * from [nguoi_dung]";
        Connection con = DBConnect.openConnection();
        Statement stm;
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("ma_nguoi_dung"));
                nv.setTenNV(rs.getString("ho_ten"));
                nv.setMatKhau(rs.getString("mat_khau"));
                nv.setVaiTro(rs.getInt("vai_tro"));
                nv.setSdt(rs.getString("so_dien_thoai"));
                nv.setNgaysinh(rs.getString("ngay_sinh"));
                nv.setEmail(rs.getString("email"));
                nv.setTrangthai(rs.getString("trang_thai"));
                listNV.add(nv);
            }
            con.close();
            pstm.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNV;
    }

    public Integer updateNhanVien(NhanVien nv) throws Exception {
        Integer row = null;
        String sql = "UPDATE nguoi_dung SET ho_ten = ?, mat_khau = ?, vai_tro = ?, so_dien_thoai = ?, ngay_sinh = ?, email = ?, trang_thai = ? WHERE ma_nguoi_dung = ?";

        try ( Connection conn = DBConnect.openConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nv.getTenNV());
            ps.setString(2, nv.getMatKhau());
            // Chuyển đổi boolean sang kiểu dữ liệu phù hợp
            ps.setBoolean(3, nv.getVaiTro() == 1); // Nếu 1 là Nhân viên, ngược lại là Quản lý (bit = 0)
            ps.setString(4, nv.getSdt());
            ps.setString(5, nv.getNgaysinh());
            ps.setString(6, nv.getEmail());
            ps.setString(7, nv.getTrangthai());
            ps.setString(8, nv.getMaNV());

            row = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return row;
    }
    
//    public List<NhanVien> selectBySQL(String sql, Object... args) {
//        try {
//            List<NhanVien> list = new ArrayList<>();
//            ResultSet rs = JDBCHelper.executeQuery(sql, args);
//            while (rs.next()) {
//                NhanVien nv = new NhanVien();
//                nv.setMaNV(rs.getString("ma_nguoi_dung"));
//                nv.setTenNV(rs.getString("ho_ten"));
//                nv.setMatKhau(rs.getString("mat_khau"));
//                nv.setVaiTro(rs.getInt("vai_tro"));
//                nv.setSdt(rs.getString("so_dien_thoai"));
//                nv.setNgaysinh(rs.getString("ngay_sinh"));
//                nv.setEmail(rs.getString("email"));
//                nv.setTrangthai(rs.getString("trang_thai"));
//                list.add(nv);
//            }
//            return list;
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//    } 
//
//    public NhanVien getByID(UUID idNV) {
//        String GET_BY_ID = "SELECT [id] FROM [nguoi_dung] WHERE [id] = ?";
//        
//        List<NhanVien> lst = selectBySQL(GET_BY_ID, idNV);
//        if(lst.isEmpty()) {
//            return null;
//        }
//        return lst.get(0);
//    }
}
