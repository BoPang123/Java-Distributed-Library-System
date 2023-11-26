package com.hrf.library.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csn.dao.BookDao;
import com.csn.dao.BookIDDao;
import com.csn.dao.ClientDao;
import com.csn.dao.LendingDao;
import com.csn.entity.Book;
import com.csn.entity.BookID;
import com.hrf.common.AjaxResult;
import com.hrf.common.Result;

/**
 * Servlet implementation class ListBorrowedBooks
 */
public class ListBorrowedBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListBorrowedBooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
		String title =  request.getParameter("title");
		ClientDao cd = new ClientDao();
		String userId = cd.getClientID(userName);
		LendingDao ld = new LendingDao();
		List<Map<String, Object>> res = ld.getLendings(userId, title);
				
		Result result = Result.success(res);
		
        // output to http response
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(result.toString().getBytes("utf-8"));  
	}

}
