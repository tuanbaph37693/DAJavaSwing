/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponsitory;

import java.util.ArrayList;
import java.util.List;
import model.ThuocTinh;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.JDBCHelper;

/**
 *
 * @author Admin
 */
public class ThuocTinhRepository {

    String GET_ALL = """
                     {CALL dbo.SP_GET_ONE_ATTRIBUTE_TABLE(?)}
                    """;
    String GET_BY_ID = """
                       {CALL dbo.SP_GET_ONE_ATTRIBUTE_BY_ID_IN_TABLE(?,?)}
                       """;
    String GET_BY_NAME = """
                         {CALL dbo.SP_GET_ATTRIBUTE_BY_NAME_IN_TABLE(?,?)}
                         """;
    String INSERT = """
                    {CALL dbo.SP_INSERT_IN_TABLE(?,?,?)}
                    """;
    String UPDATE = """
                    {CALL dbo.SP_UPDATE_IN_TABLE(?,?,?,?)}
                    """;
    String DELETE = """
                    {CALL dbo.SP_DELETE_IN_TABLE(?,?)}
                    """;
    public List<ThuocTinh> selectBySQL(String sql, Object... args) {
        try {
            List<ThuocTinh> list = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                ThuocTinh tt = new ThuocTinh();
                tt.setId(rs.getString("id"));
                tt.setTen(rs.getString("ten"));
                tt.setNgayTao(rs.getDate("ngay_tao"));
                tt.setNgaySua(rs.getDate("ngay_sua"));
                tt.setTrangThai(rs.getString("trang_thai"));
                tt.setDeleted(rs.getBoolean("deleted"));
                list.add(tt);
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<ThuocTinh> getAll(String loaiThuocTinh) {
        return selectBySQL(GET_ALL, loaiThuocTinh);
    }

    public ThuocTinh getByID(String loaiThuocTinh, String id) {
        List<ThuocTinh> list = selectBySQL(GET_BY_ID, loaiThuocTinh, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public List<ThuocTinh> getByName(String loaiThuocTinh, String name){
        return selectBySQL(GET_BY_NAME, loaiThuocTinh, name);
    }

    public void add(String loaiThuocTinh, ThuocTinh o) {
        JDBCHelper.executeUpdate(INSERT, loaiThuocTinh, o.getTen(), o.getTrangThai());
    }

    public void update(String loaiThuocTinh, ThuocTinh o, String id) {
        JDBCHelper.executeUpdate(UPDATE, loaiThuocTinh, id, o.getTen(), o.getTrangThai());
    }

    public void remove(String loaiThuocTinh, String id) {
        JDBCHelper.executeUpdate(DELETE, loaiThuocTinh, id);
    }

    public static void main(String[] args) {
        ThuocTinhRepository repository = new ThuocTinhRepository();
//        for (ThuocTinh o : repository.getAll("mau_sac")) {
//            System.out.println(o);
//        }
//        System.out.println(repository.getByID("mau_sac", "A926FD87-C60D-4AE3-9328-7D3BF42364CD"));
//        for (ThuocTinh thuocTinh : repository.getByName("mau_sac", "Đ")) {
//            System.out.println(thuocTinh);
//        }
//        repository.remove("mau_sac" ,"406C1008-5F6C-4C85-89FB-57F0736F1BBC");
//        for (ThuocTinh o : repository.getAll("mau_sac")) {
//            System.out.println(o);
//        }
    }

}   
