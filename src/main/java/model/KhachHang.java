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
public class KhachHang {

    private UUID idKH;
    private String tenKhachHang;
    private String soDienthoai;
    private String diaChi;
    private Date ngayTao;
    private Date ngaySua;
    private String nguoiTao;
    private String trangThai;
    private int delete;

    public KhachHang() {
    }

    public KhachHang(UUID idKH, String tenKhachHang, String soDienthoai, String diaChi, Date ngayTao, Date ngaySua, String nguoiTao, String trangThai, int delete) {
        this.idKH = idKH;
        this.tenKhachHang = tenKhachHang;
        this.soDienthoai = soDienthoai;
        this.diaChi = diaChi;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.nguoiTao = nguoiTao;
        this.trangThai = trangThai;
        this.delete = delete;
    }

    public UUID getIdKH() {
        return idKH;
    }

    public void setIdKH(UUID idKH) {
        this.idKH = idKH;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
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

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
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
