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
public class HoaDon {

    private UUID idHD;
    private UUID idNguoiDung;
    private UUID idKhachHang;
    private UUID idVoucher;
    private String tenNguoinhan;
    private String soDienthoai;
    private String diaChi;
    private Float giaTien;
    private Float giamGia;
    private Float tongTien;
    private Date ngayXacNhan;
    private Date ngayVanChuyen;
    private Date ngayNhan;
    private String ghiChu;
    private Float tienVanchuyen;
    private Date ngayTao;
    private Date ngaySua;
    private String trangThai;
    private int delete;
    private String tenNhanVien;
    private String tenKhachHang;

    public HoaDon() {
    }

    public HoaDon(UUID idHD, UUID idNguoiDung, UUID idKhachHang, UUID idVoucher, String tenNguoinhan, String soDienthoai, String diaChi, Float giaTien, Float giamGia, Float tongTien, Date ngayXacNhan, Date ngayVanChuyen, Date ngayNhan, String ghiChu, Float tienVanchuyen, Date ngayTao, Date ngaySua, String trangThai, int delete, String tenNhanVien, String tenKhachHang) {
        this.idHD = idHD;
        this.idNguoiDung = idNguoiDung;
        this.idKhachHang = idKhachHang;
        this.idVoucher = idVoucher;
        this.tenNguoinhan = tenNguoinhan;
        this.soDienthoai = soDienthoai;
        this.diaChi = diaChi;
        this.giaTien = giaTien;
        this.giamGia = giamGia;
        this.tongTien = tongTien;
        this.ngayXacNhan = ngayXacNhan;
        this.ngayVanChuyen = ngayVanChuyen;
        this.ngayNhan = ngayNhan;
        this.ghiChu = ghiChu;
        this.tienVanchuyen = tienVanchuyen;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
        this.delete = delete;
        this.tenNhanVien = tenNhanVien;
        this.tenKhachHang = tenKhachHang;
    }

    public UUID getIdHD() {
        return idHD;
    }

    public void setIdHD(UUID idHD) {
        this.idHD = idHD;
    }

    public UUID getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(UUID idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public UUID getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(UUID idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public UUID getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(UUID idVoucher) {
        this.idVoucher = idVoucher;
    }

    public String getTenNguoinhan() {
        return tenNguoinhan;
    }

    public void setTenNguoinhan(String tenNguoinhan) {
        this.tenNguoinhan = tenNguoinhan;
    }

    public String getSoDienthoai() {
        return soDienthoai;
    }

    public void setSoDienthoai(String soDienthoai) {
        this.soDienthoai = soDienthoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Float getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(Float giaTien) {
        this.giaTien = giaTien;
    }

    public Float getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(Float giamGia) {
        this.giamGia = giamGia;
    }

    public Float getTongTien() {
        return tongTien;
    }

    public void setTongTien(Float tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayXacNhan() {
        return ngayXacNhan;
    }

    public void setNgayXacNhan(Date ngayXacNhan) {
        this.ngayXacNhan = ngayXacNhan;
    }

    public Date getNgayVanChuyen() {
        return ngayVanChuyen;
    }

    public void setNgayVanChuyen(Date ngayVanChuyen) {
        this.ngayVanChuyen = ngayVanChuyen;
    }

    public Date getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(Date ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Float getTienVanchuyen() {
        return tienVanchuyen;
    }

    public void setTienVanchuyen(Float tienVanchuyen) {
        this.tienVanchuyen = tienVanchuyen;
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

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }
}
