/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class TongDoanhThu {

    private int tongDonHang;
    private Float giaGoc;
    private Float giaGiam;
    private Float tongDoanhThu;

    public TongDoanhThu() {
    }

    public TongDoanhThu(int tongDonHang, Float giaGoc, Float giaGiam, Float tongDoanhThu) {
        this.tongDonHang = tongDonHang;
        this.giaGoc = giaGoc;
        this.giaGiam = giaGiam;
        this.tongDoanhThu = tongDoanhThu;
    }

    public int getTongDonHang() {
        return tongDonHang;
    }

    public void setTongDonHang(int tongDonHang) {
        this.tongDonHang = tongDonHang;
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
