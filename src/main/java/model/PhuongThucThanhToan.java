/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo IdeapadGaming
 */
public class PhuongThucThanhToan {
    private String idPttt;
    private String tenPttt;

    public PhuongThucThanhToan() {
    }

    public PhuongThucThanhToan(String tenPttt) {
        this.tenPttt = tenPttt;
    }
    

    public PhuongThucThanhToan(String idPttt, String tenPttt) {
        this.idPttt = idPttt;
        this.tenPttt = tenPttt;
    }

    public String getIdPttt() {
        return idPttt;
    }

    public void setIdPttt(String idPttt) {
        this.idPttt = idPttt;
    }

    public String getTenPttt() {
        return tenPttt;
    }

    public void setTenPttt(String tenPttt) {
        this.tenPttt = tenPttt;
    }
    
    
}
