/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.db;
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

    public static void addClient(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, java.lang.String arg4) {
        package1.SoapService_Service service = new package1.SoapService_Service();
        package1.SoapService port = service.getSoapServicePort();
        port.addClient(arg0, arg1, arg2, arg3, arg4);
    }
     /**
     * Add a client
     *
     * @return The lending book list.
     */
//     public void addClient(String clientID,String name,String password, String email,String telephone) {
//        String sql = "insert into clientinfo values('"+clientID +"','"+name+"','"+password+"','"+email+"','"+ telephone + "')";  
//        Connection connection = DBUtil.getConn();
//        Statement statement = DBUtil.createStmt(connection);
//        DBUtil.executeUpdate(statement, sql);
//        DBUtil.closestmt(statement);
//        DBUtil.closeconn(connection);
//        
//        
//        
//        //////////////////////////////////////////////pangbo database2///////////////////////////////////////////
//        Connection connection2 = DBUtil.getConn2();
//        Statement statement2 = DBUtil.createStmt(connection2);
//        DBUtil.executeUpdate(statement2, sql);
//        DBUtil.closestmt(statement2);
//        DBUtil.closeconn(connection2);
//    }
    
     
}
