/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.UUID;

/**
 *
 * @author Asus
 */
public class HoaDonChiTiet {

    private UUID idHDCT;
    private UUID idCTSP;
    private UUID idHD;
    private int soLuong;
    private Float giaTien;
    private Date ngayTao;
    private Date ngaySua;
    private String trangThai;
    private int delete;
    private String ten, kichthuoc;
    private int soluong;
    private Float giaban;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(UUID idHDCT, UUID idCTSP, UUID idHD, int soLuong, Float giaTien, Date ngayTao, Date ngaySua, String trangThai, int delete, String ten, String kichthuoc, int soluong, Float giaban) {
        this.idHDCT = idHDCT;
        this.idCTSP = idCTSP;
        this.idHD = idHD;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
        this.delete = delete;
        this.ten = ten;
        this.kichthuoc = kichthuoc;
        this.soluong = soluong;
        this.giaban = giaban;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getKichthuoc() {
        return kichthuoc;
    }

    public void setKichthuoc(String kichthuoc) {
        this.kichthuoc = kichthuoc;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public Float getGiaban() {
        return giaban;
    }

    public void setGiaban(Float giaban) {
        this.giaban = giaban;
    }

    public UUID getIdHDCT() {
        return idHDCT;
    }

    public void setIdHDCT(UUID idHDCT) {
        this.idHDCT = idHDCT;
    }

    public UUID getIdCTSP() {
        return idCTSP;
    }

    public void setIdCTSP(UUID idCTSP) {
        this.idCTSP = idCTSP;
    }

    public UUID getIdHD() {
        return idHD;
    }

    public void setIdHD(UUID idHD) {
        this.idHD = idHD;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Float getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(Float giaTien) {
        this.giaTien = giaTien;
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

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return "HoaDonChiTiet{" + "idHDCT=" + idHDCT + ", idCTSP=" + idCTSP + ", idHD=" + idHD + ", soLuong=" + soLuong + ", giaTien=" + giaTien + ", ngayTao=" + ngayTao + ", ngaySua=" + ngaySua + ", trangThai=" + trangThai + ", delete=" + delete + ", ten=" + ten + ", kichthuoc=" + kichthuoc + ", soluong=" + soluong + ", giaban=" + giaban + '}';
    }
    
}
