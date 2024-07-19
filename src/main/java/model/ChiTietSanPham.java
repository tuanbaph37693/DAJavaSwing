/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Admin
 */

public class ChiTietSanPham {
    private String id;
    private String idSanPham;
    private String idThuongHieu;
    private String idTheLoai;
    private String idThietKe;
    private String idPhongCach;
    private String idCoAo;
    private String idMauSac;
    private String idChatLieu;
    private String idKichThuoc;
    private String idNSX;
    private String hinhAnh;
    private int soLuong;
    private java.math.BigDecimal giaBan;
    private Date ngayNhap;
    private Date ngaySua;
    private String moTa;
    private String trangThai;
    private boolean deleted;
    private String maCTSP;
    private String maQr;

    public String getMaQr() {
        return maQr;
    }

    public void setMaQr(String maQr) {
        this.maQr = maQr;
    }

    public String getMaCTSP() {
        return id.substring(id.length() - 6);
    }

    public ChiTietSanPham() {
    }
    
        public ChiTietSanPham(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public ChiTietSanPham(String id) {
        this.id = id;
    }

    public ChiTietSanPham(String id, String idSanPham, String idThuongHieu, String idTheLoai, String idThietKe, String idPhongCach, String idCoAo, String idMauSac, String idChatLieu, String idKichThuoc, String idNSX, String hinhAnh, int soLuong, java.math.BigDecimal giaBan, Date ngayNhap, Date ngaySua, String moTa, String trangThai, boolean deleted) {
        this.id = id;
        this.idSanPham = idSanPham;
        this.idThuongHieu = idThuongHieu;
        this.idTheLoai = idTheLoai;
        this.idThietKe = idThietKe;
        this.idPhongCach = idPhongCach;
        this.idCoAo = idCoAo;
        this.idMauSac = idMauSac;
        this.idChatLieu = idChatLieu;
        this.idKichThuoc = idKichThuoc;
        this.idNSX = idNSX;
        this.hinhAnh = hinhAnh;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.ngayNhap = ngayNhap;
        this.ngaySua = ngaySua;
        this.moTa = moTa;
        this.trangThai = trangThai;
        this.deleted = deleted;
    }

    public ChiTietSanPham(String idSanPham, String idThuongHieu, String idTheLoai, String idThietKe, String idPhongCach, String idCoAo, String idMauSac, String idChatLieu, String idKichThuoc, String idNSX, String hinhAnh, int soLuong, java.math.BigDecimal giaBan, String moTa, String trangThai) {
        this.idSanPham = idSanPham;
        this.idThuongHieu = idThuongHieu;
        this.idTheLoai = idTheLoai;
        this.idThietKe = idThietKe;
        this.idPhongCach = idPhongCach;
        this.idCoAo = idCoAo;
        this.idMauSac = idMauSac;
        this.idChatLieu = idChatLieu;
        this.idKichThuoc = idKichThuoc;
        this.idNSX = idNSX;
        this.hinhAnh = hinhAnh;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getIdThuongHieu() {
        return idThuongHieu;
    }

    public void setIdThuongHieu(String idThuongHieu) {
        this.idThuongHieu = idThuongHieu;
    }

    public String getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(String idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    public String getIdThietKe() {
        return idThietKe;
    }

    public void setIdThietKe(String idThietKe) {
        this.idThietKe = idThietKe;
    }

    public String getIdPhongCach() {
        return idPhongCach;
    }

    public void setIdPhongCach(String idPhongCach) {
        this.idPhongCach = idPhongCach;
    }

    public String getIdCoAo() {
        return idCoAo;
    }

    public void setIdCoAo(String idCoAo) {
        this.idCoAo = idCoAo;
    }

    public String getIdMauSac() {
        return idMauSac;
    }

    public void setIdMauSac(String idMauSac) {
        this.idMauSac = idMauSac;
    }

    public String getIdChatLieu() {
        return idChatLieu;
    }

    public void setIdChatLieu(String idChatLieu) {
        this.idChatLieu = idChatLieu;
    }

    public String getIdKichThuoc() {
        return idKichThuoc;
    }

    public void setIdKichThuoc(String idKichThuoc) {
        this.idKichThuoc = idKichThuoc;
    }

    public String getIdNSX() {
        return idNSX;
    }

    public void setIdNSX(String idNSX) {
        this.idNSX = idNSX;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public java.math.BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(java.math.BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "ChiTietSanPham{" + "id=" + id + ", idSanPham=" + idSanPham + ", idThuongHieu=" + idThuongHieu + ", idTheLoai=" + idTheLoai + ", idThietKe=" + idThietKe + ", idPhongCach=" + idPhongCach + ", idCoAo=" + idCoAo + ", idMauSac=" + idMauSac + ", idChatLieu=" + idChatLieu + ", idKichThuoc=" + idKichThuoc + ", idNSX=" + idNSX + ", hinhAnh=" + hinhAnh + ", soLuong=" + soLuong + ", giaBan=" + giaBan + ", ngayNhap=" + ngayNhap + ", ngaySua=" + ngaySua + ", moTa=" + moTa + ", trangThai=" + trangThai + ", deleted=" + deleted + ", maCTSP=" + maCTSP + ", maQr=" + maQr + '}';
    }

    
    
}
