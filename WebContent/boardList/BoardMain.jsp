<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="dto.*"%>
<%@page import="dao.*"%>
<%
%>
<%
	ArrayList<BoardDto> listBoard = (ArrayList<BoardDto>) request.getAttribute("listBoard");
	ArrayList<BoardDto> listBoard2 = (ArrayList<BoardDto>) request.getAttribute("listBoard2");
	ArrayList<BoardDto> listbDto = (ArrayList<BoardDto>) request.getAttribute("listbDto");
	int pageNum = (int)request.getAttribute("pageNum");
	String id = (String) request.getSession().getAttribute("id");
	int cnt = (int)request.getAttribute("cnt");
	int cnt2 = 1;
	if(request.getAttribute("cnt2")!=null) {
		cnt2 = (int)request.getAttribute("cnt2");
	}
	MemberDto memberDto = (MemberDto)request.getAttribute("memberDto");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>메인페이지</title>
	<link rel="stylesheet" href="css/BoardMain.css" />
	<script src="js/jquery-3.7.0.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
	<script>
		$(function(){
			$("#writenow").click(function() {
				location.href="Controller?command=write";
			});
			$(".boardListTitle").click(function() {
				let num = $(this).parent().find(".boardBNO").text();
				location.href="Controller?command=view_update&bno="+num;
			});
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
			$("#allcheckbox").click(function(){
				 if ($(".checklistbox").prop("checked")) {
				    $(".checklistbox").prop("checked", false);
				  } else {
				    $(".checklistbox").prop("checked", true);
				  }
			});
			$(".boardDelete").click(function() {
				let num = $(this).parent().parent().find(".boardBNO").text();
				alert("삭제되었습니다.");
				location.href="Controller?command=board_delete&bno="+num;
			});
			$(".slider").bxSlider({
				mode: 'horizontal',
			    auto: true,	             
	        });
			$(".bx-viewport").click(function() {
				let href = $(this).find("a[aria-hidden=false]").attr("href");
				location.href= href;
			});
			
			$("#searchButton").click(function() {
				let searchContent = $(this).parents().find("#searchContent").val();
				
				$.ajax({
					type:'post',
					url:'SearchServlet',
					data : { "searchContent" : searchContent,
								"pageNum" : <%=pageNum%>	},
					datatype : "json",
					success: function(data) {
						var $table = $("#ajaxTable");
						$table.empty();
						
						for (var i = 0; i < data.result.length; i++) {
					      var $row = $("<tr></tr>");
					      $row.addClass("boardListContent");
					      let cnt = Number(0);
					      for (var j = 0; j < data.result[i].length; j++) {
					    	  cnt++;
					    	  if(cnt==2){
					    		  	var $cell = $("<td></td>");
							        $cell.html(data.result[i][j].value);
							        $cell.addClass("boardBNO");
							        $row.append($cell);
					    	  }else if(cnt==4){
						        	var $cell = $("<td></td>");
						       	 	$cell.html(data.result[i][j].value);
						       	 	$cell.addClass("boardListTitle");
						       	 	$row.append($cell);
					    	  }else {
					    		 	var $cell = $("<td></td>");
						       	 	$cell.html(data.result[i][j].value);
						       	 	$row.append($cell);
					    	  }
					      }
					      $table.prepend($row);
					    }
						$(".boardListTitle").click(function() {
							let num = $(this).parent().find(".boardBNO").text();
							location.href="Controller?command=view_update&bno="+num;
							location.href="Controller?command=board_content&bno="+num;
						});
						$(".boardDelete").click(function() {
							let num = $(this).parent().parent().find(".boardBNO").text();
							alert("삭제되었습니다.");
							location.href="Controller?command=board_delete&bno="+num;
						});
						$("#paging").css("display","none");
					  },
					 error: function (request, status, error) {
				        console.log("code: " + request.status)
				        console.log("message: " + request.responseText)
				        console.log("error: " + error);
				    }
				});
			});
			$("#searchContent").keyup(function() {
				let searchContent = $(this).parents().find("#searchContent").val();
				
				$.ajax({
					type:'post',
					url:'SearchServlet',
					data : { "searchContent" : searchContent,
								"pageNum" : <%=pageNum%>	},
					datatype : "json",
					success: function(data) {
						var $table = $("#ajaxTable");
						$table.empty();
						
						for (var i = 0; i < data.result.length; i++) {
						      var $row = $("<tr></tr>");
						      $row.addClass("boardListContent");
						      let cnt = Number(0);
						      for (var j = 0; j < data.result[i].length; j++) {
						    	  cnt++;
						    	  if(cnt==2){
						    		  	var $cell = $("<td></td>");
								        $cell.html(data.result[i][j].value);
								        $cell.addClass("boardBNO");
								        $row.append($cell);
						    	  }else if(cnt==4){
							        	var $cell = $("<td></td>");
							       	 	$cell.html(data.result[i][j].value);
							       	 	$cell.addClass("boardListTitle");
							       	 	$row.append($cell);
						    	  }else {
						    		 	var $cell = $("<td></td>");
							       	 	$cell.html(data.result[i][j].value);
							       	 	$row.append($cell);
						    	  }
						      }
						      $table.prepend($row);
						    }
							$(".boardListTitle").click(function() {
								let num = $(this).parent().find(".boardBNO").text();
								location.href="Controller?command=view_update&bno="+num;
								location.href="Controller?command=board_content&bno="+num;
							});
							$(".boardDelete").click(function() {
								let num = $(this).parent().parent().find(".boardBNO").text();
								alert("삭제되었습니다.");
								location.href="Controller?command=board_delete&bno="+num;
							});
							$("#paging").css("display","none");
					  },
					 error: function (request, status, error) {
				        console.log("code: " + request.status)
				        console.log("message: " + request.responseText)
				        console.log("error: " + error);
				    }
				});
			});
		});
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
		                <li><a style="text-decoration: underline; cursor:pointer;" id="loginButton"><%=id %></a></li>
		                <li><a href="Controller?command=Sign_up">SignUp</a></li>
					<%}else{%>
		                <li><a href="Controller?command=board_list">Home</a></li>
		                <li><a href="Controller?command=my_page">MyPage</a></li>
		                <li><a style="text-decoration: underline; cursor:pointer;"id="loginButton"><%=id %></a></li>
					<%}%>
	            </ul>
	        </nav>
    </header>
    <div style="margin-top:10px; text-align: center;">
    	<div class="slider" style="text-align: center;">
    		<%for(BoardDto bDto : listbDto) {%>
	    		<a href="Controller?command=board_content&bno=<%=bDto.getBno() %>" style="display:block;">
					<img style="margin: 0 auto; height: 400px;width: 400px;"src="Image/<%=bDto.getImage() %>">
	    		</a>
			<%} %>
	    </div>
    </div>
    <!-- 검색박스 -->
    <div class="search-box">
         <input type="text" name="search" id="searchContent" placeholder="검색할 제목을 입력하세요">
         <input type="button" id="searchButton" value="검색">
    </div>
    <!-- 글쓰기버튼 -->
	<div style="text-align: center;">
		<button class="writing" id="writenow" type="button">글쓰기</button>
	</div>
	<!-- 게시판 리스트 -->
	<div class="tablebox">
		<table style="margin: 0 auto; width:1000px;">
			<thead>
				<tr class="theadtr">
					<th style="width:50px;"><input id="allcheckbox" type="checkbox"></th>
					<th style="width:50px;">NO.</th>
					<% if(id!=null&&id.equals("manager")){%>
					<th style="width:150px;">ID</th>
					<% }else{%>
					<th style="width:150px;">NICKNAME</th>
					<% }%>
					<th>제목</th>
					<th style="width:100px;">작성일</th>
					<th style="width:60px;">조회수</th>
					<th style="width:60px;">좋아요</th>
					<th style="width:60px;">싫어요</th>
					<th style="width:50px;">삭제</th>
				</tr>
			</thead>
			<tbody id="ajaxTable">
			<% if(id!=null&&id.equals("manager")){
				for(BoardDto dto2 : listBoard2) {
				%>
				<tr class="boardListContent">
					<td><input class="checklistbox" type="checkbox"></td>
					<td class="boardBNO"><%=dto2.getBno() %></td>
					<td><%=dto2.getWriter() %></td>
					<td class="boardListTitle">
						<%if(dto2.getImage()!=null){%>
						<img style="width:30px; margin-right : 10px; vertical-align: middle;"src="Image/<%=dto2.getImage() %>">
						<%}%>
						<%=dto2.getTitle() %>
					</td>
					<td><%=dto2.getWritedate().substring(0, 10) %></td>
					<td><%=dto2.getViews() %></td>
					<td><%=dto2.getLikey() %></td>
					<td><%=dto2.getDislikey() %></td>
					<td><button type="button" class="boardDelete">삭제</button></td>
				</tr>
				<% } %>
			<% }else{ %>
				<%	
				for(BoardDto dto : listBoard) {
				%>
				<tr class="boardListContent">
					<td><input class="checklistbox" type="checkbox"></td>
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
					<td><% if(memberDto!=null&&memberDto.getNickname().equals(dto.getWriter())) { %>
						<button type="button" class="boardDelete">삭제</button>
					<%}else { %>
						
					<%} %></td>
				</tr>
				<% }
				} %>
			</tbody>
		</table>
			<!-- 페이지 -->
		<div id="paging" style="margin:10px auto; text-align: center;">
			<%if(id!=null&&id.equals("manager")){
				
			}else{
				if(cnt%20==0){ %>
			 		<%for(int i=1; i<=(cnt/20); i++) {
			 			if(i==pageNum){
			 				%><span><%=i %></span><%
			 			}else {
				 		%>
				 		<a class=pg href="Controller?command=board_list&pageNum=<%=i %>"><%=i %></a>
				 		<% }
			 		 } 
		 		}else{%>
		 			<%for(int i=1; i<=(cnt/20+1); i++) {
			 			if(i==pageNum){
			 				%><span><%=i %></span><%
			 			}else {
				 		%>
				 		<a class=pg href="Controller?command=board_list&pageNum=<%=i %>"><%=i %></a>
				 		<%}
		 		 		}
		 		}
			}
			%>
	 	</div>
	</div>
    <!-- 본문 내용 등 추가적인 내용을 작성할 수 있습니다. -->
</body>
</html>