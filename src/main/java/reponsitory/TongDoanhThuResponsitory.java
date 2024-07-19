/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponsitory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.TongDoanhThu;

/**
 *
 * @author Asus
 */
public class TongDoanhThuResponsitory {

    public ArrayList<TongDoanhThu> getAllDoanhThu() throws Exception {
        ArrayList<TongDoanhThu> lstDT = new ArrayList<>();
        String sql = "SELECT count(*) as tong_don_hang, SUM(tong_tien) AS tong_doanh_thu"
                + " FROM hoa_don"
                + " WHERE trang_thai = N'Đã thanh toán'";
        Connection conn = DBConnect.openConnection();
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                TongDoanhThu tdt = new TongDoanhThu();
                tdt.setTongDonHang(rs.getInt("tong_don_hang"));
                tdt.setTongDoanhThu(rs.getFloat("tong_doanh_thu"));
                lstDT.add(tdt);
            }
        }
        return lstDT;
    }

}
