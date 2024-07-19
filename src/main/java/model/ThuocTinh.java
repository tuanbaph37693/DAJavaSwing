/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class ThuocTinh {
    private String id;
    private String ten ;
    private Date ngayTao ;
    private Date ngaySua;
    private String trangThai;
    private boolean deleted ;

    @Override
    public String toString() {
        return ten;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
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

    public ThuocTinh(String ten, String trangThai) {
        this.ten = ten;
        this.trangThai = trangThai;
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

    public ThuocTinh(String ten, Date ngaySua, String trangThai) {
        this.ten = ten;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

    public ThuocTinh(String id, String ten, Date ngayTao, Date ngaySua, String trangThai, boolean deleted) {
        this.id = id;
        this.ten = ten;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
        this.deleted = deleted;
    }

    public ThuocTinh() {
    }
}
