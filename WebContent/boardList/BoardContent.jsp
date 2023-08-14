<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="dto.*"%>
<%@page import="dao.*"%>
<%
	BoardDto content = (BoardDto) request.getAttribute("content");
	ArrayList<ReplyDto> replyDto = (ArrayList<ReplyDto>) request.getAttribute("replyDto");
	String id = (String)request.getSession().getAttribute("id");
	ReplyDao replyDao = new ReplyDao();
	
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>글번호<%=content.getBno() %></title>
	<link rel="stylesheet" href="css/BoardContent.css" />
	<link rel="stylesheet" href="css/BoardMain.css" />
	<script src="js/jquery-3.7.0.min.js"></script>
	<script>
		$(function(){
			$("#loginButton").click(function() {
				if($(this).text()=="LogOut") {
					location.href="Controller?command=Log_out";
				}else {
					location.href="Controller?command=Log_in";
				}
			});
			$("#likey").click(function() {
				location.href="Controller?command=likey_update";
			});
			$("#dislikey").click(function() {
				location.href="Controller?command=dislikey_update";
			});
			<%if(id==null){%>
				$("#loginButton").text("LogIn");
			<%}else{%>
				$("#loginButton").text("LogOut");
			<%}%>
			$(".rereplybtn").click(function() {
				let num = $(this).attr("id");
				if($("#div" + num).css("display")=="none"){
					$("#div" + num).css("display", "block");
				}else {
					$("#div" + num).css("display", "none");
				}
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
	<%if(id==null){%>
		<div class="board-container">
			<div class="post" style="text-align: center;">
				<h1>회원에게만 공개된 컨텐츠입니다. 로그인해주세요.</h1>
			</div>
		</div>
			<%}else{%>
	 <div class="board-container">
        <div class="post">
            <div class="title">[ NO.<%=content.getBno() %> ] <%=content.getTitle() %></div>
            <div class="meta" style="margin-bottom: 20px; float:left;">
                작성일: <%=content.getWritedate() %> / 작성자: <%=content.getWriter() %> / 조회수: <%=content.getViews()%>
            <div style="display: inline-block;">
	            <%if(content.getWriter().equals(id)){ %>
	            <form action="Controller?" method="get">
		            <input class="updating" type="submit" value="수정"/>
		            <input type="hidden" name="command" value="board_update">
		            <input type="hidden" name="bno" value="<%=content.getBno()%>">
	            </form>
	            <%} %>
            </div>
            </div>
            <div style="clear:both;"></div>
            <div>
	            <div class="content">
	                <img style="width:500px;"src="<%=content.getImage()==null?"":"Image/"+content.getImage() %>"/>
	            </div>
	            <div class="content">
	                <%=content.getContent() %>
	            </div>
	            <div class="fr" style="padding-right:30px;">
	               <div class="fl">
		            <form action="Controller?" method="get">
			            <input class="updating" type="submit" id="likey" value="좋아요 <%=content.getLikey()%>"/>
			            <input type="hidden" name="command" value="like">
			            <input type="hidden" name="bno" value="<%=content.getBno()%>">
		            </form>
	               </div>
	               <div class="fr">
		            <form action="Controller?" method="get">
			            <input class="updating" type="submit" id="dislikey"  value="싫어요 <%=content.getDislikey()%>"/>
			            <input type="hidden" name="command" value="dislike">
			            <input type="hidden" name="bno" value="<%=content.getBno()%>">
		            </form>
	               </div>
	               <div style="clear:both;"></div>
	            </div>
	            <div style="clear:both;"></div>
            </div>
            <div class="comment" style="border:groove;">
            	<div class="reId">
	            	<%=id %>
            	</div>
            	<form action="Controller?" method="get">
            		<input type="hidden" name="command" value="reply_plus">
            		<input type="hidden" name="bno" value="<%=content.getBno()%>">
            		<input type="hidden" name="id" value="<%=id%>">
	                <div class="content fl" style="width: 94%;height: 25px;">
	                   	<input type="text" name="recontent" class="reinput" placeholder="댓글을 남겨보세요.">
	                </div>
	                <div class="content fr" style="margin-right:10px;">
	                   	<input type="submit" class="resubmit" value="등록">
	                </div>
            	</form>
                <div style="clear:both;"></div>
            </div>
            <%int cnt=0;
            for(ReplyDto rdto : replyDto) {
            	cnt++;
            %>
            <div class="comment">
            	
            	<div class="reId">
	            	<%=rdto.getId() %>
            	</div>
                <div class="content">
                   	<%=rdto.getRecontent() %>
                </div>
                <div class="meta">
                    작성일: <%=rdto.getWritedate() %>
                    <button class="rereplybtn" id="<%=cnt %>" type="button">답글달기</button>
                </div>
            </div>
            	<div id="div<%=cnt %>" style="display:none;">
	            	<div class="comment" style="border:groove; margin-left:50px;">
		            	<div class="reId">
			            	<%=id %>
		            	</div>
		                <form action="Controller?" method="get">
		            		<input type="hidden" name="command" value="re_reply_plus">
		            		<input type="hidden" name="bno" value="<%=content.getBno()%>">
		            		<input type="hidden" name="id" value="<%=id%>">
		            		<input type="hidden" name="rebno" value="<%=rdto.getBno()%>">
			                <div class="content fl" style="width: 94%;height: 25px;">
			                   	<input type="text" name="recontent"  class="reinput" placeholder="댓글을 남겨보세요.">
			                </div>
			                <div class="content fr" style="margin-right:10px;">
			                   	<input type="submit" class="resubmit" value="등록">
			                </div>
		            	</form>
		                <div style="clear:both;"></div>
		            </div>
		            </div>
		            <div style="display:block;">
		            <%int rebno = rdto.getBno();
		        	ArrayList<ReplyDto> reReplyDto = replyDao.reselect(rebno);
		            if(reReplyDto!=null){%>
			            <%for(ReplyDto rdto2 : reReplyDto) { %>
		            	 <div class="comment" style="margin-left:50px;">
				            	<div class="reId">
					            	<%=rdto2.getId() %>
				            	</div>
				                <div class="content">
				                   └ <%=rdto2.getRecontent() %>
				                </div>
				                <div class="meta">
				                    작성일: <%=rdto2.getWritedate() %>
				                    <!-- <button class="rereplybtn" type="button">답글달기</button> -->
				                </div>
			            	</div>
	               	<%}
               	} %>
            	</div>
               <%} %>
        </div>
    </div>
    <%}%>
</body>
</html>