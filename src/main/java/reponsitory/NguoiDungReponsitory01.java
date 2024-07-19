/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;
import model.NguoiDung;

/**
 *
 * @author Asus
 */
public class NguoiDungReponsitory01 {

    public NguoiDung checkLogin(String manguoidung, String matkhau) throws Exception {

        String sql = "SELECT id,ho_ten, ma_nguoi_dung, mat_khau, vai_tro FROM nguoi_dung WHERE ma_nguoi_dung = ? AND mat_khau = ?";

        try (Connection conn = DBConnect.openConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
//            st.setObject(1, id);
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
    
}
