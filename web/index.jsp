<%-- 
    Document   : newjsp
    Created on : 13/12/2015, 20:57:38
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Trivia Game</title>
        <link rel="shortcut icon" href="http://www.smartlabtoys.com/media/chalkboard/chalkboard-trivia-overlay.png">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body style="background-color:lightgoldenrodyellow; font-size:120%; font-family: Times New Roman">       
        <div style="background-color: lightgoldenrodyellow; height:5%;width:10%;float:top">
            <img src="http://www.smartlabtoys.com/media/chalkboard/chalkboard-trivia-overlay.png" style="width:300px;height:80px">
        </div>   
        <div style="color:darkgreen;font-size:180%; margin-top: -50px">
            <center>
                <h1><b>Trivia Game</b></h1>
            </center>
        </div>
        <div style="font-size: 130%"><center>
            <form action="Welcome">
                <input type="hidden" name="loginInfo" value="">
                <p><font size="5">Please log in<br>
                <br><input type="text" name="fName" value="" size="35" placeholder="First Name"><br>
                <input type="text" name="lName" value="" size="35" placeholder="Last Name"><br>
                     <input type="checkbox" name="Remember" value="RememberMe"><font size="3">Remember me
                     <br><input type="submit" name="Register" value="Register" style="width:80px">
            </form>
        </center></div>
    </body>
</html>
