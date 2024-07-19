/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.SanPhamMuaNhieu;
import reponsitory.SanPhamMuaNhieuRS;

/**
 *
 * @author Asus
 */
public class SanPhamMuaNhieuS {

    private final SanPhamMuaNhieuRS spmnr = new SanPhamMuaNhieuRS();

    public ArrayList<SanPhamMuaNhieu> getAllSanPham() throws Exception {
        return spmnr.getAllSanPham();
    }
}
