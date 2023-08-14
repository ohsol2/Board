<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="dto.*"%>
<%@page import="dao.*"%>
<%
 	String error = (String)request.getAttribute("LoginFail");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인</title>
	<link rel="stylesheet" href="css/Login.css" />
	<script src="js/jquery-3.7.0.min.js"></script>
</head>
<%=error==null?"":error %>

<body>
	  <div class="login-container">
        <h1>Login</h1>
        <form action="Controller?" method="get">
        	<input type="hidden" name="command" value="Log_in"/>
	        <div class="form-group">
	            <label for="username">ID</label>
	            <input type="text" name="id" id="id" placeholder="Enter your ID">
	        </div>
	        <div class="form-group">
	            <label for="password">Password</label>
	            <input type="password" name="pw" id="pw" placeholder="Enter your password">
	        </div>
	        <div class="form-group">
	            <button id="logIn" type="submit">Login</button>
	        </div>
        </form>
    </div>
</body>
</html>
