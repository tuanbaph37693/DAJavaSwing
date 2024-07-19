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
public class ThuongHieu {

    private UUID idTH;
    private String tenThuongHieu;
    private Date ngayTao;
    private Date ngaySua;
    private String trangThai;
    private int delete;

    public ThuongHieu() {
    }

    public ThuongHieu(UUID idTH, String tenThuongHieu, Date ngayTao, Date ngaySua, String trangThai, int delete) {
        this.idTH = idTH;
        this.tenThuongHieu = tenThuongHieu;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
        this.delete = delete;
    }

    public UUID getIdTH() {
        return idTH;
    }

    public void setIdTH(UUID idTH) {
        this.idTH = idTH;
    }

    public String getTenThuongHieu() {
        return tenThuongHieu;
    }

    public void setTenThuongHieu(String tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
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
