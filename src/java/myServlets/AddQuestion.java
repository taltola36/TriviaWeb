
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
public class AddQuestion extends HttpServlet {
    private String type;
    private int numberOfOptions; 
    public void init() throws ServletException {
       type = "NULL";
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie firstName = new Cookie("fName",request.getParameter("fName"));
        Cookie lastName = new Cookie("lName",request.getParameter("lName"));
        firstName.setMaxAge(60 * 60 * 24);
        lastName.setMaxAge(60 * 60 * 24);
        response.addCookie(firstName);
        response.addCookie(lastName);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("<html><head>");
        out.print("<title>Trivia Game</title><link rel='shortcut icon' href='http://www.smartlabtoys.com/media/chalkboard/chalkboard-trivia-overlay.png'>");

        
        if(!type.equals("NULL") && request.getParameterMap().containsKey("addFromCategory")!=false)         
            type = "okCategory";
        else
            type = "NULL";  //make sure that refresh doesn't change output

        if(type.equals("NULL") && request.getParameterMap().containsKey("addQuestion")!=false)         
            type = "addQuestion";

        if(type.equals("NULL") && request.getParameterMap().containsKey("TypeQuestion")!=false)         
           type = "TypeQuestion";  

        if(type.equals("NULL") && request.getParameterMap().containsKey("difficultyLevel")!=false)         
           type = "difficultyLevel";  
       
        if(type.equals("NULL") && request.getParameterMap().containsKey("AddRightAnswer")!=false)         
           type = "AddRightAnswer";  

        if (request.getParameterMap().containsKey("backToDifficultyLevel")) {
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
        
            out.print("<div style='margin-right:6cm'><center>Choose your question's difficulty level<br><br>");
            out.print("<form><input type='radio' name='difficultLevel' value='easy'>Easy<br>");
            out.print("<input type='radio' name='difficultLevel' value='medium'>Medium<br>");
            out.print("<input type='radio' name='difficultLevel' value='hard'>Hard<br>");
            out.print("<br><input type='submit' name='difficultyLevel' value='Add'><br></form></center></div>");    
            if (type.equals("NULL"))
                type = "Nothing";        
        }
        
        if (request.getParameterMap().containsKey("backToTypeQuestion")) {
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
        
            out.print("<div style='margin-right:6cm'><center>Choose your question's type<br><br>");
            out.print("<form><input type='radio' name='questionType' value='open'>Open question<br>");
            out.print("<input type='radio' name='questionType' value='multi'>Multiple options question<br>");
            out.print("<input type='radio' name='questionType' value='yesNo'>Yes or no questions<br>");
            out.print("<br><input type='submit' name='TypeQuestion' value='Add'><br></form></center></div>");         
            if (type.equals("NULL"))
                type = "Nothing";
        }
        boolean flag=false;
        if (request.getParameterMap().containsKey("AddMultiAnswers")) {
            if (request.getParameterMap().containsKey("Otheranswers0"))
                for (int i=0; i<numberOfOptions-1; i++)
                    if (request.getParameter("Otheranswers"+i).equals(""))
                        flag = true;
            if (request.getParameter("Rightanswer").equals("") || flag) {
                out.print("<body style='background-color:darkseagreen; font-family: Times New Roman; font-size:130%; height:100%; width:100%'>");
                out.print("<div style='height:5%;width:10%;float:top'>");
                out.print("<img src='http://www.smartlabtoys.com/media/chalkboard/chalkboard-trivia-overlay.png' style='width:300px;height:80px'></div>");
                out.print("<body><div style='font-size:25px;margin-top: -0.5cm;margin-right:1cm;float:right'>Hello, "+request.getParameter("fName")+" "+request.getParameter("lName")  +"</div>");
                out.print("<div style='color:red; font-size:170%;font-family: Times New Roman;'><center><h1><b>Error</b></h1></center></div>");
                out.print("<div style='background-color:lightgoldenrodyellow;height:68%;width:16.7%;float:left;font-size:100%'>");
                out.print("<h1><b> Main Menu</b></h1>");
                out.print("<a href='AboutUs?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'> About us </a><br />");              
                out.print("<a href='MoreGames?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'>More games</a><br />");
                out.print("<a href='MainMenu?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'> Game main menu </a><br /></div>");       
        
                out.print("<div style='margin-right:6cm'><font size='5'><center>Something went wrong.<br> Please type all the answers.</center></div>");
                //numberOfOptions = numberOfOptions; //so next Servlet page gets the value, not null
                out.print("<br><div style='margin-right:6cm'><center><form><input type='submit' name='backToMultiAnswers' value='back to typing answers' style='width:5cm; font-size:13px'>"
                        + "<input type='submit' name='backToNumberOfOptions' value='change number of answers' style='width:6cm; font-size:13px'></form></center></div>");
            }
            if (!flag && !request.getParameter("Rightanswer").equals("")) {
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
                out.print("<div style='margin-right:6cm'><center>Your question has been successfully added<br>");
                out.print("<form><br><input type='submit' name='addAnother' value='Add another question'><br></form></center></div>");                       
            }
            type = "Nothing";
        }
        if (request.getParameterMap().containsKey("backToMultiAnswers")) {
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
        
            out.print("<div style='margin-right:6cm'><center>Type the correct answer<br><form>");
            out.print("<br>1. <input type='text' name='Rightanswer' style='width:10cm'><br>");                
            out.print("<br>Type the other answers<br><br><form>");
            
            for (int i=0; i<numberOfOptions-1; i++)
                out.print(i+2+". <input type='text' name='Otheranswers"+i+"' style='width:10cm'><br>");
                
            out.print("<br><br><input type='submit' name='AddMultiAnswers' value='Add'>    <input type='submit' name='backToNumberOfOptions' value='change number of answers' style='width:5cm'><br>");
            out.print("</form></center></div>");
            type = "Nothing";
        }
        if (request.getParameterMap().containsKey("AddNumberOfOptions") ) {
            if (request.getParameter("numberOfOptions").equals("")) {
                out.print("<body style='background-color:darkseagreen; font-family: Times New Roman; font-size:130%; height:100%; width:100%'>");
                out.print("<div style='height:5%;width:10%;float:top'>");
                out.print("<img src='http://www.smartlabtoys.com/media/chalkboard/chalkboard-trivia-overlay.png' style='width:300px;height:80px'></div>");
                out.print("<body><div style='font-size:25px;margin-top: -0.5cm;margin-right:1cm;float:right'>Hello, "+request.getParameter("fName")+" "+request.getParameter("lName")  +"</div>");
                out.print("<div style='color:red; font-size:170%;font-family: Times New Roman;'><center><h1><b>Error</b></h1></center></div>");
                out.print("<div style='background-color:lightgoldenrodyellow;height:68%;width:16.7%;float:left;font-size:100%'>");
                out.print("<h1><b> Main Menu</b></h1>");
                out.print("<a href='AboutUs?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'> About us </a><br />");              
                out.print("<a href='MoreGames?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'>More games</a><br />");
                out.print("<a href='MainMenu?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'> Game main menu </a><br /></div>");       
        
                out.print("<div style='margin-right:6cm'><font size='5'><center>Something went wrong.<br> Please type the number of answers you have.</center></div>");
                out.print("<br><div style='margin-right:6cm'><center><form><input type='submit' name='backToNumberOfOptions' value='back' style='width:80px; font-size:16px'></form></center></div>");
                type = "Nothing";
            }
            else {
                out.print("<body style='background-color:lightgoldenrodyellow; font-family: Times New Roman; font-size:120%'>");
                out.print("<div style='height:5%;width:10%;float:top'>");
                out.print("<img src='http://www.smartlabtoys.com/media/chalkboard/chalkboard-trivia-overlay.png' style='width:300px;height:80px'></div>");
                out.print("<div style='font-size:25px;margin-top: -0.5cm;margin-right:1cm;float:right'>Hello, "+request.getParameter("fName")+" "+request.getParameter("lName")  +"</div>");
                out.print("<div style='background-color:lightgoldenrodyellow; font-family: Times New Roman; font-size:120%'>");
                out.print("<div style='color:darkgreen; font-size:150%;font-family: Times New Roman;'><center><h1><b>Trivia Game</b></h1></center></div>");
                out.print("<div style='background-color:darkseagreen;height:150%;width:16.7%;float:left;font-size:100%'>");
                out.print("<h1><b> Main Menu</b></h1>");
                out.print("<a href='AboutUs?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'> About us </a><br />");              
                out.print("<a href='MoreGames?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'>More games</a><br />");
                out.print("<a href='MainMenu?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'> Game main menu </a><br /></div>");       
        
                out.print("<div style='margin-right:6cm'><center>Type the correct answer<br><form>");
                out.print("<br>1. <input type='text' name='Rightanswer' style='width:10cm'><br>");                
                out.print("<br>Type the other answers<br><br>");
                numberOfOptions = Integer.parseInt(request.getParameter("numberOfOptions"));
                for (int i=0; i<Integer.parseInt(request.getParameter("numberOfOptions"))-1; i++)
                    out.print(i+2+". <input type='text' name='Otheranswers"+i+"' style='width:10cm'><br>");                  
               
                out.print("<br><input type='submit' name='AddMultiAnswers' value='Add'>   <input type='submit' name='backToNumberOfOptions' value='change number of answers' style='width:5cm'><br>");
                out.print("</form></center></div>");
                type = "Nothing";
            }
        }
        
        if (type.equals("AddRightAnswer")) {
            if (request.getParameter("Rightanswer").equals("")){
                out.print("<body style='background-color:darkseagreen; font-family: Times New Roman; font-size:130%; height:100%; width:100%'>");
                out.print("<div style='height:5%;width:10%;float:top'>");
                out.print("<img src='http://www.smartlabtoys.com/media/chalkboard/chalkboard-trivia-overlay.png' style='width:300px;height:80px'></div>");
                out.print("<body><div style='font-size:25px;margin-top: -0.5cm;margin-right:1cm;float:right'>Hello, "+request.getParameter("fName")+" "+request.getParameter("lName")  +"</div>");
                out.print("<div style='color:red; font-size:170%;font-family: Times New Roman;'><center><h1><b>Error</b></h1></center></div>");
                out.print("<div style='background-color:lightgoldenrodyellow;height:68%;width:16.7%;float:left;font-size:100%'>");
                out.print("<h1><b> Main Menu</b></h1>");
                out.print("<a href='AboutUs?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'> About us </a><br />");              
                out.print("<a href='MoreGames?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'>More games</a><br />");
                out.print("<a href='MainMenu?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'> Game main menu </a><br /></div>");       
        
                out.print("<div style='margin-right:6cm'><font size='5'><center>Something went wrong.<br> Please type the right answer to your question.</center></div>");
                out.print("<br><div style='margin-right:6cm'><center><form><input type='submit' name='backToRightAnswer' value='back' style='width:80px; font-size:16px'></form></center></p>");
            }
            else {
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
        
                out.print("<div style='margin-right:6cm'><center>Your question has been successfully added<br>");
                out.print("<form><br><input type='submit' name='addAnother' value='Add another question'><br></form></center></div>");             
            }            
        }
        
        if (type.equals("TypeQuestion") || request.getParameterMap().containsKey("backToNumberOfOptions") || request.getParameterMap().containsKey("backToRightAnswer") || request.getParameterMap().containsKey("backToNumberOfOptions")) {
            if (!request.getParameterMap().containsKey("questionType") && type.equals("TypeQuestion")){
                out.print("<body style='background-color:darkseagreen; font-family: Times New Roman; font-size:130%; height:100%; width:100%'>");
                out.print("<div style='height:5%;width:10%;float:top'>");
                out.print("<img src='http://www.smartlabtoys.com/media/chalkboard/chalkboard-trivia-overlay.png' style='width:300px;height:80px'></div>");
                out.print("<body><div style='font-size:25px;margin-top: -0.5cm;margin-right:1cm;float:right'>Hello, "+request.getParameter("fName")+" "+request.getParameter("lName")  +"</div>");
                out.print("<div style='color:red; font-size:170%;font-family: Times New Roman;'><center><h1><b>Error</b></h1></center></div>");
                out.print("<div style='background-color:lightgoldenrodyellow;height:68%;width:16.7%;float:left;font-size:100%'>");
                out.print("<h1><b> Main Menu</b></h1>");
                out.print("<a href='AboutUs?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'> About us </a><br />");              
                out.print("<a href='MoreGames?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'>More games</a><br />");
                out.print("<a href='MainMenu?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'> Game main menu </a><br /></div>");       
        
                out.print("<div style='margin-right:6cm'><font size='5'><center>Something went wrong.<br> Please choose one option.</center></div>");
                out.print("<br><div style='margin-right:6cm'><center><form><input type='submit' name='backToTypeQuestion' value='back' style='width:80px; font-size:16px'></form></center></div>");
            }
             else {
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
        
                if (request.getParameterMap().containsKey("backToNumberOfOptions") || (request.getParameterMap().containsKey("questionType") && request.getParameter("questionType").equals("multi"))) {
                    out.print("<div style='margin-right:6cm'><center><form>Type the number of answers you have.<br><br><input type='text' style='width:2cm' name='numberOfOptions'><br><br>");             
                    out.print("<br><input type='submit' name='AddNumberOfOptions' value='Add'><br></form></center></div>");
                }
                else {
                    out.print("<div style='margin-right:6cm'><center>Type the correct answer<br>");
                    out.print("<form><br><input type='text' name='Rightanswer' style='width:10cm'><br>");
                    out.print("<br><input type='submit' name='AddRightAnswer' value='Add'><br></form></center></div>");
                }
                    type = "Nothing";
            }
            if (request.getParameterMap().containsKey("backToRightAnswer"))
                type = "AddRightAnswer";
        }

//        out.print("<div><center><form>Type the number of answers you have.<br><br><input type='submit' style='width:2cm' name='numberOfOptions'><br><br></form></center></div>");
//        if (request.getParameterMap().containsKey("numberOfOptions"))
//            out.print("AVITAL");

        if (type.equals("difficultyLevel")) {
            if (!request.getParameterMap().containsKey("difficultLevel") && type.equals("difficultyLevel")){
                out.print("<body style='background-color:darkseagreen; font-family: Times New Roman; font-size:130%; height:100%; width:100%'>");
                out.print("<div style='height:5%;width:10%;float:top'>");
                out.print("<img src='http://www.smartlabtoys.com/media/chalkboard/chalkboard-trivia-overlay.png' style='width:300px;height:80px'></div>");
                out.print("<body><div style='font-size:25px;margin-top: -0.5cm;margin-right:1cm;float:right'>Hello, "+request.getParameter("fName")+" "+request.getParameter("lName")  +"</div>");
                out.print("<div style='color:red; font-size:170%;font-family: Times New Roman;'><center><h1><b>Error</b></h1></center></div>");
                out.print("<div style='background-color:lightgoldenrodyellow;height:68%;width:16.7%;float:left;font-size:100%'>");
                out.print("<h1><b> Main Menu</b></h1>");
                out.print("<a href='AboutUs?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'> About us </a><br />");              
                out.print("<a href='MoreGames?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'>More games</a><br />");
                out.print("<a href='MainMenu?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'> Game main menu </a><br /></div>");       
        
                out.print("<div style='margin-right:6cm'><font size='5'><center>Something went wrong.<br> Please choose one option.</center></div>");
                out.print("<br><div style='margin-right:6cm'><center><form><input type='submit' name='backToDifficultyLevel' value='back' style='width:80px; font-size:16px'></form></center></div>");
            }
            else {
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
        
                out.print("<div style='margin-right:6cm'><center>Choose your question's type<br><br>");
                out.print("<form><input type='radio' name='questionType' value='open'>Open question<br>");
                out.print("<input type='radio' name='questionType' value='multi'>Multiple options question<br>");
                out.print("<input type='radio' name='questionType' value='yesNo'>Yes or no questions<br>");
                out.print("<br><input type='submit' name='TypeQuestion' value='Add'><br></form></center></div>");         
            }
               if (request.getParameterMap().containsKey("backToTypeQuestion"))
                type = "TypeQuestion";
            
        }

        if (type.equals("addQuestion") ) {
            if (request.getParameter("question").equals("") && type.equals("addQuestion")){
                out.print("<body style='background-color:darkseagreen; font-family: Times New Roman; font-size:130%; height:100%; width:100%'>");
                out.print("<div style='height:5%;width:10%;float:top'>");
                out.print("<img src='http://www.smartlabtoys.com/media/chalkboard/chalkboard-trivia-overlay.png' style='width:300px;height:80px'></div>");
                out.print("<body><div style='font-size:25px;margin-top: -0.5cm;margin-right:1cm;float:right'>Hello, "+request.getParameter("fName")+" "+request.getParameter("lName")  +"</div>");
                out.print("<div style='color:red; font-size:170%;font-family: Times New Roman;'><center><h1><b>Error</b></h1></center></div>");
                out.print("<div style='background-color:lightgoldenrodyellow;height:68%;width:16.7%;float:left;font-size:100%'>");
                out.print("<h1><b> Main Menu</b></h1>");
                out.print("<a href='AboutUs?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'> About us </a><br />");              
                out.print("<a href='MoreGames?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'>More games</a><br />");
                out.print("<a href='MainMenu?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'> Game main menu </a><br /></div>");       
        
                out.print("<div style='margin-right:6cm'><font size='5'><center>Something went wrong.<br> Please type your question.</center></div>");
                out.print("<br><div style='margin-right:6cm'><center><form><input type='submit' name='backToQuestion' value='back' style='width:80px; font-size:16px'></form></center></div>");
            }
            else {
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
        
                out.print("<div style='margin-right:6cm'><center>Choose your question's difficulty level<br><br>");
                out.print("<form><input type='radio' name='difficultLevel' value='easy'>Easy<br>");
                out.print("<input type='radio' name='difficultLevel' value='medium'>Medium<br>");
                out.print("<input type='radio' name='difficultLevel' value='hard'>Hard<br>");
                out.print("<br><input type='submit' name='difficultyLevel' value='Add'><br></form></center></div>");         
            }   
         }
        
         if (type.equals("okCategory") || request.getParameterMap().containsKey("backToQuestion")) {   //then show add question page
            if (!request.getParameterMap().containsKey("category") && type.equals("okCategory")){
                out.print("<body style='background-color:darkseagreen; font-family: Times New Roman; font-size:130%; height:100%; width:100%'>");
                out.print("<div style='height:5%;width:10%;float:top'>");
                out.print("<img src='http://www.smartlabtoys.com/media/chalkboard/chalkboard-trivia-overlay.png' style='width:300px;height:80px'></div>");
                out.print("<body><div style='font-size:25px;margin-top: -0.5cm;margin-right:1cm;float:right'>Hello, "+request.getParameter("fName")+" "+request.getParameter("lName")  +"</div>");
                out.print("<div style='color:red; font-size:170%;font-family: Times New Roman;'><center><h1><b>Error</b></h1></center></div>");
                out.print("<div style='background-color:lightgoldenrodyellow;height:68%;width:16.7%;float:left;font-size:100%'>");
                out.print("<h1><b> Main Menu</b></h1>");
                out.print("<a href='AboutUs?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'> About us </a><br />");              
                out.print("<a href='MoreGames?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'>More games</a><br />");
                out.print("<a href='MainMenu?fName="+request.getParameter("fName")+"&lName="+request.getParameter("lName")+"'> Game main menu </a><br /></div>");       
        
                out.print("<div style='margin-right:6cm'><font size='5'><center>Something went wrong.<br> Please choose one option.</center></div>");
                out.print("<br><div style='margin-right:6cm'><center><form><input type='submit' name='backToCategory' value='back' style='width:80px; font-size:16px'></form></center></div>");
            }
            else {
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
        
                out.print("<div style='margin-right:6cm'><center>Type your question<br>");
                out.print("<form><br><input type='text' style='width:10cm' name='question'><br>");
                out.print("<br><input type='submit' name='addQuestion' value='Add'><br></form></center></div>");
            }
            if (request.getParameterMap().containsKey("backToQuestion"))
                type = "addQuestion";
         }
         if (type.equals("NULL") || request.getParameterMap().containsKey("backToCategory")) {     //then show category page
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
        
            out.print("<div style='margin-right:6cm'><center>Select the relevant categories you want to add an answer to<br /><br />");
            out.print("<form><input type='radio' name='category'>category1<br>");
            out.print("<input type='radio' name='category'>category2<br>");
            out.print("<br><input type='submit' name='addFromCategory' value='OK'><br></form>");
            type="NotNull"; //make sure occurs only once
        }
        out.print("</center></div></body></html>");
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    } 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
