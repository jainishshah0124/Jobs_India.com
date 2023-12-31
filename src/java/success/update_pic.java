/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package success;

import com.oreilly.servlet.MultipartRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jainish Shah
 */
public class update_pic extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
        if(session.getAttribute("role").equals("company"))
        {
            MultipartRequest m=new MultipartRequest(request,"C:\\Users\\Jainish Shah\\Documents\\NetBeansProjects\\Job_protal\\web\\image\\company\\New_Company");
            String url="C:\\Users\\Jainish Shah\\Documents\\NetBeansProjects\\Job_protal\\web\\image\\company\\New_Company\\"+m.getParameter("txt1").substring(12);
            System.out.println(""+url);
            try{
                Connection con=Config.connection.getConnection();
                PreparedStatement psmt=con.prepareStatement("update tbl_company set logo_src=? where email='"+session.getAttribute("uname")+"'");
                psmt.setString(1, url);
                psmt.executeUpdate();
                response.sendRedirect("my_profile_company.jsp");
            }
            catch(Exception e)
            {
                System.out.println(""+e);
            }
        }
        else{
         MultipartRequest m=new MultipartRequest(request,"C:\\Users\\Jainish Shah\\Documents\\NetBeansProjects\\Job_protal\\web\\image\\user");
            String url="C:\\Users\\Jainish Shah\\Documents\\NetBeansProjects\\Job_protal\\web\\image\\user\\"+m.getParameter("txt1").substring(12);
            System.out.println(""+url);
            try{
                Connection con=Config.connection.getConnection();
                PreparedStatement psmt=con.prepareStatement("update tbl_user set src=? where email='"+session.getAttribute("uname")+"'");
                psmt.setString(1, url);
                psmt.executeUpdate();
                response.sendRedirect("my_profile.jsp");
            }
            catch(Exception e)
            {
                System.out.println(""+e);
            }
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
