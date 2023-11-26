/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csn.dao;
import com.csn.entity.LendingHis;
import java.sql.Connection;
import java.sql.Statement;
import com.csn.dbutils.DBUtil;
/**
 *
 * @author Ning
 * 对LendingHis操作的类
 */
public class LendingHisDao {
    /**
     * Add a record when a book has been lended
     * 当有书被借走时增加一条记录
     * @return The lending book list.
     */
     public void addLendingHis(String clientID,String bookID,String name) {
        String sql = "insert into lendingHistory values('"+clientID +"','"+bookID+"','"+name+"')";
        Connection connection = DBUtil.getConn();
        Statement statement = DBUtil.createStmt(connection);
        DBUtil.executeUpdate(statement, sql);
        DBUtil.closestmt(statement);
        DBUtil.closeconn(connection);
    }
}
