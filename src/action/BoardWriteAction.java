package action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDao;
import dao.MemberDao;
import dto.BoardDto;
import dto.MemberDto;

public class BoardWriteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = (String) request.getSession().getAttribute("id");
		MemberDao memberDao = new MemberDao();
		MemberDto memberDto = memberDao.select1(id);
		
		boolean success = false;
		
		request.setAttribute("memberDto", memberDto);
		request.setAttribute("id", id);
		request.setAttribute("success", success);
		RequestDispatcher rd = request.getRequestDispatcher("boardList/BoardWrite.jsp");
		rd.forward(request, response);
		
	}
}







