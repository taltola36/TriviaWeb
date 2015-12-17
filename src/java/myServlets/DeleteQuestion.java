/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
public class DeleteQuestion extends HttpServlet {
    private String type;   //למה לא ניתן לעשות רענון-שגיאה
    private String firstName,lastName;

    public void init() throws ServletException {
       type = "NULL";
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
//        Cookie firstName = new Cookie("fName",request.getParameter("fName"));
//        Cookie lastName = new Cookie("lName",request.getParameter("lName"));
//        firstName.setMaxAge(60 * 60 * 24);
//        lastName.setMaxAge(60 * 60 * 24);
//        response.addCookie(firstName);
//        response.addCookie(lastName);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.print("<html><head>");
        out.print("<title>Trivia Game</title><link rel='shortcut icon' href='http://www.smartlabtoys.com/media/chalkboard/chalkboard-trivia-overlay.png'><head>");
       
        if(!type.equals("NULL") && request.getParameterMap().containsKey("deleteQuestion")!=false)         
            type = "delete";
        else
            type = "NULL";  //make sure that refresh doesn't change output

        if (type.equals("delete")) {
            if (!request.getParameterMap().containsKey("q")){
                out.print("<body style='background-color:darkseagreen; font-family: Times New Roman; font-size:130%'>");
                out.print("<div style='height:5%;width:10%;float:top'>");
                out.print("<img src='http://www.smartlabtoys.com/media/chalkboard/chalkboard-trivia-overlay.png' style='width:300px;height:80px'></div>");
                out.print("<body><div style='font-size:25px;margin-top: -0.5cm;margin-right:1cm;float:right'>Hello, "+
                        request.getParameter("fname")+" "+request.getParameter("lname")  +"</div>");
                out.print("<div style='color:red; font-size:170%;font-family: Times New Roman;'><center><h1><b>Error</b></h1></center></div>");
                out.print("<div style='background-color:lightgoldenrodyellow;height:67%;width:16.7%;float:left;font-size:110%'>");
                out.print("<h1><b> Main Menu</b></h1>");
                out.print("<a href='AboutUs?fName="+request.getParameter("fname")+
                        "&lName="+request.getParameter("lname")+"'> About us </a><br />");              
                out.print("<a href='MoreGames?fName="+request.getParameter("fname")+
                        "&lName="+request.getParameter("lname")+"'>More games</a><br />");
                out.print("<a href='MainMenu?fName="+request.getParameter("fname")+
                        "&lName="+request.getParameter("lname")+"'> Game main menu </a><br /></div>");       
        
                out.print("<div style='margin-right:6cm'><font size='5'><center>Something went wrong.<br> Please choose one option.</center></div>");
                out.print("<div style='margin-right:6cm'><center><form><br>"
                        + "<input type='hidden' name='fname' value='"+request.getParameter("fname")+"'>"
                        + "<input type='hidden' name='lname' value='"+request.getParameter("lname")+"'>"
                        + "<input type='submit' name='backToLogin' value='back' style='width:80px; font-size:16px'></form></center></div>");
            }  
            else {
                out.println("<body style='background-color:lightgoldenrodyellow; font-family: Times New Roman; font-size:143%'>");
                out.print("<div style='height:5%;width:10%;float:top'>");
                out.print("<img src='http://www.smartlabtoys.com/media/chalkboard/chalkboard-trivia-overlay.png' style='width:300px;height:80px'></div>");
                out.print("<div style='font-size:25px;margin-top: -0.5cm;margin-right:1cm;float:right'>Hello, "+
                        request.getParameter("fname")+" "+request.getParameter("lname")+"</div>");
                out.print("<div style='color:darkgreen; font-size:150%;font-family: Times New Roman;'><center><h1><b>Trivia Game</b></h1></center></div>");
                out.print("<div style='background-color:darkseagreen;height:68%;width:16.7%;float:left;font-size:100%'>");
                out.print("<h1><b> Main Menu</b></h1>");
                out.print("<a href='AboutUs?fName="+request.getParameter("fname")+
                        "&lName="+request.getParameter("lname")+"'> About us </a><br />");              
                out.print("<a href='MoreGames?fName="+request.getParameter("fname")+
                        "&lName="+request.getParameter("lname")+"'>More games</a><br />");
                out.print("<a href='MainMenu?fName="+request.getParameter("fname")+
                        "&lName="+request.getParameter("lname")+"'> Game main menu </a><br /></div>");       
                out.print("<div style='margin-right:6cm'><font size='5'><center>The question was successfully deleted<br /><br />");
                out.print("<form><input type='hidden' name='fname' value='"+request.getParameter("fname")+"'>"
                        + "<input type='hidden' name='lname' value='"+request.getParameter("lname")+"'>"
                        + "<input type='submit' name='deleteAnother' value='Delete another question'><br></form></center></div>");         
             }
        }
        if (type.equals("NULL")) {     //then show category page
            firstName = request.getParameter("fName");
            lastName = request.getParameter("lName");
         
            if (firstName==null && lastName==null)  {
                firstName = request.getParameter("fname");
                lastName = request.getParameter("lname");
            }

            out.println("<body style='background-color:lightgoldenrodyellow; font-family: Times New Roman; font-size:143%'>");
            out.print("<div style='height:5%;width:10%;float:top'>");
            out.print("<img src='http://www.smartlabtoys.com/media/chalkboard/chalkboard-trivia-overlay.png' style='width:300px;height:80px'></div>");
            out.print("<div style='font-size:25px;margin-top: -0.5cm;margin-right:1cm;float:right'>Hello, "
                    +firstName+" "+lastName+"</div>");
            out.print("<div style='color:darkgreen; font-size:150%;font-family: Times New Roman;'><center><h1><b>Trivia Game</b></h1></center></div>");
            out.print("<div style='background-color:darkseagreen;height:68%;width:16.7%;float:left;font-size:100%'>");
            out.print("<h1><b> Main Menu</b></h1>");
            out.print("<a href='AboutUs?fName="+firstName+"&lName="+lastName+"'> About us </a><br />");              
            out.print("<a href='MoreGames?fName="+firstName+"&lName="+lastName+"'>More games</a><br />");
            out.print("<a href='MainMenu?fName="+firstName+"&lName="+lastName+"'> Game main menu </a><br /></div>");       
        
            out.print("<div style='margin-right:6cm'><center>Select the question you want to delete<br /><br />");
            out.print("<form><input type='radio' name='q'>1<br>");
            out.print("<input type='radio' name='q'>2<br>");
            out.print("<input type='radio' name='q'>3<br>");
            out.print("<input type='radio' name='q'>4<br>");
            out.print("<input type='hidden' name='fname' value='"+firstName+"'>");
            out.print("<input type='hidden' name='lname' value='"+lastName+"'>");
            out.print("<br><input type='submit' name='deleteQuestion' value='delete'><br></form></center></div>");
            type="NotNull"; //make sure occurs only once
        }
        out.print("</body></html>");
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
