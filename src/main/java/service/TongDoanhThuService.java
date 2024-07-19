/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.TongDoanhThu;
import reponsitory.TongDoanhThuResponsitory;

/**
 *
 * @author Asus
 */
public class TongDoanhThuService {

    private final TongDoanhThuResponsitory tdtr = new TongDoanhThuResponsitory();

    public ArrayList<TongDoanhThu> getAllDoanhThu() throws Exception {
        return tdtr.getAllDoanhThu();
    }
}
