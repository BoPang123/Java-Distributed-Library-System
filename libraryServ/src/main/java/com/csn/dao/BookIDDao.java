/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package com.csn.dao;
import com.csn.entity.BookID;
import com.csn.dbutils.DBUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ning
 */
public class BookIDDao {
    /**
     * Get one bookID of an available book
     * 在借书是得到一个ISBN下的第一个没被借出的书的bookid
     * @return The book Id 
     */
    public BookID getbookID(String ISBN) {
        String sql = "SELECT * FROM bookID WHERE ISBN = '" + ISBN + "' and available = true Limit 1";
        // Find items in the book name that contain user input.
        Connection connection = DBUtil.getConn();
        Statement statement = DBUtil.createStmt(connection);
        ResultSet resultSet = DBUtil.executeQuery(statement, sql);
        BookID bookid = new BookID();
        try {
            while (resultSet.next()) {
                bookid.setISBN(resultSet.getString("ISBN"));
                bookid.setBookID(resultSet.getString("bookID"));
                bookid.setAva(resultSet.getBoolean("available"));
                System.out.println(bookid.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closers(resultSet);
            DBUtil.closestmt(statement);
            DBUtil.closeconn(connection);
        }
        return bookid;
    }
    
        
    /**
     * change the state of available
     * 改变借出状态（反转）
     * @return The lending book list.
     */
    public void changeava(String bookID) {

        //////////////////////////////////////////////pangbo database2///////////////////////////////////////////
        String sql2 = "SELECT * FROM bookID WHERE bookID = '" + bookID + "'";
        // Find items in the book name that contain user input.
        Connection connection2 = DBUtil.getConn2();
        Statement statement2 = DBUtil.createStmt(connection2);
        ResultSet resultSet2 = DBUtil.executeQuery(statement2, sql2);
        try {
            if (resultSet2.next()) {
                String sql3;
              if(resultSet2.getBoolean("available")==true){
                  sql3 = "Update bookID SET available = false WHERE bookID = '" + bookID + "'";
              }else{
                  sql3 = "Update bookID SET available = true WHERE bookID = '" + bookID + "'";
              }
              DBUtil.executeUpdate(statement2, sql3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closers(resultSet2);
            DBUtil.closestmt(statement2);
            DBUtil.closeconn(connection2);
        }

        
        String sql = "SELECT * FROM bookID WHERE bookID = '" + bookID + "'";
        // Find items in the book name that contain user input.
        Connection connection = DBUtil.getConn();
        Statement statement = DBUtil.createStmt(connection);
        ResultSet resultSet = DBUtil.executeQuery(statement, sql);
        try {
            if (resultSet.next()) {
                String sql1;
              if(resultSet.getBoolean("available")==true){
                  sql1 = "Update bookID SET available = false WHERE bookID = '" + bookID + "'";
              }else{
                  sql1 = "Update bookID SET available = true WHERE bookID = '" + bookID + "'";
              }
              DBUtil.executeUpdate(statement, sql1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closers(resultSet);
            DBUtil.closestmt(statement);
            DBUtil.closeconn(connection);
        }
    }
    
     /**
     * Get the ISBN
     * @return The ISBN
     */
    public String getISBN(String bookid) {
        String sql = "SELECT ISBN FROM bookID WHERE bookID = '" +bookid+ "'";
        // Find items in the book name that contain user input.
        Connection connection = DBUtil.getConn();
        Statement statement = DBUtil.createStmt(connection);
        ResultSet resultSet = DBUtil.executeQuery(statement, sql);
        try {
            while (resultSet.next()) {
                return resultSet.getString("ISBN");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closers(resultSet);
            DBUtil.closestmt(statement);
            DBUtil.closeconn(connection);
        }
        return bookid;
    }
}
