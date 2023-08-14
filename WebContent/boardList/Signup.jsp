<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	boolean signupCheck = (boolean) request.getAttribute("signupCheck");
	String id = (String)request.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Sign Up</title>
	<link rel="stylesheet" href="css/Signup.css" />
	<script src="js/jquery-3.7.0.min.js"></script>
	<% if(!signupCheck){ %>
		<script>
			alert("<%=id%>는 있는 아이디입니다. 다시 회원가입 해주세요.");
			location.href="Controller?command=Sign_up";
		</script>
	<% }else if(signupCheck&&id!=null){ %>
		<script>
			alert("<%=id%>님, 회원가입되었습니다. 로그인 해주세요.");
			location.href="Controller?command=Log_in";
		</script>
	<% } %>
</head>
<body>
	 <div class="signup-container">
        <h1>SignUp</h1>
        	<form action="Controller?" method="get">
        		<input type="hidden" name="command" value="Sign_up"/>
	            <div class="form-group">
	                <label for="username">ID</label>
	                <input type="text" id="id" name="id" placeholder="Enter your ID">
	            </div>
	            <div class="form-group">
	                <label for="password">Password</label>
	                <input type="password" id="password" name="pw" placeholder="Enter your password">
	            </div>
	            <div class="form-group">
	                <label for="name">Name</label>
	                <input type="text" id="name" name="name" placeholder="Enter your name">
	            </div>
	            <div class="form-group">
	                <label for="nickname">Nickname</label>
	                <input type="text" id="nickname" name="nickname" placeholder="Enter your nickname">
	            </div>
	            <div class="form-group">
	                <button id="signUp" type="submit">Sign Up</button>
	            </div>
        	</form>
    </div>
</body>
</html>