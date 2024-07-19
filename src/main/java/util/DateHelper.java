/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class DateHelper {

    static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

    public static Date toDate(String date, String... pattern) {
        try {
            if (pattern.length > 0) {
            DATE_FORMAT.applyPattern(pattern[0]);
        }
        if (date == null) {
            return DateHelper.now();
        }
        return DATE_FORMAT.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static String toString(Date date, String...pattern){
        if (pattern.length > 0) {
            DATE_FORMAT.applyPattern(pattern[0]);
        }
        if (date == null) {
            date = DateHelper.now();
        }
        return DATE_FORMAT.format(date);
    }
    
    public static Date now(){
        return new Date();
    }
    
    public static Date addDays(Date date, int days){
        date.setTime(date.getTime() + days*24*60*60*1000);
        return date;
    }
    
    public static Date add(int days){
        Date now = DateHelper.now();
        now.setTime(now.getTime() + days*24*60*60*1000);
        return now;
    }
}
