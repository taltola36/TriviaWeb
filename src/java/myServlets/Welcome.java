/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
public class Welcome extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("<title>Trivia Game</title><link rel='shortcut icon' href='http://www.smartlabtoys.com/media/chalkboard/chalkboard-trivia-overlay.png'>");
        out.print("<div style=height:5%;width:10%;float:top'>");
        out.print("<img src='http://www.smartlabtoys.com/media/chalkboard/chalkboard-trivia-overlay.png' style='width:300px;height:80px'></div>");

        if (request.getParameter("fName").equals(request.getParameter("lName")) || request.getParameter("fName").equals("") || request.getParameter("lName").equals("")) {    
            out.print("<body style='background-color:darkseagreen; font-family: Times New Roman; font-size:120%'>");
            out.print("<div style='color:red; font-size:170%;font-family: Times New Roman;'><center><h1><b>Error</b></h1></center></div>");
            out.print("<p><font size='5'><center>Something went wrong.<br> Please login with your first name and last name.</center></p>");
            out.print("<p><center><form action='index.jsp'><input type='submit' name='backToLogin' value='back' style='width:80px; font-size:16px'></form></center></p>");
        }
        else {        
            out.print("<body style='background-color:lightgoldenrodyellow; font-family: Times New Roman; font-size:110%'>");
            out.print("<div style='font-size:25px;margin-top: -0.5cm;margin-right:1cm;float:right'>Hello, "+request.getParameter("fName")+" "+request.getParameter("lName")  +"</div>");
//            if (request.getParameterMap().containsKey("Remember")) {
//                use cookies somehow to remember next time
//            }
            out.print("<div style='color:darkgreen; font-size:200%;font-family: Times New Roman;'><center><h1><b>Trivia Game</b></h1></center></div>");
            out.print("<div style='background-color:darkseagreen;height:70%;width:17%;float:left;font-size:130%'>");
            out.print("<h1><b> Main Menu</b></h1>");
            out.print("<a href='AboutUs?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'> About us </a><br />");               
            out.print("<a href='MoreGames?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'>More games</a><br />");
            out.print("<a href='MainMenu?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'> Game main menu </a><br /></div>");
            
            out.print("<div style='font-size:23px;margin-right:6cm'><center>Blah<br />   Blah blah<br />Blah blah blah <br />");

            out.print("<div style='background-color:darkgreen;font-size:75%;height:7%;clear:both'>");
            out.print("<center><h1>Copyright 2015 AvitalTal</h1></center></div></body></html>");
            //}
          
            
        }
                
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
