/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.db.*;
import java.util.Random;
/**
 *
 * @author Ning
 */
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
        
        String name = request.getParameter("userName");
        String password = request.getParameter("userPassword");
        String email = request.getParameter("userEmail");
        String telephone = request.getParameter("userTele");
        System.out.println(name+password+email+telephone);
        try{
            if((name!=null) && (name.length()>0)&&(password!=null) && (password.length()>0)&&(email!=null) && (email.length()>0)&&(telephone!=null) && (telephone.length()>0)){
                ClientDao cd = new ClientDao();
                Random random = new Random();
                int s = random.nextInt(10000)%(9980+1) + 20;
                cd.addClient(String.valueOf(s),name, password, email, telephone);
                System.out.println("Success");
                String json ="{\"code\":0,\"msg\":\"Success\"}";
                response.getWriter().write(json);
            }else{
                String json ="{\"code\":1,\"msg\":\"You should finish all the information!\"}";
                response.getWriter().write(json);
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
