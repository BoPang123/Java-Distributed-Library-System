/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csn.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ning
 *         连接数据库
 */
public class DBUtil {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    // private static final String driver2 = "com.mysql.cj.jdbc.Driver";
    private static final String url1 = "jdbc:mysql://localhost:3306/new_library?useSSL=false&serverTimezone=Hongkong&characterEncoding=utf-8";
    // =
    // "jdbc:mysql://192.168.1.15:3306/lib?useSSL=false&serverTimezone=Hongkong&characterEncoding=utf-8";
    // "jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=Hongkong&characterEncoding=utf-8";

    private static final String username1 = "root";
    // Our distributed database does not have a password, leave it blank here.
    private static final String password1 = "zhonghua2008";// 需更改你的密码？
    // private static final String password1 = "123456";//需更改你的密码？

    private static final String url2
    // =
    // "jdbc:mysql://172.20.10.4/library?useSSL=false&serverTimezone=Hongkong&characterEncoding=utf-8";
            = "jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=Hongkong&characterEncoding=utf-8";

    private static final String username2 = "root";
    // Our distributed database does not have a password, leave it blank here.
    private static final String password2 = "88888888";// 需更改你的密码？

    // Get connection to our database.
    public static Connection getConn() {
        Connection conn1 = null;
        // try {
        // Class.forName(driver);
        // } catch (ClassNotFoundException e) {
        // e.printStackTrace();
        // System.out.println("Driver class not found.");
        // }
        // try {
        DBHelper h = new DBHelper(url1, username1, password1, driver);
        conn1 = h.getConn();
        // } catch (SQLException e) {
        // e.printStackTrace();
        // System.out.println("Connection failed.");
        // }
        return conn1;
    }

    public static Connection getConn2() {
        Connection conn2 = null;

        // try {
        // Class.forName(driver2);
        // } catch (ClassNotFoundException e) {
        // e.printStackTrace();
        // System.out.println("Driver class not found.");
        // }
        // try {
        // DBHelper h = new DBHelper(url2, username2, password2,driver);
        DBHelper h = new DBHelper(url2, username1, password1, driver);
        conn2 = h.getConn();
        // } catch (SQLException e) {
        // e.printStackTrace();
        // System.out.println("Connection failed.");
        // }
        return conn2;
    }

    public static Statement createStmt(Connection conn) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }

    public static ResultSet executeQuery(Statement stmt, String sql) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static int executeUpdate(Statement stmt, String sql) {
        int rs = 0;
        try {
            rs = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void closers(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closestmt(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeconn(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Map<String, Object>> getQueryResult(ResultSet rs) {

        try {
            List<Map<String, Object>> result = new ArrayList<>();
            List<String> fieldNames = DBUtil.getColumnNames(rs.getMetaData());
            int colNum = fieldNames.size();
            // return if empty
            if ((fieldNames == null) || (colNum == 0)) {
                return result;
            }

            while (rs.next()) {
                Map<String, Object> row = new HashMap<String, Object>();

                for (int i = 1; i <= colNum; i++) {
                    row.put(fieldNames.get(i - 1), rs.getObject(i));
                }
                result.add(row);
            } // end of while

            return result;
        } catch (SQLException e) {
            System.err.println("Error in retrieving data.");
            e.printStackTrace();
        }
        // get here if exception
        return null;
    }

    /**
     * @param rsmd JDBC ResultSetMetaData object.
     * @return List of Strings containing Column Names.
     */
    public static List<String> getColumnNames(ResultSetMetaData rsmd) {

        try {
            int colNum = rsmd.getColumnCount();
            List<String> colNames = new ArrayList<String>();

            for (int i = 1; i <= colNum; i++) {
                colNames.add(rsmd.getColumnName(i));
            }

            return colNames;
        } catch (SQLException e) {
            System.err.println("Error in retrieving column names.");
            e.printStackTrace();
        }

        return null;
    }

}
