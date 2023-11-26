/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csn.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.csn.dbutils.DBUtil;
/**
 *
 * @author Ning
 */
public class FavorDao {
        /**
     * Add a favorite book
     *
     * @return The lending book list.
     */
     public void addfavorite(String clientID,String bookID,String name) {
        String sql = "insert into favorite values('"+clientID +"','"+bookID+"','"+name+"')";
        Connection connection = DBUtil.getConn();
        Statement statement = DBUtil.createStmt(connection);
        DBUtil.executeUpdate(statement, sql);
        DBUtil.closestmt(statement);
        DBUtil.closeconn(connection);
    }
}
