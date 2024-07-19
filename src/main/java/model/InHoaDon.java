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
public class InHoaDon {

    private String tennv, tenkh, tenkt, tensp;
    private int soluongmua;
    private float tong_tien, giasp;
    private Date ngaythanhtoan, ngayTao;

    public InHoaDon() {
    }

    public InHoaDon(String tennv, String tenkh, String tenkt, String tensp, int soluongmua, float tong_tien, float giasp, Date ngaythanhtoan, Date ngayTao) {
        this.tennv = tennv;
        this.tenkh = tenkh;
        this.tenkt = tenkt;
        this.tensp = tensp;
        this.soluongmua = soluongmua;
        this.tong_tien = tong_tien;
        this.giasp = giasp;
        this.ngaythanhtoan = ngaythanhtoan;
        this.ngayTao = ngayTao;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getTenkt() {
        return tenkt;
    }

    public void setTenkt(String tenkt) {
        this.tenkt = tenkt;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getSoluongmua() {
        return soluongmua;
    }

    public void setSoluongmua(int soluongmua) {
        this.soluongmua = soluongmua;
    }

    public float getTong_tien() {
        return tong_tien;
    }

    public void setTong_tien(float tong_tien) {
        this.tong_tien = tong_tien;
    }

    public float getGiasp() {
        return giasp;
    }

    public void setGiasp(float giasp) {
        this.giasp = giasp;
    }

    public Date getNgaythanhtoan() {
        return ngaythanhtoan;
    }

    public void setNgaythanhtoan(Date ngaythanhtoan) {
        this.ngaythanhtoan = ngaythanhtoan;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    

}
