/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Classes.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
public class login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private PreparedStatement ps;
    private ResultSet rs;
    private MessageDigest md;
    private Connection conn;
    private Database db;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        db = new Database();
        try (PrintWriter out = response.getWriter()) {
            //get paremeter values
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            //password encryption with MD5
            try {
                md = MessageDigest.getInstance("MD5");
                byte[] thedigest = md.digest(password.getBytes("UTF-8"));
                password = new BigInteger(1, thedigest).toString(16);
            } catch (NoSuchAlgorithmException ex) {
                System.out.println("MD5 failed ! " + ex);
            }
            try {
                conn = db.getCon();
                ps = conn.prepareStatement("select * from users where userName=? and password=?");
                ps.setString(1, username);
                ps.setString(2, password);
                //find userName with this password in db
                rs = ps.executeQuery();
                //response
                if (rs.next()) {
                    //login success 
                    RequestDispatcher rd = request.getRequestDispatcher("/read.jsp");
                    rd.forward(request, response);
                } else {
                    //login failed
                    request.setAttribute("login_fail", "Please check your username and password.");
                    RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                    rd.include(request, response);
                }
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Connect failed ! " + ex);
            }

            out.close();
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
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
