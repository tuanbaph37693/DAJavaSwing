/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.UUID;
//import javax.xml.crypto.Data;

/**
 *
 * @author Asus
 */
public class PhieuGiamGia {
    private UUID idVoucher;
    private String maVoucher;
    private String tenVoucher;
    private Float giaTri;
    private int soLuong;
    private Date ngayTao;
    private Date ngaySua;
    private Date ngayBatdau;
    private Date ngayKetthuc;
    private String moTa;
    private String trangThai;
    private int delete;

    public PhieuGiamGia() {
    }

    public PhieuGiamGia(UUID idVoucher, String maVoucher, String tenVoucher, Float giaTri, int soLuong, Date ngayTao, Date ngaySua, Date ngayBatdau, Date ngayKetthuc, String moTa, String trangThai, int delete) {
        this.idVoucher = idVoucher;
        this.maVoucher = maVoucher;
        this.tenVoucher = tenVoucher;
        this.giaTri = giaTri;
        this.soLuong = soLuong;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.ngayBatdau = ngayBatdau;
        this.ngayKetthuc = ngayKetthuc;
        this.moTa = moTa;
        this.trangThai = trangThai;
        this.delete = delete;
    }

    public UUID getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(UUID idVoucher) {
        this.idVoucher = idVoucher;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public String getTenVoucher() {
        return tenVoucher;
    }

    public void setTenVoucher(String tenVoucher) {
        this.tenVoucher = tenVoucher;
    }

    public Float getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(Float giaTri) {
        this.giaTri = giaTri;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
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

    public Date getNgayBatdau() {
        return ngayBatdau;
    }

    public void setNgayBatdau(Date ngayBatdau) {
        this.ngayBatdau = ngayBatdau;
    }

    public Date getNgayKetthuc() {
        return ngayKetthuc;
    }

    public void setNgayKetthuc(Date ngayKetthuc) {
        this.ngayKetthuc = ngayKetthuc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }
    
}
