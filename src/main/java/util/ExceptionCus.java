/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
public class ExceptionCus {

    public static void CheckEmpty(String... string) {
        int count = 0;
        for (int i = 0; i < string.length; i++) {
            if (string[i].trim().isEmpty()) {
                count++;
            }
        }
        if (count != 0) {
            throw new RuntimeException("Xuất hiện giá trị trống");
        }
    }

    public static void CheckValueInput(String regex, String value) {
        if (!value.trim().matches(regex)) {
            throw new RuntimeException("Giá trị không hợp lệ");
        }
    }
    
}
