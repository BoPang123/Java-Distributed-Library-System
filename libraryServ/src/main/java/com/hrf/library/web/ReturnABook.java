package com.hrf.library.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csn.dao.BookDao;
import com.csn.dao.BookIDDao;
import com.csn.dao.LendingDao;
import com.hrf.common.AjaxResult;
import com.hrf.common.Result;

/**
 * Servlet implementation class ReturnABook
 */
public class ReturnABook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnABook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String userName = request.getParameter("userName");
		String bookID =  request.getParameter("bookID");
                System.out.println(bookID);
                BookIDDao bid = new BookIDDao();
                bid.changeava(bookID);
                BookDao bd = new BookDao();
                bd.redLendingQ(bid.getISBN(bookID));
                LendingDao ld = new LendingDao();
                ld.delLending(bookID);
		Result result= Result.success("");
		response.getWriter().append(result.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
