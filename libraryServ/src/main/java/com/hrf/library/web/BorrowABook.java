package com.hrf.library.web;

import java.io.IOException;
import java.io.OutputStream;

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
 * Servlet implementation class BorrowABook
 */
public class BorrowABook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowABook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String ISBN = request.getParameter("ISBN");
		Result result = null;
                // System.out.println("bbbb"+userName+ISBN);
                ClientDao cd = new ClientDao();
                String ClientID = cd.getClientID(userName);
                BookDao bd = new BookDao();
                Book b = bd.getProductById(ISBN);
		if ((b.getInventoryQuantity() - b.getLendingQuantity())>0){
                    bd.insLendingQ(ISBN);
                    BookIDDao bid = new BookIDDao();
                    BookID bi = bid.getbookID(ISBN);
                    bid.changeava(bi.getBookID());
                     LendingDao ld = new LendingDao();
                    ld.addLending(ClientID, bi.getBookID(), b.getTitle());

                    result = Result.success("OK");
        }
        else {
        	result = Result.error("No avaiable book.");
        }
        // output to http response
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(result.toString().getBytes("utf-8"));  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
