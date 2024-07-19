/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.SQLException;
import java.util.ArrayList;
import model.HoaDon;
import model.KhachHang;
import reponsitory.HoaDonReponsitory;

/**
 *
 * @author Asus
 */
public class HoaDonService {
    private final HoaDonReponsitory hdr = new HoaDonReponsitory();
    public ArrayList<HoaDon> getAllHoaDon() throws SQLException, Exception {
        return hdr.getAllHoaDon();
    }
    public boolean themHoaDon(KhachHang kh, HoaDon hd) throws Exception {
        return hdr.themHoaDon(kh, hd);
    }
    
    public boolean taoHoaDon(KhachHang kh, HoaDon hd) throws Exception{
        return hdr.taoHoaDon(kh, hd);
    }
}
