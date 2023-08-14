package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import dao.MemberDao;
import dto.BoardDto;
import dto.MemberDto;

public class BoardUpdateAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao bDao = new BoardDao();
		int bno = Integer.parseInt(request.getParameter("bno"));
		BoardDto content = bDao.select1(bno);
		
		String id = (String) request.getSession().getAttribute("id");
		MemberDao memberDao = new MemberDao();
		MemberDto memberDto = memberDao.select1(id);
		
		request.setAttribute("memberDto", memberDto);
		request.setAttribute("content", content);
		RequestDispatcher rd = request.getRequestDispatcher("boardList/BoardUpdate.jsp");
		rd.forward(request, response);
		
	}
}







