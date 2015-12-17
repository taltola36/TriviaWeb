package myServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import TriviaWebLogic.TriviaGame;
import TriviaWebLogic.*;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author HP
 */
public class MainMenu extends HttpServlet {
    static TriviaGame TG = TriviaGame.loadFromFile();
    static Random rand = new Random();
    static ArrayList<String> askedQuestions = new ArrayList<String>();
    static boolean over = false;

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
        TriviaGame TG = TriviaGame.loadFromFile();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String fname = request.getParameter("fName");
        String lname = request.getParameter("lName");
        out.print("<html><head>");
        out.print("<title>Trivia Game</title><link rel='shortcut icon' href='http://www.smartlabtoys.com/media/chalkboard/chalkboard-trivia-overlay.png'>");
        
        out.print("<body style='background-color:lightgoldenrodyellow; font-family: Times New Roman; font-size:120%'>");
        out.print("<div style='height:5%;width:10%;float:top'>");
        out.print("<img src='http://www.smartlabtoys.com/media/chalkboard/chalkboard-trivia-overlay.png' style='width:300px;height:80px'></div>");
        out.print("<div style='font-size:25px;margin-top: -0.5cm;margin-right:1cm;float:right'>Hello, "+request.getParameter("fName")+" "+request.getParameter("lName")  +"</div>");
        out.print("<div style='background-color:lightgoldenrodyellow; font-family: Times New Roman; font-size:120%'>");
        out.print("<div style='color:darkgreen; font-size:150%;font-family: Times New Roman;'><center><h1><b>Trivia Game</b></h1></center></div>");
        
        out.print("<div style='background-color:darkseagreen;height:68%;width:16.7%;float:left;font-size:100%'>");
        out.print("<h1><b> Main Menu</b></h1>");
        out.print("<a href='AboutUs?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'> About us </a><br />");              
        out.print("<a href='MoreGames?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'>More games</a><br />");
        out.print("<a href='MainMenu?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'> Game main menu </a><br /></div>");       
        
        out.print("<div style='margin-right:6cm'><center>Choose one of the following:<br /><br>");
        out.print("<a href='PlayGame?fName="+fname+"&lName="+lname+"'> Start playing</a><br />");
        out.print("<a href='AddQuestion?fName="+fname+"&lName="+lname+"'> Add a new Trivia question</a><br />");
        out.print("<a href='DeleteQuestion?fName="+fname+"&lName="+lname+"'>Delete a Trivia question</a><br />");
        out.print("<a href='SaveToFile?fName="+fname+"&lName="+lname+"'>Save a Trivia question to file</a><br /></center></div>");

        //WHY IS IT NOT WORKING? out.print("<div style='position: absolute; left: 20%; right: 19%; bottom: 5%;'><center><form action='Welcome'><input type='submit' name='backToWelcome' value='Back to Home Page'></form></center></div></body></html>");

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
