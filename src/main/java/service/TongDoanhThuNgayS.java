/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Date;
import model.TongDoanhThuNgay;
import reponsitory.TongDoanhThuNgayRS;

/**
 *
 * @author Asus
 */
public class TongDoanhThuNgayS {

    private final TongDoanhThuNgayRS tdtnr = new TongDoanhThuNgayRS();

    public TongDoanhThuNgay getDoanhThuNgay(int ngaysua) throws Exception {
        return tdtnr.getDoanhThuNgay(ngaysua);
    }

    public TongDoanhThuNgay getDoanhThuThang(int ngaysua) throws Exception {
        return tdtnr.getDoanhThuThang(ngaysua);
    }

    public TongDoanhThuNgay getDoanhThuNam(int ngaysua) throws Exception {
        return tdtnr.getDoanhThuNam(ngaysua);
    }
}
