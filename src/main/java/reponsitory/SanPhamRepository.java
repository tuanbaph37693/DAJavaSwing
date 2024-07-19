/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponsitory;

import java.util.ArrayList;
import java.util.List;
import model.SanPham;
import util.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DateHelper;

/**
 *
 * @author Admin
 */
public class SanPhamRepository implements MethodRepository<SanPham> {

    String GET_ALL = """
                    select * from san_pham where deleted = 0  order by ngay_sua desc OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
                    """;
    String GET_ALL_DATA = """
                    select * from san_pham where deleted = 0 order by ngay_sua desc
                    """;
    String GET_ALL_DATA_REMOVE = """
                             select * from san_pham where deleted = 1 order by ngay_sua desc
                             """;

    String GET_BY_ID = """
                     select * from san_pham where id = ? order by ngay_sua desc
                     """;

    String GET_BY_PROPERTIES = """
                        select * from san_pham where (ten like ? or ma_san_pham like ? or trang_thai like ?) and deleted = 0  order by ngay_sua desc OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
                        """;

    String GET_BY_PROPERTIES_UNREMOVE = """
                        select * from san_pham where (ten like ? or ma_san_pham like ? or trang_thai like ?) and deleted = 1  order by ngay_sua desc
                        """;
    String INSERT = """
                    INSERT INTO [dbo].[san_pham]
                               ([id]
                               ,[ma_san_pham]
                               ,[ten]
                               ,[trang_thai])
                         VALUES
                               (newid(),?,?,?)
                    """;
    String UPDATE = """
                    UPDATE [dbo].[san_pham]
                       SET [ma_san_pham] = ?
                          ,[ten] = ?
                          ,[ngay_sua] = ?
                          ,[trang_thai] = ?
                     WHERE id = ?
                    """;
    String DELETE = """
                    update san_pham set deleted = 1 where id = ?
                    """;

    String UN_DELETE = """
                       update san_pham set deleted = 0 where id = ?
                       """;

    @Override
    public List<SanPham> selectBySQL(String sql, Object... args) {
        try {
            List<SanPham> list = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getString("id"));
                sp.setMa(rs.getString("ma_san_pham"));
                sp.setTen(rs.getString("ten"));
                sp.setNgayTao(rs.getDate("ngay_tao"));
                sp.setNgaySua(rs.getDate("ngay_sua"));
                sp.setTrangThai(rs.getString("trang_thai"));
                sp.setDeleted(rs.getBoolean("deleted"));
                list.add(sp);
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    int resultPage(int page, int limit) {
        int inputPage = (page - 1) * limit;
        return inputPage;
    }

    @Override
    public List<SanPham> getAll(int page, int limit) {
        return selectBySQL(GET_ALL, resultPage(page, limit), limit);
    }

    public List<SanPham> getAllData() {
        return selectBySQL(GET_ALL_DATA);
    }

    @Override
    public SanPham getByID(String id) {
        List<SanPham> list = selectBySQL(GET_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public void add(SanPham o) {
        JDBCHelper.executeUpdate(INSERT, o.getMa(), o.getTen(), o.getTrangThai());
    }

    @Override
    public void update(SanPham o, String id) {
        JDBCHelper.executeUpdate(UPDATE, o.getMa(), o.getTen(), DateHelper.now(), o.getTrangThai(), id);
    }

    @Override
    public void remove(String id) {
        JDBCHelper.executeUpdate(DELETE, id);
    }

    public List<SanPham> getByProperties(String value, int page, int limit) {
        String properties = "%" + value + "%";
        return selectBySQL(GET_BY_PROPERTIES, properties, properties, properties, resultPage(page, limit), limit);
    }

    public List<SanPham> getByPropertiesUnremove(String value) {
        String name = "%" + value + "%";
        return selectBySQL(GET_BY_PROPERTIES_UNREMOVE, name, name, name);
    }

    @Override
    public void unremove(String id) {
        JDBCHelper.executeUpdate(UN_DELETE, id);
    }

    @Override
    public List<SanPham> getListRemove() {
        return selectBySQL(GET_ALL_DATA_REMOVE);
    }
}
