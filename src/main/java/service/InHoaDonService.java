/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.InHoaDon;
import reponsitory.InHoaDonReponsitory;

/**
 *
 * @author Asus
 */
public class InHoaDonService {
    private final InHoaDonReponsitory ihdr = new InHoaDonReponsitory();
    public ArrayList<InHoaDon> getAllHoaDonChiTiet(Object idHoaDon) throws Exception {
        return ihdr.getAllHoaDonChiTiet(idHoaDon);
    }
}
