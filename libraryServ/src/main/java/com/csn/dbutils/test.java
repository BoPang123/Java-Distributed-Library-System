/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csn.dbutils;
import com.csn.dao.*;
/**
 *
 * @author Ning
 */
public class test {
    public static void main(String[] args) {
//        BookDao bd = new BookDao();
//        bd.insLendingQ("9780553380165");
//           BookIDDao bid = new BookIDDao();
//           bid.changeava("00005");
        LendingDao ld = new LendingDao();
        ld.addLending("0002", "00100", "test0000000");
        //ld.delLending("00050");
    }
            
   
}
