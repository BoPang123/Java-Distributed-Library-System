/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csn.dao;

import com.csn.entity.Book;
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
 * 对书进行操作的类
 * 
 */
public class BookDao {

    
  /**
     * Encapsulates the operation of querying the database by ID.
     *根据书的ISBN查找书
     * @param book_id ID number of a book.
     * @return The selected product list.
     */
    public Book getProductById(String book_id) {
        String sql = "SELECT * FROM library WHERE ISBN = " + book_id;
        Connection connection = DBUtil.getConn();
        Statement statement = DBUtil.createStmt(connection);
        ResultSet resultSet = DBUtil.executeQuery(statement, sql);
        Book book = new Book();
        try {
            while (resultSet.next()) {
                book.setISBN(book_id);
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setTheDate(resultSet.getInt("theDate"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setInventoryQuantity(resultSet.getInt("inventoryQuantity"));
                book.setLendingQuantity(resultSet.getInt("lendingQuantity"));
                book.setPosition(resultSet.getString("position"));
                //book.setCover(resultSet.getString("cover"));
                System.out.println(book.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closers(resultSet);
            DBUtil.closestmt(statement);
            DBUtil.closeconn(connection);
        }
        
         
        return book;
    }

    
    /**
     * When lending a book, the lending quantity need to reduce 1
     * 当书被归还时，被借数量要减少
     * @param book_name The English name of the book.
     * @return The result of operation
     */
    public boolean redLendingQ(String ISBN) {
        int r = 0;
        //////////////////////////////////////////////pangbo database2///////////////////////////////////////////
        String sql2 = "SELECT inventoryQuantity,lendingQuantity FROM library WHERE ISBN = '" + ISBN + "'";
        System.out.println(sql2);
        // Find items in the book name that contain user input.
        Connection connection2 = DBUtil.getConn2();
        Statement statement2 = DBUtil.createStmt(connection2);
        ResultSet resultSet2 = DBUtil.executeQuery(statement2, sql2);
        try {
             while (resultSet2.next()) {
            int iq = resultSet2.getInt("inventoryQuantity");
            int ld = resultSet2.getInt("lendingQuantity");
            if(ld > 0){
                String sql3 =  "Update library set lendingQuantity = " + (ld-1) + " WHERE ISBN = '"  + ISBN + "'";                
                DBUtil.executeUpdate(statement2, sql3);
                System.out.println(ld);
                r++;
                break;
                }
             }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closers(resultSet2);
            DBUtil.closestmt(statement2);
            DBUtil.closeconn(connection2);
        }

        String sql = "SELECT inventoryQuantity,lendingQuantity FROM library WHERE ISBN = '" + ISBN + "'";
        System.out.println(sql);
        // Find items in the book name that contain user input.
        Connection connection = DBUtil.getConn();
        Statement statement = DBUtil.createStmt(connection);
        ResultSet resultSet = DBUtil.executeQuery(statement, sql);
        try {
             while (resultSet.next()) {
            int iq = resultSet.getInt("inventoryQuantity");
            int ld = resultSet.getInt("lendingQuantity");
            if(ld > 0){
                String sql1 =  "Update library set lendingQuantity = " + (ld-1) + " WHERE ISBN = '"  + ISBN + "'";                
                DBUtil.executeUpdate(statement, sql1);
                System.out.println(ld);
                r++;
                break;
            }
             }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closers(resultSet);
            DBUtil.closestmt(statement);
            DBUtil.closeconn(connection);
        }
        if(r==2){return true;}
        return false;
    }
    
        /**
     * When returning a book, the lending quantity need to increase 1
     * 当书被借走时，被借数量要增加
     * @param book_name The English name of the book.
     * @return The result of operation
     */
    public boolean insLendingQ(String ISBN) {   
        //////////////////////////////////////////////pangbo database2///////////////////////////////////////////
        int r = 0;
        String sql2 = "SELECT inventoryQuantity,lendingQuantity FROM library WHERE ISBN = '" + ISBN + "'";
        // Find items in the book name that contain user input.
        Connection connection2 = DBUtil.getConn2();
        Statement statement2 = DBUtil.createStmt(connection2);
        ResultSet resultSet2 = DBUtil.executeQuery(statement2, sql2);
        try {
           while (resultSet2.next()) {
            int iq = resultSet2.getInt("inventoryQuantity");
            int ld = resultSet2.getInt("lendingQuantity");
            if(iq>ld){
                String sql3 = "Update library set lendingQuantity = " + (ld+1) + " WHERE ISBN = '"  + ISBN + "'";
                          System.out.println(sql3);
                DBUtil.executeUpdate(statement2, sql3);
                r++;
                break;
            }
             }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closers(resultSet2);
            DBUtil.closestmt(statement2);
            DBUtil.closeconn(connection2);
        }
        
        

        
        String sql = "SELECT inventoryQuantity,lendingQuantity FROM library WHERE ISBN = '" + ISBN + "'";
        // Find items in the book name that contain user input.
        Connection connection = DBUtil.getConn();
        Statement statement = DBUtil.createStmt(connection);
        ResultSet resultSet = DBUtil.executeQuery(statement, sql);
         
        try {
             while (resultSet.next()) {
            int iq = resultSet.getInt("inventoryQuantity");
            int ld = resultSet.getInt("lendingQuantity");
            if(iq>ld){
                String sql1 = "Update library set lendingQuantity = " + (ld+1) + " WHERE ISBN = '"  + ISBN + "'";
                 System.out.println(sql1);
                DBUtil.executeUpdate(statement, sql1);
                r++;
                break;
            }
             }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closers(resultSet);
            DBUtil.closestmt(statement);
            DBUtil.closeconn(connection);
        }
        if(r==2){return true;}
        return false;
    }
}
