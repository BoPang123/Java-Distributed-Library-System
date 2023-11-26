/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csn.dao;
import com.csn.entity.Client;
import com.csn.dbutils.DBUtil;
import com.csn.dao.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Ning
 * 操作Client表的类
 */
public class ClientDao {
     /**
     * Add a client
     *
     * @return The lending book list.
     */
     public void addClient(String clientID,String name,String password, String email,String telephone) {
        String sql = "insert into clientInfo values('"+clientID+"','" + name +"','"+password+"','"+email+"','"+ telephone + "')";  
        Connection connection = DBUtil.getConn();
        Statement statement = DBUtil.createStmt(connection);
        DBUtil.executeUpdate(statement, sql);
        DBUtil.closestmt(statement);
        DBUtil.closeconn(connection);
        
        
        
        //////////////////////////////////////////////pangbo database2///////////////////////////////////////////
        Connection connection2 = DBUtil.getConn2();
        Statement statement2 = DBUtil.createStmt(connection2);
        DBUtil.executeUpdate(statement2, sql);
        DBUtil.closestmt(statement2);
        DBUtil.closeconn(connection2);
    }
     
/**
     * Check the name and password 
     *在登陆时检查
     * @return The lending book list.
     */
    public boolean checkClient(String name, String password){
        String sql = "select * from clientInfo Where userName = '"+name+"' and userPassword = '"+password+"'";  
        
        
        
        //////////////////////////////////////////////pangbo database2///////////////////////////////////////////
        try{
            
            Connection connection = DBUtil.getConn();
        Statement statement = DBUtil.createStmt(connection);
        ResultSet resultSet = DBUtil.executeQuery(statement, sql);
         try {
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closers(resultSet);
            DBUtil.closestmt(statement);
            DBUtil.closeconn(connection);
        }
        return false;
            
            
        } catch (Exception e) {

            Connection connection = DBUtil.getConn2();
        Statement statement = DBUtil.createStmt(connection);
        ResultSet resultSet = DBUtil.executeQuery(statement, sql);
         try {
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        } finally {
            DBUtil.closers(resultSet);
            DBUtil.closestmt(statement);
            DBUtil.closeconn(connection);
        }
        return false;
        }
        
    }
    
        
          /**
     *得到用户ID
     * @return ID
     */
    public String getClientID(String name){
        String sql = "select ID from clientInfo Where userName = '"+name+"'";  
        try {
        Connection connection = DBUtil.getConn();
        Statement statement = DBUtil.createStmt(connection);
        ResultSet resultSet = DBUtil.executeQuery(statement, sql);
         try {
            if (resultSet.next()) {
                return resultSet.getString("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closers(resultSet);
            DBUtil.closestmt(statement);
            DBUtil.closeconn(connection);
        }

         } catch (Exception e) {
              Connection connection = DBUtil.getConn2();
        Statement statement = DBUtil.createStmt(connection);
        ResultSet resultSet = DBUtil.executeQuery(statement, sql);
         try {
            if (resultSet.next()) {
                return resultSet.getString("ID");
            }
        } catch (SQLException e2) {
            e.printStackTrace();
        } finally {
            DBUtil.closers(resultSet);
            DBUtil.closestmt(statement);
            DBUtil.closeconn(connection);
        }
    }
                return null;
    }
}
