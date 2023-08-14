package action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import dao.MemberDao;
import dto.BoardDto;
import dto.MemberDto;

public class MyPageAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNum = 0;
		try{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		} catch(NumberFormatException e) {
			pageNum = 1;
		}
		String id = (String) request.getSession().getAttribute("id");
		BoardDao listDao = new BoardDao();
		ArrayList<BoardDto> ListDto = listDao.selectMine(pageNum,id);
		MemberDao memberDao = new MemberDao();
		MemberDto memberDto = memberDao.select1(id);
		
		request.setAttribute("ListDto", ListDto);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("memberDto", memberDto);
		RequestDispatcher rd = request.getRequestDispatcher("boardList/MyPage.jsp");
		rd.forward(request, response);
		
	}
}







