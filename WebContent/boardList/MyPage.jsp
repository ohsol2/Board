<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="dto.*"%>
<%@page import="dao.*"%>
<%
		ArrayList<BoardDto> ListDto = (ArrayList<BoardDto>)request.getAttribute("ListDto");
		MemberDto memberDto = (MemberDto)request.getAttribute("memberDto");	
		int pageNum = (int)request.getAttribute("pageNum");
		String id = (String)session.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>마이페이지</title>
	<link rel="stylesheet" href="css/BoardMain.css" />
	<script src="js/jquery-3.7.0.min.js"></script>
	<script>
		$(document).on('click','.boardListTitle',function() {
			let num = $(this).parent().find(".boardBNO").text();
			location.href="Controller?command=board_content&bno="+num;
		});
		$(function(){
			$("#writenow").click(function() {
				location.href="BoardWrite.jsp";
			});
			$("#loginButton").click(function() {
				if($(this).text()=="LogOut") {
					location.href="Controller?command=Log_out";
				}else {
					location.href="Controller?command=Log_in";
				}
			});
			$(".boardDelete").click(function() {
				let num = $(this).parent().parent().find(".boardBNO").text();
				alert("삭제되었습니다.");
				location.href="Controller?command=board_delete&bno="+num;
			});
			<%if(id==null){%>
				$("#loginButton").text("LogIn");
			<%}else{%>
				$("#loginButton").text("LogOut");
			<%}%>
			$("#writenow").click(function() {
				location.href="Controller?command=write";
			});
			
			$(window).scroll(function(){
				  var scrT = $(window).scrollTop();
				  console.log(scrT); //스크롤 값 확인용
				  if(scrT == $(document).height() - $(window).height()){
				  	//스크롤이 끝에 도달했을때 실행될 이벤트
				  	request_one_page();
				  }
			});
		});
		let pageNum = 1;
		function request_one_page() {
			pageNum += 1;
			$.ajax({
				type:'get',
				url:'MyBoardServlet',
				data : {'pageNum' : pageNum },
				dataType : 'json',
				success : function(data) {
					//console.log(data);
					let imageHtml = null;
					for (var i = 0; i < data.length; i++) {
						if(data[i].image!=null){
							imageHtml = "<td class='boardListTitle'><img style='width:30px; margin-right : 10px; vertical-align: middle;' src='Image/" +data[i].image+"'>"
				      			+ data[i].title+ "</td>";
			      		}else{
			      			imageHtml = "<td class='boardListTitle'>"+ data[i].title+ "</td>";
			      		}
					    let str = "<tr class='boardListContent'>"
					      		+ "<td>" + "<input type='checkbox'>" + "</td>"
					      		+ "<td class='boardBNO'>" + data[i].bno + "</td>"
					      		+ "<td>" + data[i].writer + "</td>"
					      		+ imageHtml
					      		+ "<td>" + data[i].writedate.substring(0,10) + "</td>"
					      		+ "<td>" + data[i].views + "</td>"
					      		+ "<td>" + data[i].likey + "</td>"
					      		+ "<td>" + data[i].dislikey + "</td>"
					      		+ "<td>" + "<button type='button' class='boardDelete'>삭제</button>"  + "</td>"
					      		+ "</tr>";
					    $("#table1").append(str);
					}
				},
				error: function (request, status, error) {
				        console.log("code: " + request.status)
				        console.log("message: " + request.responseText)
				        console.log("error: " + error);
				}
			
			});
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
    <!-- 글쓰기버튼 -->
	<div style="text-align: center;">
		<button class="writing" id="writenow" type="button">글쓰기</button>
	</div>
	<!-- 게시판 리스트 -->
	<div class="tablebox">
		<table id="table1" style="margin: 0 auto; width:1000px;">
			<tr class="theadtr">
				<th style="width:50px;">전체</th>
				<th style="width:50px;">글번호</th>
				<th style="width:150px;">닉네임</th>
				<th>제목</th>
				<th style="width:100px;">작성일</th>
				<th style="width:50px;">조회수</th>
				<th style="width:50px;">좋아요</th>
				<th style="width:50px;">싫어요</th>
				<th style="width:50px;">삭제</th>
			</tr>
			<%
				for(BoardDto dto : ListDto) {
			%>
			<tr class="boardListContent">
				<td><input type="checkbox"></td>
				<td class="boardBNO"><%=dto.getBno() %></td>
				<td><%=dto.getWriter() %></td>
				<td class="boardListTitle">
					<%if(dto.getImage()!=null){%>
						<img style="width:30px; margin-right : 10px; vertical-align: middle;"src="Image/<%=dto.getImage() %>">
					<%}%>
					<%=dto.getTitle() %>
				</td>
				<td><%=dto.getWritedate().substring(0, 10) %></td>
				<td><%=dto.getViews() %></td>
				<td><%=dto.getLikey() %></td>
				<td><%=dto.getDislikey() %></td>
				<td><% if(memberDto.getNickname().equals(dto.getWriter())) { %>
					<button type="button" class="boardDelete">삭제</button>
				<%}else { %>
					
				<%} %></td>
			</tr>
			<% } %>
		</table>
	</div>

    <!-- 본문 내용 등 추가적인 내용을 작성할 수 있습니다. -->
</body>
</html>