/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponsitory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.SanPhamMuaNhieu;

/**
 *
 * @author Asus
 */
public class SanPhamMuaNhieuRS {

    public ArrayList<SanPhamMuaNhieu> getAllSanPham() throws Exception {
        ArrayList<SanPhamMuaNhieu> lstSPMN = new ArrayList<>();
        String sql = " SELECT sp.ma_san_pham, sp.ten, ms.ten as mau_sac, nsx.ten as nha_san_xuat, COUNT(*) as so_lan_mua "
                + " FROM hoa_don_chi_tiet hdct "
                + " JOIN chi_tiet_san_pham ctsp on hdct.id_ctsp = ctsp.id "
                + " JOIN san_pham sp ON ctsp.id_san_pham = sp.id "
                + " JOIN hoa_don hd on hdct.id_hoa_don = hd.id "
                + " join mau_sac ms on ms.id = ctsp.id_mau_sac "
                + " join nha_san_xuat nsx on nsx.id = ctsp.id_nha_san_xuat "
                + " WHERE hd.trang_thai = N'Đã thanh toán' "
                + " group by sp.id, sp.ma_san_pham, sp.ten, ms.ten, nsx.ten "
                + " ORDER BY so_lan_mua DESC";
        Connection conn = DBConnect.openConnection();
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                SanPhamMuaNhieu spmn = new SanPhamMuaNhieu();
                spmn.setMasp(rs.getString("ma_san_pham"));
                spmn.setTensp(rs.getString("ten"));
                spmn.setMausac(rs.getString("mau_sac"));
                spmn.setNhasanxuat(rs.getString("nha_san_xuat"));
                spmn.setSolanmua(rs.getString("so_lan_mua"));
                lstSPMN.add(spmn);
            }
        }
        return lstSPMN;
    }
}
