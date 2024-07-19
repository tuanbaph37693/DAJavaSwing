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
public class ChiTietPhuongThuc {
    private String idCTPT;
    private String idHD;
    private String idPttt;
    private BigDecimal soTienMat;
    private BigDecimal chuyenKhoan;
    private BigDecimal tongTien;
    private Date ngayTao;

    public ChiTietPhuongThuc() {
    }

    public ChiTietPhuongThuc(String idCTPT, String idHD, String idPttt, BigDecimal soTienMat, BigDecimal chuyenKhoan, BigDecimal tongTien, Date ngayTao) {
        this.idCTPT = idCTPT;
        this.idHD = idHD;
        this.idPttt = idPttt;
        this.soTienMat = soTienMat;
        this.chuyenKhoan = chuyenKhoan;
        this.tongTien = tongTien;
        this.ngayTao = ngayTao;
    }

    public String getIdCTPT() {
        return idCTPT;
    }

    public void setIdCTPT(String idCTPT) {
        this.idCTPT = idCTPT;
    }

    public String getIdHD() {
        return idHD;
    }

    public void setIdHD(String idHD) {
        this.idHD = idHD;
    }

    public String getIdPttt() {
        return idPttt;
    }

    public void setIdPttt(String idPttt) {
        this.idPttt = idPttt;
    }

    public BigDecimal getSoTienMat() {
        return soTienMat;
    }

    public void setSoTienMat(BigDecimal soTienMat) {
        this.soTienMat = soTienMat;
    }

    public BigDecimal getChuyenKhoan() {
        return chuyenKhoan;
    }

    public void setChuyenKhoan(BigDecimal chuyenKhoan) {
        this.chuyenKhoan = chuyenKhoan;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    
}
