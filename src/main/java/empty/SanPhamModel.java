/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empty;

import java.sql.Date;
import java.util.UUID;

/**
 *
 * @author Asus
 */
public class SanPhamModel {
    private UUID idSP; 
    private String maSanpham;
    private String tenSanham;
    private Date ngayTao;
    private Date ngaySua;
    private String trangThai;
    private int delete;

    public SanPhamModel() {
    }

    public SanPhamModel(UUID idSP, String maSanpham, String tenSanham, Date ngayTao, Date ngaySua, String trangThai, int delete) {
        this.idSP = idSP;
        this.maSanpham = maSanpham;
        this.tenSanham = tenSanham;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
        this.delete = delete;
    }

    public UUID getIdSP() {
        return idSP;
    }

    public void setIdSP(UUID idSP) {
        this.idSP = idSP;
    }

    public String getMaSanpham() {
        return maSanpham;
    }

    public void setMaSanpham(String maSanpham) {
        this.maSanpham = maSanpham;
    }

    public String getTenSanham() {
        return tenSanham;
    }

    public void setTenSanham(String tenSanham) {
        this.tenSanham = tenSanham;
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
