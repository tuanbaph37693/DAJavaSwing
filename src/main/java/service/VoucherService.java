/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.SQLException;
import java.util.ArrayList;
import model.PhieuGiamGia;
import empty.VoucherReponsitory;

/**
 *
 * @author Asus
 */
public class VoucherService {

    private final VoucherReponsitory vcr = new VoucherReponsitory();

    public ArrayList<PhieuGiamGia> getAllKhuyenMai() throws SQLException, Exception {
        return vcr.getAllKhuyenMai();
    }

    public boolean addKhuyenMai(PhieuGiamGia k) throws Exception {
        return vcr.addKhuyenMai(k);
    }

    public boolean updateKhuyenMai(PhieuGiamGia k) throws Exception {
        return vcr.updateKhuyenMai(k);
    }

    public boolean deleteKhuyenMai(String maKhuyenMai) throws Exception {
        return vcr.deleteKhuyenMai(maKhuyenMai);
    }
}
