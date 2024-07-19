/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.SQLException;
import java.util.ArrayList;
import model.NguoiDung;
import reponsitory.NguoiDungReponsitory;

/**
 *
 * @author Asus
 */
public class NguoiDungService {

    private NguoiDungReponsitory ndr = new NguoiDungReponsitory();

    public NguoiDung checkLogin(String manguoidung, String matkhau) throws Exception {
        return ndr.checkLogin(manguoidung, matkhau);
    }

    public ArrayList<NguoiDung> getAllNguoiDung() throws Exception {
        return ndr.getAllNguoiDung();
    }

    public Integer addNguoiDung(NguoiDung nd) throws SQLException, Exception {
        return ndr.addNguoiDung(nd);
    }

    public Integer updateNguoiDung(NguoiDung nd) throws Exception {
        return ndr.updateNguoiDung(nd);
    }
    public boolean DoiMatKhau(NguoiDung nd) throws Exception {
        return ndr.DoiMatKhau(nd);
    }
}
