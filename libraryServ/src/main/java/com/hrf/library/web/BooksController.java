package com.hrf.library.web;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSONObject;
import com.csn.dbutils.DBUtil;
import com.csn.entity.Book;
import com.hrf.common.Result;

/**
 * Servlet implementation class BooksController
 */
public class BooksController extends HttpServlet {
	private static final long serialVersionUID = 2345L;

    /**
     * Default constructor. 
     */
    public BooksController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
        String conditions ="";
		 
		String ISBN = request.getParameter("ISBN");	
		String title = request.getParameter("title");
		String author =  request.getParameter("author");
		String publisher = request.getParameter("publisher");
		Integer inventoryQuantity;
		try {
			inventoryQuantity = Integer.parseInt(request.getParameter("inventoryQuantity"));
		} catch(Exception e) {
			inventoryQuantity =0;
		}
		String position = request.getParameter("position");

        if((ISBN!=null) && (ISBN.length()>0)){
            conditions = "ISBN = " + ISBN;
        }else if((title!=null) && (title.length()>0)){
            conditions = "title Like '" + "%" + title + "%" + "'"; 
        }else if((author!=null) && (author.length()>0)){
            conditions = "author Like '" + "%" + author + "%" + "'";
        }else if((publisher!=null) && (publisher.length()>0)){
            conditions = "publisher = '" + publisher  + "'";
        }else if ((inventoryQuantity!=0)){
            conditions = "inventoryQuantity = " + inventoryQuantity ;
        }else if((position!=null) && (position.length()>0)){
            conditions = "position =  '" + position + "'";
        }
		
        String sql = "SELECT * FROM library";

        if(conditions.length()>0) {
            sql = sql + " where " + conditions;
        }
        System.out.println(sql);
        Connection connection = DBUtil.getConn();
        Statement statement = DBUtil.createStmt(connection);
        ResultSet resultSet = DBUtil.executeQuery(statement, sql);
        java.util.List<Map<String, Object>> list = DBUtil.getQueryResult(resultSet);        
        
        Result result = Result.success(list);
        
        // output to http response
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(result.toString().getBytes("utf-8"));        
		

		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
