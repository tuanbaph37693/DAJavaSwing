/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Lenovo IdeapadGaming
 */
public class HoaDonChiTiet1 {
    private String idHDCT;
    private String idCTSP;
    private String idHD;
    private int sl;
    private BigDecimal thanhTien;
    private Date ngayTao;
    private Date ngaySua;
    private String trangThai;
    private boolean deleted;
    private SanPham sp;
    private ChiTietSanPham ctsp;

    public HoaDonChiTiet1() {
    }

    public HoaDonChiTiet1(String idHDCT, String idCTSP, String idHD, int sl, BigDecimal thanhTien) {
        this.idHDCT = idHDCT;
        this.idCTSP = idCTSP;
        this.idHD = idHD;
        this.sl = sl;
        this.thanhTien = thanhTien;
    }

    public HoaDonChiTiet1(String idHDCT, int sl, BigDecimal thanhTien, SanPham sp, ChiTietSanPham ctsp) {
        this.idHDCT = idHDCT;
        this.sl = sl;
        this.thanhTien = thanhTien;
        this.sp = sp;
        this.ctsp = ctsp;
    }
    
    

    public HoaDonChiTiet1(String idHDCT, String idCTSP, String idHD, int sl, BigDecimal thanhTien, Date ngayTao, Date ngaySua, String trangThai, boolean deleted, SanPham sp, ChiTietSanPham ctsp) {
        this.idHDCT = idHDCT;
        this.idCTSP = idCTSP;
        this.idHD = idHD;
        this.sl = sl;
        this.thanhTien = thanhTien;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
        this.deleted = deleted;
        this.sp = sp;
        this.ctsp = ctsp;
    }

    public String getIdHDCT() {
        return idHDCT;
    }

    public void setIdHDCT(String idHDCT) {
        this.idHDCT = idHDCT;
    }

    public String getIdCTSP() {
        return idCTSP;
    }

    public void setIdCTSP(String idCTSP) {
        this.idCTSP = idCTSP;
    }

    public String getIdHD() {
        return idHD;
    }

    public void setIdHD(String idHD) {
        this.idHD = idHD;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public SanPham getSp() {
        return sp;
    }

    public void setSp(SanPham sp) {
        this.sp = sp;
    }

    public ChiTietSanPham getCtsp() {
        return ctsp;
    }

    public void setCtsp(ChiTietSanPham ctsp) {
        this.ctsp = ctsp;
    }
    
}
