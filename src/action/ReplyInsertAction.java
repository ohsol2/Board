package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import dao.MemberDao;
import dao.ReplyDao;
import dto.BoardDto;
import dto.MemberDto;

public class ReplyInsertAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		String recontent = request.getParameter("recontent");
		String id = request.getParameter("id");
		ReplyDao replydao = new ReplyDao();
		replydao.insert(bno,id,recontent);
		response.sendRedirect("Controller?command=board_content&bno="+bno);
		
	}
}







