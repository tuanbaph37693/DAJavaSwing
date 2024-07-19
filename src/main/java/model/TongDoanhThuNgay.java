/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Asus
 */
public class TongDoanhThuNgay {

    private int ngay;
    private int soHoaDon;
    private Float giaGoc;
    private Float giaGiam;
    private Float tongDoanhThu;

    public TongDoanhThuNgay() {
    }

    public TongDoanhThuNgay(int ngay, int soHoaDon, Float giaGoc, Float giaGiam, Float tongDoanhThu) {
        this.ngay = ngay;
        this.soHoaDon = soHoaDon;
        this.giaGoc = giaGoc;
        this.giaGiam = giaGiam;
        this.tongDoanhThu = tongDoanhThu;
    }

    public int getSoHoaDon() {
        return soHoaDon;
    }

    public void setSoHoaDon(int soHoaDon) {
        this.soHoaDon = soHoaDon;
    }

    public int getNgay() {
        return ngay;
    }

    public void setNgay(int ngay) {
        this.ngay = ngay;
    }

    public Float getGiaGoc() {
        return giaGoc;
    }

    public void setGiaGoc(Float giaGoc) {
        this.giaGoc = giaGoc;
    }

    public Float getGiaGiam() {
        return giaGiam;
    }

    public void setGiaGiam(Float giaGiam) {
        this.giaGiam = giaGiam;
    }

    public Float getTongDoanhThu() {
        return tongDoanhThu;
    }

    public void setTongDoanhThu(Float tongDoanhThu) {
        this.tongDoanhThu = tongDoanhThu;
    }

}
