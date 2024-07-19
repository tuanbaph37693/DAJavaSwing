/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.util.*;

/**
 *
 * @author Lenovo IdeapadGaming
 */
public class HoaDon1 {
    private String idHD;
    private String idNV;
    private String idKH;
    private BigDecimal GiamGia;
    private  BigDecimal gia_tien;
    private BigDecimal tong_tien;
    private Date ngayTao;
    private Date ngaySua;
    private String trangThai;
    private int deleted;
    

    public HoaDon1() {
    }

    public HoaDon1(String idHD, String idNV, String idKH, BigDecimal GiamGia, BigDecimal gia_tien, BigDecimal tong_tien, Date ngayTao, Date ngaySua, String trangThai, int deleted) {
        this.idHD = idHD;
        this.idNV = idNV;
        this.idKH = idKH;
        this.GiamGia = GiamGia;
        this.gia_tien = gia_tien;
        this.tong_tien = tong_tien;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
        this.deleted = deleted;
    }

    public String getIdHD() {
        return idHD;
    }

    public void setIdHD(String idHD) {
        this.idHD = idHD;
    }

    public String getIdNV() {
        return idNV;
    }

    public void setIdNV(String idNV) {
        this.idNV = idNV;
    }

    public String getIdKH() {
        return idKH;
    }

    public void setIdKH(String idKH) {
        this.idKH = idKH;
    }

    public BigDecimal getGiamGia() {
        return GiamGia;
    }

    public void setGiamGia(BigDecimal GiamGia) {
        this.GiamGia = GiamGia;
    }

    public BigDecimal getGia_tien() {
        return gia_tien;
    }

    public void setGia_tien(BigDecimal gia_tien) {
        this.gia_tien = gia_tien;
    }

    public BigDecimal getTong_tien() {
        return tong_tien;
    }

    public void setTong_tien(BigDecimal tong_tien) {
        this.tong_tien = tong_tien;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    
    
}
