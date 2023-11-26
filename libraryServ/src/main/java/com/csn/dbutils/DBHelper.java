/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csn.dbutils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ning
 */
public class DBHelper {
    //public static String bank =null; //这里就是定义的变量，在url里引入变量
    //public  String url = "jdbc:mysql://你的ip地址/"+bank+"autoReconnect=true&amp;failOverReadOnly=false&&useSSL=false";

    //public static final String name = "com.mysql.cj.jdbc.Driver";
    //public static final String user = "数据库账号";
    //public static final String password = "数据密码";

    public Connection conn = null;
    //public PreparedStatement pst = null;

    public Connection getConn() {
        return conn;
    }

    public DBHelper(String url,String user,String password, String name) {
 //               System.out.println("11111");
        System.out.println(url);
        try {
            Class.forName(name);//
            conn = DriverManager.getConnection(url, user, password);//
            //pst = conn.prepareStatement(sql);//
            //System.out.println(pst);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void close() {
//        try {
//            this.conn.close();
//            //this.pst.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

}

