<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="dto.*"%>
<%@page import="dao.*"%>
<%
	MemberDto memberDto = (MemberDto)request.getAttribute("memberDto");
	String id =  (String)request.getSession().getAttribute("id");
	boolean success = false;
	if(request.getAttribute("success")==null){
		success = false;
	}else {
		success = (boolean)request.getAttribute("success");
	}
	BoardDto content = (BoardDto)request.getAttribute("content");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>글쓰기</title>
	<link rel="stylesheet" href="css/BoardWrite.css" />
	<script src="js/jquery-3.7.0.min.js"></script>
	<%if(success){ %>
	<script>
		alert("<%=id%>님, 게시글이 수정되었습니다.");
		location.href="Controller?command=board_list";
	</script>
	<%}%>
	<script>
		$(function(){
			$("#loginButton").click(function() {
				if($(this).text()=="LogOut") {
					location.href="Controller?command=Log_out";
				}else {
					location.href="Controller?command=Log_in";
				}
			});
			<%if(id==null){%>
				$("#loginButton").text("LogIn");
			<%}else{%>
				$("#loginButton").text("LogOut");
			<%}%>
		});
	</script>
	<script type="text/javascript" src="smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
	<script>
	   let oEditors = [];
	
	   smartEditor = function() {
	      console.log("Naver SmartEditor");
	      nhn.husky.EZCreator.createInIFrame({
	         oAppRef: oEditors,
	         elPlaceHolder: "editorTxt",
	         sSkinURI: "smarteditor/SmartEditor2Skin.html",
	         fCreator: "createSEditor2"
	      });
	   };
	
	   $(document).ready(function() {
	      smartEditor();
	
	      $("#submitBtn").click(function(e) {
	         e.preventDefault(); // 기본 제출 동작 방지
	         submitPost();
	      });
	   });
	
	   function submitPost() {
	      oEditors.getById["editorTxt"].exec("UPDATE_CONTENTS_FIELD", []);
	
	      let content = oEditors.getById["editorTxt"].getIR();
	      if (content === "") {
	         alert("내용을 입력해주세요.");
	         oEditors.getById["editorTxt"].exec("FOCUS");
	         return;
	      }
	
	      // 숨겨진 필드에 HTML 내용 설정
	      $("#content").val(content);
	
	      // 폼 제출
	      $("#writeForm").submit();
	   }
	</script>
</head>
<body>
	  <!-- 로고와 메뉴바 -->
    <header>
        <div id="logo"><a href="Controller?command=board_list">BOARD</a></div>
        <nav>
            <ul>
                <%if(id==null){%>
		           	<li><a href="Controller?command=board_list">Home</a></li>
	                <li><a style="text-decoration: underline; cursor:pointer;"id="loginButton"><%=id %></a></li>
	                <li><a href="Controller?command=Sign_up">SignUp</a></li>
				<%}else{%>
	                <li><a href="Controller?command=board_list">Home</a></li>
	                <li><a href="Controller?command=my_page">MyPage</a></li>
	                <li><a style="text-decoration: underline; cursor:pointer;"id="loginButton"><%=id %></a></li>
				<%}%>
            </ul>
        </nav>
    </header>
	<!-- 게시판 리스트 -->
	<div class="tablebox">
		<form id="writeForm" action="UpdateServlet" method="post"  enctype="multipart/form-data">
			<input type="hidden" name="id" value="<%=id%>">
			<input type="hidden" name="bno" value="<%=content.getBno()%>">
			<table style="margin: 20px auto; width:1000px;">
				<tr>
					<th style="width:100px;">닉네임</th>
					<td><%=memberDto.getNickname() %></td>
				</tr>
				<tr>
					<th style="width:100px;">제목</th>
					<td><input class="titleinput" type="text" name="title" value="<%=content.getTitle()%>"></td>
				</tr>
				<tr>
					<th style="width:100px; height : 100px;">내용</th>
					<td><div id="smarteditor">
							<textarea name="editorTxt" id="editorTxt" 
							rows="20" cols="10" placeholder="내용을 입력해주세요"
							style="width:880px;"><%=content.getContent() %></textarea>
						</div>
						<input type="hidden" id="content" name="content"></td>
				</tr>
				<tr>
					<th style="width:100px;">첨부이미지</th>
					<%if(content.getImage()==null){ %>
					<td><input type="file" name="image" value="<%=content.getImage()%>"></td>
					<%}else{ %>
					<td><input type="file" name="image" value="<%=content.getImage()%>"></td>
					<%} %>
				</tr>
				<tr>
					<th style="width:100px;">공개여부</th>
					<td>
						<select name="visibility">
							<option value="공개">공개</option>
							<option value="비공개">비공개</option>
						</select>
					</td>
				</tr>
			</table>
			<input class="writing" id="submitBtn" type="submit" value="수정완료"/>
		</form>
	</div>
    <!-- 본문 내용 등 추가적인 내용을 작성할 수 있습니다. -->
</body>
</html>