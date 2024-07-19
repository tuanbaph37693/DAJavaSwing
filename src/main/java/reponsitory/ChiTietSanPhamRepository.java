/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import model.ChiTietSanPham;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DateHelper;
import util.JDBCHelper;

/**
 *
 * @author Admin
 */
public class ChiTietSanPhamRepository implements MethodRepository<ChiTietSanPham> {
    
    String GET_ALL = """
                     select * from chi_tiet_san_pham where deleted = 0  order by ngay_sua desc OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
                     """;
    String GET_ALL_DATA = """
                     select * from chi_tiet_san_pham where deleted = 0  order by ngay_sua desc
                     """;
    String GET_ALL_DATA_REMOVE = """
                     select * from chi_tiet_san_pham where deleted = 1  order by ngay_sua desc
                     """;
    String GET_BY_ID = """
                       select * from chi_tiet_san_pham where id = ?  order by ngay_sua desc
                       """;
    String SEARCH_BY_ID = """
                            select * from chi_tiet_san_pham where id like ? and deleted = 0  order by ngay_sua desc
                            """;
    String INSERT = """
                    INSERT INTO [dbo].[chi_tiet_san_pham]
                               ([id]
                               ,[id_san_pham]
                               ,[id_thuong_hieu]
                               ,[id_the_loai]
                               ,[id_thiet_ke]
                               ,[id_phong_cach]
                               ,[id_co_ao]
                               ,[id_mau_sac]
                               ,[id_chat_lieu]
                               ,[id_kich_thuoc]
                               ,[id_nha_san_xuat]
                               ,[hinh_anh]
                               ,[so_luong_ton]
                               ,[gia_ban]
                               ,[mo_ta]
                               ,[trang_thai],[ma_qr])
                         VALUES
                               (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
                    """;
    String UPDATE = """
                    UPDATE [dbo].[chi_tiet_san_pham]
                       SET [id_san_pham] = ?
                          ,[id_thuong_hieu] = ?
                          ,[id_the_loai] = ?
                          ,[id_thiet_ke] = ?
                          ,[id_phong_cach] = ?
                          ,[id_co_ao] = ?
                          ,[id_mau_sac] = ?
                          ,[id_chat_lieu] = ?
                          ,[id_kich_thuoc] = ?
                          ,[id_nha_san_xuat] = ?
                          ,[hinh_anh] = ?
                          ,[so_luong_ton] = ?
                          ,[gia_ban] = ?
                          ,[ngay_sua] = ?
                          ,[mo_ta] = ?
                          ,[trang_thai] = ?
                     WHERE id = ?
                    """;
    String DELETE = """
                    update chi_tiet_san_pham set deleted = 1 where id = ?
                    """;
    String UN_DELETE = """
                    update chi_tiet_san_pham set deleted = 0 where id = ?
                    """;
    String SEARCH_BY_NAME = """
                    select * from chi_tiet_san_pham ctsp join
                    san_pham sp on ctsp.id_san_pham = sp.id where sp.ten like ? and ctsp.deleted = 0 order by ctsp.ngay_sua desc OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
                    """;
    String SEARCH_BY_NAME_UNREMOVE = """
                    select * from chi_tiet_san_pham ctsp join
                    san_pham sp on ctsp.id_san_pham = sp.id where sp.ten like ? and ctsp.deleted = 1 order by ctsp.ngay_sua desc OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
                    """;
    String SEARCH_CTSP_BY_ID_SP = """
                               select * from chi_tiet_san_pham where id_san_pham like ?  order by ngay_sua desc OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
                               """;
    public List<ChiTietSanPham> getCTSPByIDSP(String id, int page, int limit){
        String value = "%"+id+"%";
        return selectBySQL(SEARCH_CTSP_BY_ID_SP, value, resultPage(page, limit), limit);
    }
    @Override
    public List<ChiTietSanPham> selectBySQL(String sql, Object... args) {
        try {
            List<ChiTietSanPham> list = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham();
                ctsp.setId(rs.getString("id"));
                ctsp.setIdSanPham(rs.getString("id_san_pham"));
                ctsp.setIdThuongHieu(rs.getString("id_thuong_hieu"));
                ctsp.setIdTheLoai(rs.getString("id_the_loai"));
                ctsp.setIdThietKe(rs.getString("id_thiet_ke"));
                ctsp.setIdPhongCach(rs.getString("id_phong_cach"));
                ctsp.setIdCoAo(rs.getString("id_co_ao"));
                ctsp.setIdMauSac(rs.getString("id_mau_sac"));
                ctsp.setIdChatLieu(rs.getString("id_chat_lieu"));
                ctsp.setIdKichThuoc(rs.getString("id_kich_thuoc"));
                ctsp.setIdNSX(rs.getString("id_nha_san_xuat"));
                ctsp.setHinhAnh(rs.getString("hinh_anh"));
                ctsp.setGiaBan(rs.getBigDecimal("gia_ban"));
                ctsp.setSoLuong(rs.getInt("so_luong_ton"));
                ctsp.setNgayNhap(rs.getDate("ngay_nhap_kho"));
                ctsp.setNgaySua(rs.getDate("ngay_sua"));
                ctsp.setMoTa(rs.getString("mo_ta"));
                ctsp.setTrangThai(rs.getString("trang_thai"));
                ctsp.setDeleted(rs.getBoolean("deleted"));
                ctsp.setMaQr(rs.getString("ma_qr"));
                list.add(ctsp);
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    int resultPage(int page, int limit){
        int inputPage  = (page - 1) * limit;
        return inputPage;
    }
    
    @Override
    public List<ChiTietSanPham> getAll(int page, int limit) {
        return selectBySQL(GET_ALL, resultPage(page, limit),limit);
    }
    
    public List<ChiTietSanPham> getAllData() {
        return selectBySQL(GET_ALL_DATA);
    }
    
    @Override
    public ChiTietSanPham getByID(String id) {
        List<ChiTietSanPham> list = selectBySQL(GET_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public ChiTietSanPham getByIDhd(UUID id) {
        List<ChiTietSanPham> list = selectBySQL(GET_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    @Override
    public void add(ChiTietSanPham o) {
        JDBCHelper.executeUpdate(INSERT, o.getId(),
                o.getIdSanPham(), o.getIdThuongHieu(), o.getIdTheLoai(),
                o.getIdThietKe(), o.getIdPhongCach(), o.getIdCoAo(),
                o.getIdMauSac(), o.getIdChatLieu(), o.getIdKichThuoc(),
                o.getIdNSX(), o.getHinhAnh(), o.getSoLuong(),
                o.getGiaBan(), o.getMoTa(), o.getTrangThai(), o.getMaQr());
    }
    
    @Override
    public void update(ChiTietSanPham o, String id) {
        JDBCHelper.executeUpdate(UPDATE, 
                o.getIdSanPham(), o.getIdThuongHieu(), o.getIdTheLoai(),
                o.getIdThietKe(), o.getIdPhongCach(), o.getIdCoAo(),
                o.getIdMauSac(), o.getIdChatLieu(), o.getIdKichThuoc(),
                o.getIdNSX(), o.getHinhAnh(), o.getSoLuong(),
                o.getGiaBan(), DateHelper.now(),o.getMoTa(), o.getTrangThai(), id);
    }
    
    @Override
    public void remove(String id) {
        JDBCHelper.executeUpdate(DELETE, id);
    }
    
    public List<ChiTietSanPham> searchByTen(String value, int page, int limit){
        String name = "%"+ value + "%";
        return selectBySQL(SEARCH_BY_NAME, name, resultPage(page, limit), limit);
    }
    
    public List<ChiTietSanPham> searchByTenUnRemove(String value, int page, int limit){
        String name = "%"+ value + "%";
        return selectBySQL(SEARCH_BY_NAME_UNREMOVE, name, resultPage(page, limit), limit);
    }
    
    // Hy hữu lắm mới có mã giống nhau
    public List<ChiTietSanPham> searchByMaCTSP(String value, int page, int limit){
        String name = "%"+value+"%";
        return selectBySQL(SEARCH_BY_ID, name, resultPage(page, limit), limit);
    }
    
    public static void main(String[] args) {
        ChiTietSanPhamRepository repository = new ChiTietSanPhamRepository();
//        for (ChiTietSanPham o : repository.getAll()) {
//            System.out.println(o);
//        }
//        for (ChiTietSanPham o : repository.searchByTen("Áo phông s")) {
//            System.out.println(o);
//        }
    }
    
//    public static void main(String[] args) {
//        ChiTietSanPhamRepository repository = new ChiTietSanPhamRepository();
//        repository.remove("a8eb7e42-61e3-4a1e-9357-fd6abaed7d1f");
//        for (ChiTietSanPham o : repository.searchByTen("Áo phông ss")) {
//            System.out.println(o);
//        }
////        for (ChiTietSanPham arg : repository.getAll()) {
////            System.out.println(arg.toString());
////        }
////        System.out.println(repository.getByID("FEB6B3B9-F5A7-4486-BEE3-1435536282D2"));
////        repository.add(new ChiTietSanPham("D7F63232-4A78-4C6C-81A0-B05D4BC6F44E", "490CA78B-053E-4D18-883C-4A0ED0FA1FD5", "7A6C2DA2-A4D5-454B-BB6E-473D4533D446", "04C70CCC-68CC-4304-B7B5-2C2606CDEBF8", "AD0E3863-CF41-43BE-943E-3ED09BB01664", "400C0924-7BEA-41F9-BE00-166DFCEBDD1D", "972B5C61-8FF0-4B29-86E0-57E8E63EF590", "06C132B3-4B82-4458-BBD2-2C3A562679FF", "44CC0AFB-77C6-41A5-BFD8-F579B5BC955E", "E150B015-1B6B-4E03-AA68-22E0140A0625", "anh4.png", 110, 11111110, "Áo độc quyền", "Mới"));
////        repository.update(new ChiTietSanPham("C5B45149-5F1D-4CB3-9479-B367955E940A", "490CA78B-053E-4D18-883C-4A0ED0FA1FD5", "7A6C2DA2-A4D5-454B-BB6E-473D4533D446", "04C70CCC-68CC-4304-B7B5-2C2606CDEBF8", "AD0E3863-CF41-43BE-943E-3ED09BB01664", "400C0924-7BEA-41F9-BE00-166DFCEBDD1D", "972B5C61-8FF0-4B29-86E0-57E8E63EF590", "06C132B3-4B82-4458-BBD2-2C3A562679FF", "44CC0AFB-77C6-41A5-BFD8-F579B5BC955E", "E150B015-1B6B-4E03-AA68-22E0140A0625", "anh4.png", 110, 11111110, "Áo độc quyền", "Mới"), "FA696CDE-B5B8-477B-8D0F-7CD8E3FEE2F9");
////        for (ChiTietSanPham arg : repository.getAll()) {
////            System.out.println(arg.toString());
////        }
//    }

    @Override
    public void unremove(String id) {
        JDBCHelper.executeUpdate(UN_DELETE, id);
    }

    @Override
    public List<ChiTietSanPham> getListRemove() {
        return selectBySQL(GET_ALL_DATA_REMOVE);
    }
    
    public int UpdateSL(UUID idCTSP, int sl) {
        String sql = "Update chi_tiet_san_pham set so_luong_ton = ? where id = ?";

        try {
            Connection con = DBConnect.openConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, sl);
            ps.setString(2, idCTSP.toString());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
    
    String UPDATE_MA_QR = """
                          update chi_tiet_san_pham
                          set ma_qr = ? where id = ?
                          """;
    public void updateQrCode(String qr, String id){
        JDBCHelper.executeUpdate(UPDATE_MA_QR, qr, id);
    }
}
