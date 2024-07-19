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
import model.HoaDon;
import model.HoaDon1;
import model.HoaDonChiTiet;
import model.KhachHang;
import util.JDBCHelper;

/**
 *
 * @author Asus
 */
public class HoaDonReponsitory {

    public ArrayList<HoaDon> getAllHoaDon() throws SQLException, Exception {
        ArrayList<HoaDon> lstHoaDon = new ArrayList<>();
        String sql = " SELECT hd.id,nd.ho_ten,kh.ten,hd.gia_tien,hd.giam_gia,hd.ngay_tao,hd.ngay_sua,hd.trang_thai, hd.deleted FROM hoa_don hd "
                + " INNER JOIN nguoi_dung nd on nd.id = hd.id_nguoi_tao"
                + " INNER JOIN khach_hang kh on kh.id = hd.id_khach_hang ";
        Connection con = DBConnect.openConnection();
        try ( Statement st = con.createStatement();  ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                String idHd = (String) rs.getObject(1);
                HoaDon hd = new HoaDon();
                hd.setIdHD(UUID.fromString(idHd));
                hd.setTenNhanVien(rs.getString(2));
                hd.setTenKhachHang(rs.getString(3));
                hd.setGiamGia(rs.getFloat(4));
                hd.setGiaTien(rs.getFloat(5));
                hd.setNgayTao(rs.getDate(6));
                hd.setNgaySua(rs.getDate(7));
                hd.setTrangThai(rs.getString(8));
                hd.setDelete(rs.getInt("deleted"));
                lstHoaDon.add(hd);
            }
        }
        return lstHoaDon;
    }

    public ArrayList<HoaDon1> getAllHD() {
        String sql = "select hd.id, nd.ho_ten, kh.ten, hd.ngay_tao, hd.trang_thai, hd.deleted from hoa_don hd\n"
                + "	inner join khach_hang kh on kh.id = hd.id_khach_hang\n"
                + "	inner join nguoi_dung nd on nd.id = hd.id_nguoi_tao";
        try {
            Connection con = DBConnect.openConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ArrayList<HoaDon1> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon1 hd = new HoaDon1();

                hd.setIdHD(rs.getString(1));
                hd.setIdNV(rs.getString(2));
                hd.setIdKH(rs.getString(3));
                hd.setNgayTao(rs.getDate(4));
                hd.setTrangThai(rs.getString(5));
                hd.setDeleted(rs.getInt("deleted"));

                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean themHoaDon(KhachHang kh, HoaDon hd) throws Exception {
        UUID IDkh = UUID.randomUUID();
        String sql2 = " INSERT INTO khach_hang (id, ten, so_dien_thoai, dia_chi, ngay_tao, ngay_sua, nguoi_tao, trang_thai, deleted) "
                + " VALUES (?, ?, ?, N'DiaChi', GETDATE(), null, ?, 0, 0)";

        String sql = "INSERT INTO [dbo].[hoa_don] ( "
                + "    [id],"
                + "    [id_nguoi_tao],"
                + "    [id_khach_hang],"
                + "    [id_voucher],"
                + "    [ten_nguoi_nhan],"
                + "    [so_dien_thoai],"
                + "    [dia_chi],"
                + "    [gia_tien],"
                + "    [giam_gia],"
                + "    [tong_tien],"
                + "    [ngay_xac_nhan],"
                + "    [ngay_van_chuyen],"
                + "    [ngay_nhan],"
                + "    [ghi_chu],"
                + "    [tien_van_chuyen],"
                + "    [ngay_tao],"
                + "    [ngay_sua],"
                + "    [trang_thai],"
                + "    [deleted]"
                + ") VALUES ("
                + "    ?,"
                + "    ?,"
                + "    ?,"
                + "    null,"
                + "    null, "
                + "    null,"
                + "    null,"
                + "    0,"
                + "    0,"
                + "    0,"
                + "    GETDATE(),"
                + "    null, "
                + "    null,"
                + "    N'Chưa thanh toán',"
                + "    0,"
                + "	GETDATE()," // Thêm giá trị cho cột ngay_tao
                + "	GETDATE()," // Thêm giá trị cho cột ngay_sua
                + "	N'Chưa thanh toán',"
                + "	1 )";

        try ( Connection conn = DBConnect.openConnection();  PreparedStatement st2 = conn.prepareStatement(sql2);  PreparedStatement st = conn.prepareStatement(sql);) {
            //Khách Hàng 
            st2.setObject(1, IDkh);
            st2.setString(2, kh.getTenKhachHang());
            st2.setString(3, kh.getSoDienthoai());
            st2.setString(4, kh.getNguoiTao());
            //Hóa Đơn
            st.setObject(1, UUID.randomUUID());
            st.setObject(2, hd.getIdNguoiDung());
            st.setObject(3, IDkh);
            boolean stKH = st2.executeUpdate() > 0;
            boolean stHD = st.executeUpdate() > 0;
            return stKH && stHD;
        }
    }

    public boolean taoHoaDon(KhachHang kh, HoaDon hd) throws Exception {
        String sql = "INSERT INTO [dbo].[hoa_don] ( "
                + "    [id],"
                + "    [id_nguoi_tao],"
                + "    [id_khach_hang],"
                + "    [id_voucher],"
                + "    [ten_nguoi_nhan],"
                + "    [so_dien_thoai],"
                + "    [dia_chi],"
                + "    [gia_tien],"
                + "    [giam_gia],"
                + "    [tong_tien],"
                + "    [ngay_xac_nhan],"
                + "    [ngay_van_chuyen],"
                + "    [ngay_nhan],"
                + "    [ghi_chu],"
                + "    [tien_van_chuyen],"
                + "    [ngay_tao],"
                + "    [ngay_sua],"
                + "    [trang_thai],"
                + "    [deleted]"
                + ") VALUES ("
                + "    ?,"
                + "    ?,"
                + "    ?,"
                + "    null,"
                + "    null, "
                + "    null,"
                + "    null,"
                + "    0,"
                + "    0,"
                + "    0,"
                + "    GETDATE(),"
                + "    null, "
                + "    null,"
                + "    N'Chưa thanh toán',"
                + "    0,"
                + "	GETDATE()," // Thêm giá trị cho cột ngay_tao
                + "	GETDATE()," // Thêm giá trị cho cột ngay_sua
                + "	N'Chưa thanh toán',"
                + "	1 )";
        try ( Connection con = DBConnect.openConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, UUID.randomUUID());
            ps.setObject(2, hd.getIdNguoiDung());
            ps.setObject(3, kh.getIdKH());

            boolean tao = ps.executeUpdate() > 0;
            return tao;
        }
    }

    public void Update(String idHD) {
        String sql = "Update hoa_don set gia_tien = 0, tong_tien = 0, [giam_gia] = 0, ngay_sua = getdate(), trang_thai = N'Đã hủy', deleted = 0 where [id] = ?";

        try {
            Connection con = DBConnect.openConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, idHD);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ThanhToan(HoaDon1 hd) {
        String sql = "Update hoa_don set gia_tien = ?, tong_tien = ?, giam_gia = ?, ngay_sua = getdate(), trang_thai = N'Đã thanh toán', deleted = 0 where [id] = ?";

        try {
            Connection con = DBConnect.openConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(4, hd.getIdHD());
            ps.setBigDecimal(1, hd.getGia_tien());
            ps.setBigDecimal(2, hd.getTong_tien());
            ps.setBigDecimal(3, hd.getGiamGia());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteHD(String maHD) {
        String sql = "DELETE FROM hoa_don WHERE [id] = ?";

        try {
            Connection con = DBConnect.openConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, maHD);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

    
