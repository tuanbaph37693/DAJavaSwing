/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public class KhuyenMai1 {

   

    private UUID id;
    private String MaKM,TenKM,GiaTriGiam,TrangThai,MoTa;
    private int soluong;
    private Date NgayBD,NgayKT;

    public KhuyenMai1() {
    }

    public KhuyenMai1(UUID id, String MaKM, String TenKM, String GiaTriGiam, String TrangThai, String MoTa, int soluong, Date NgayBD, Date NgayKT) {
        this.id = id;
        this.MaKM = MaKM;
        this.TenKM = TenKM;
        this.GiaTriGiam = GiaTriGiam;
        this.TrangThai = TrangThai;
        this.MoTa = MoTa;
        this.soluong = soluong;
        this.NgayBD = NgayBD;
        this.NgayKT = NgayKT;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMaKM() {
        return MaKM;
    }

    public void setMaKM(String MaKM) {
        this.MaKM = MaKM;
    }

    public String getTenKM() {
        return TenKM;
    }

    public void setTenKM(String TenKM) {
        this.TenKM = TenKM;
    }

    public String getGiaTriGiam() {
        return GiaTriGiam;
    }

    public void setGiaTriGiam(String GiaTriGiam) {
        this.GiaTriGiam = GiaTriGiam;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public Date getNgayBD() {
        return NgayBD;
    }

    public void setNgayBD(Date NgayBD) {
        this.NgayBD = NgayBD;
    }

    public Date getNgayKT() {
        return NgayKT;
    }

    public void setNgayKT(Date NgayKT) {
        this.NgayKT = NgayKT;
    }

    

    

    
}
