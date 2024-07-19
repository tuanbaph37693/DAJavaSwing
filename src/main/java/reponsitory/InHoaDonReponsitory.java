/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.InHoaDon;

/**
 *
 * @author Asus
 */
public class InHoaDonReponsitory {

    public ArrayList<InHoaDon> getAllHoaDonChiTiet(Object idHoaDon) throws Exception {
        ArrayList<InHoaDon> lstPHD = new ArrayList<>();
        String sql = "SELECT hd.ngay_sua,hdct.ngay_tao, kh.ten as tenkh, nd.ho_ten as tennv, sp.ten AS tensp, kt.ten AS tenkt, hdct.so_luong as soluongmua, hdct.gia_tien as giasp, hd.tong_tien "
                + "FROM hoa_don_chi_tiet hdct "
                + "INNER JOIN chi_tiet_san_pham spct ON hdct.id_ctsp = spct.id "
                + "INNER JOIN san_pham sp ON spct.id_san_pham = sp.id "
                + "INNER JOIN kich_thuoc kt ON spct.id_kich_thuoc = kt.id "
                + "INNER JOIN hoa_don hd ON hdct.id_hoa_don = hd.id "
                + "INNER JOIN khach_hang kh ON hd.id_khach_hang = kh.id "
                + "INNER JOIN nguoi_dung nd ON hd.id_nguoi_tao = nd.id "
                + "WHERE hdct.id_hoa_don = ? ";

        try (Connection conn = DBConnect.openConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setObject(1, idHoaDon);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    InHoaDon ihd = new InHoaDon();
                    ihd.setTenkh(rs.getString("tenkh"));
                    ihd.setTennv(rs.getString("tennv"));
                    ihd.setTensp(rs.getString("tensp"));
                    ihd.setTenkt(rs.getString("tenkt"));
                    ihd.setSoluongmua(rs.getInt("soluongmua"));
                    ihd.setGiasp(rs.getFloat("giasp"));
                    ihd.setTong_tien(rs.getFloat("tong_tien"));
                    ihd.setNgaythanhtoan(rs.getDate("ngay_sua"));
                    ihd.setNgayTao(rs.getDate("ngay_tao"));
                    lstPHD.add(ihd);
                }
            }
        }

        return lstPHD;
    }
}
