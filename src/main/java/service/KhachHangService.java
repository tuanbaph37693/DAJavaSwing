/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.SQLException;
import java.util.ArrayList;
import model.KhachHang;
import reponsitory.KhachHangReponsitory;

/**
 *
 * @author Asus
 */
public class KhachHangService {

    private final KhachHangReponsitory khr = new KhachHangReponsitory();

    public ArrayList<KhachHang> getAllKhachHang() throws SQLException, Exception {
        return khr.getAllKhachHang();
    }

    public boolean updateKhachHang(KhachHang kh) throws Exception {
        return khr.updateKhachHang(kh);
    }

    public boolean deleteKhachHang(Object id) throws Exception {
        return khr.deleteKhachHang(id);
    }

    public KhachHang timKhachHang(String soDienThoai) throws Exception {
        return khr.timKhachHang(soDienThoai);
    }
}
