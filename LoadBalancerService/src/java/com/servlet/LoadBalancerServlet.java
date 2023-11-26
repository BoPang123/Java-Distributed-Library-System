/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ning
 */
public class LoadBalancerServlet extends HttpServlet {

    private Integer  index = 0;
    ArrayList<String> ips = new ArrayList<String>(){
        {
            add("172.20.10.4");//zyj
//            add("1111.0.0.0");
//            add("127.0.0.0");
            add("172.20.10.3"); //csn
        }
    };
    
      public String roundRobin(){
        String serverIp;
        synchronized(index){
            if (index >= ips.size()){
                index = 0;
            }
            serverIp= ips.get(index);
            //轮询+1
            index ++;
        }
        return serverIp;
    }
      
      
    public  static  boolean ping(String ipAddress)  throws Exception {
         int  timeOut =  3000 ;   // 超时应该在3钞以上        
         boolean status = InetAddress.getByName(ipAddress).isReachable(timeOut);      //  当返回值是true时，说明host是可用的，false则不可。
         return status;
    }
    
    public String getIP(){
            try {
            String ip=roundRobin();
            if(ping(ip)==true){
                return ip;
            }
            else{
                return roundRobin();
            }
        } catch (Exception ex) {
        }
            return "";
  }
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
        System.out.println("1111111111111111111111111111111111");
        String json ="{\"ip\":\"http://"+ getIP()+":8000\"}";
        System.out.println(json);
        response.getWriter().write(json);
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
