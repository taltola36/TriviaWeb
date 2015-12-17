/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myServlets.Welcome;
/**
 *
 * @author HP
 */
public class MoreGames extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("<html><head>");
        out.print("<title>Trivia Game</title><link rel='shortcut icon' href='http://www.smartlabtoys.com/media/chalkboard/chalkboard-trivia-overlay.png'></head>");
        out.print("<div style=height:5%;width:10%;float:top'>");
        out.print("<img src='http://www.smartlabtoys.com/media/chalkboard/chalkboard-trivia-overlay.png' style='width:300px;height:80px'></div>");
        out.print("<body style='background-color:lightgoldenrodyellow; font-family: Times New Roman; font-size:143%'>");
        out.print("<div style='font-size:25px;margin-top: -0.5cm;margin-right:1cm;float:right'>Hello, "+request.getParameter("fName")+" "+request.getParameter("lName")  +"</div>");
        out.print("<div style='color:darkgreen; font-size:150%;font-family: Times New Roman'>");
        out.print("<center><h1><b>More Games</b></h1></center></div>");
        out.print("<div style='background-color:darkseagreen;height:68%;width:17%;float:left;font-size:100%'>");
        out.print("<h1><b> Main Menu</b></h1>");
        out.print("<a href='AboutUs?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'> About us </a><br />");               
        out.print("<a href='MoreGames?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'>More games</a><br />");
        out.print("<a href='MainMenu?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'> Game main menu </a><br /></div>");
        out.print("<div style='margin-right:6cm'><center>");
        out.print("<a href='http://www.sporcle.com/'> Sporcle </a><br />");
        out.print("<a href='http://www.triviaplaza.com/'> Triviaplaza  </a><br />");
        out.print("<a href='http://www.youplay.com/games/list/online-trivia/'> You Play </a><br /></center></div></body></html>");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
