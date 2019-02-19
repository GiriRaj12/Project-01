<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<style>
.login{
  width: 360px;
  padding: 8% 0 0;
  margin: auto;
}
.form {
  position: realtive;
  z-index: 1;
  background: #FFFFFF;
  max-width: 360px;
  margin: 0 auto 100px;
  padding: 45px;
  text-align: center;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
}
.form input {
  font-family: "Roboto", sans-serif;
  outline: 0;
  background: #f2f2f2;
  width: 100%;
  border: 0;
  margin: 0 0 15px;
  padding: 15px;
  box-sizing: border-box;
  font-size: 14px;
}
.form button {
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background: #4CAF50;
  width: 100%;
  border: 0;
  padding: 15px;
  color: #FFFFFF;
  font-size: 14px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;
}
</style>

<body>
<%
HttpSession ses=request.getSession(false);
String user=(String)ses.getAttribute("User");
String pass=(String)ses.getAttribute("Pass");
if(user!=null&&pass!=null){
	response.sendRedirect("Check");
}
%>
<div class="login">
<div class="form">
<form action="login" method="post">
      <input type="text" name="username" placeholder="Email ID"/>
      <input type="password" name="password" placeholder="Password"/>
      <button>Login</button>
      <p class="message">Not registered? <a href="Register.jsp">Create an account</a></p>
      <p><a href="index.html">Home</a></p>
    </form>
  </div>
  </div>
</body>
</html>