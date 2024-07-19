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
public class NguoiDung {

    private UUID idND;
    private String maNguoidung;
    private String matKhau;
    private String hoTen;
    private Date ngaySinh;
    private String hinhAnh;
    private String soDienthoai;
    private String eMail;
    private String diaChi;
    private int vaiTro,Gioitinh;
    private Date ngayTao;
    private Date ngaySua;
    private String trangThai;
    private int delete;

    public NguoiDung() {
    }

    public NguoiDung(UUID idND, String maNguoidung, String matKhau, String hoTen, Date ngaySinh, String hinhAnh, String soDienthoai, String eMail, String diaChi, int vaiTro, int Gioitinh, Date ngayTao, Date ngaySua, String trangThai, int delete) {
        this.idND = idND;
        this.maNguoidung = maNguoidung;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.hinhAnh = hinhAnh;
        this.soDienthoai = soDienthoai;
        this.eMail = eMail;
        this.diaChi = diaChi;
        this.vaiTro = vaiTro;
        this.Gioitinh = Gioitinh;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
        this.delete = delete;
    }

    public int getGioitinh() {
        return Gioitinh;
    }

    public void setGioitinh(int Gioitinh) {
        this.Gioitinh = Gioitinh;
    }

    

    public UUID getIdND() {
        return idND;
    }

    public void setIdND(UUID idND) {
        this.idND = idND;
    }

    public String getMaNguoidung() {
        return maNguoidung;
    }

    public void setMaNguoidung(String maNguoidung) {
        this.maNguoidung = maNguoidung;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getSoDienthoai() {
        return soDienthoai;
    }

    public void setSoDienthoai(String soDienthoai) {
        this.soDienthoai = soDienthoai;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(int vaiTro) {
        this.vaiTro = vaiTro;
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

}
