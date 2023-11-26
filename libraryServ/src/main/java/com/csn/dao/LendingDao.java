
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csn.dao;
import com.csn.entity.Lending;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.csn.dbutils.DBUtil;
import com.csn.dbutils.DBUtil;
import java.util.Map;

/**
 *
 * @author Ning
 * 对Lending表操作的类
 */
public class LendingDao {
        /**
     * Output all records of lending books
     *输出所有内容
     * @return The lending book list.
     */
    public List<Map<String, Object>> getLendings(String userId, String title) {
        String sql = "SELECT * FROM Lending where ID = '" + userId + "'";
               if(title!=null){ 
                   sql = sql+" and title Like '" + "%"+ title + "%"+"'";
               }
        Connection connection = DBUtil.getConn();
        Statement statement = DBUtil.createStmt(connection);
        ResultSet resultSet = DBUtil.executeQuery(statement, sql);
       java.util.List<Map<String, Object>> result = DBUtil.getQueryResult(resultSet);
        return result;
    }
    /**
     * Output all records of lending books
     *输出所有内容
     * @return The lending book list.
     */
    public List<Lending> getAllLendings( ) {
        String sql = "SELECT * FROM Lending";
        
        //////////////////////////////////////////////pangbo database2///////////////////////////////////////////
        try{
            Connection connection = DBUtil.getConn();
            Statement statement = DBUtil.createStmt(connection);
        ResultSet resultSet = DBUtil.executeQuery(statement, sql);
        List<Lending> lendList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Lending lend = new Lending();
                lend.setBook_ID(resultSet.getString("bookID"));
                lend.setClient_ID(resultSet.getString("ID"));
                lend.setTitle(resultSet.getString("title"));
                System.out.print(lend.toString());
                lendList.add(lend);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closers(resultSet);
            DBUtil.closestmt(statement);
            DBUtil.closeconn(connection);
        }
        return lendList;
        
        
        
        
        
        } catch (Exception e) {
            
            
            
            
             Connection connection = DBUtil.getConn2();
             Statement statement = DBUtil.createStmt(connection);
        ResultSet resultSet = DBUtil.executeQuery(statement, sql);
        List<Lending> lendList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Lending lend = new Lending();
                lend.setBook_ID(resultSet.getString("bookID"));
                lend.setClient_ID(resultSet.getString("ID"));
                lend.setTitle(resultSet.getString("title"));
                System.out.print(lend.toString());
                lendList.add(lend);
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        } finally {
            DBUtil.closers(resultSet);
            DBUtil.closestmt(statement);
            DBUtil.closeconn(connection);
        }
        return lendList;
        } 
        
        
    }
    
    /**
     * Add a record when a book has been lended
     * 在有书被借时增加一条记录
     * @return The lending book list.
     */
    public void addLending(String clientID,String bookID,String name) {
        String sql = "insert into lending values('"+clientID +"','"+bookID+"','"+name+"')";
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
     * Delete a record when a book has been returned
     * 但有书被归还时删除一条记录
     * @return The lending book list.
     */
    public void delLending(String bookID) {
        String sql = "delete from lending where bookID = '" + bookID +"'";
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
}
