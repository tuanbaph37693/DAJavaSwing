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
public class SanPhamMuaNhieu {

    private String masp, tensp, mausac, nhasanxuat, solanmua;

    public SanPhamMuaNhieu() {
    }

    public SanPhamMuaNhieu(String masp, String tensp, String mausac, String nhasanxuat, String solanmua) {
        this.masp = masp;
        this.tensp = tensp;
        this.mausac = mausac;
        this.nhasanxuat = nhasanxuat;
        this.solanmua = solanmua;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getMausac() {
        return mausac;
    }

    public void setMausac(String mausac) {
        this.mausac = mausac;
    }

    public String getNhasanxuat() {
        return nhasanxuat;
    }

    public void setNhasanxuat(String nhasanxuat) {
        this.nhasanxuat = nhasanxuat;
    }

    public String getSolanmua() {
        return solanmua;
    }

    public void setSolanmua(String solanmua) {
        this.solanmua = solanmua;
    }

}
