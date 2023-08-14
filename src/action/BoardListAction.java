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

public class BoardListAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao bDao = new BoardDao();
		int pageNum = 0;
		try{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		} catch(NumberFormatException e) {
			pageNum = 1;
		}
		
		BoardDao listDao = new BoardDao();
		int cnt = listDao.pageCount();
		MemberDao memberDao = new MemberDao();
		ArrayList<BoardDto> listBoard = bDao.select(pageNum);
		ArrayList<BoardDto> listBoard2 = bDao.selectAll();
		String id = (String) request.getSession().getAttribute("id");
		MemberDto memberDto = memberDao.select1(id);
		ArrayList<BoardDto> listbDto = bDao.selectImageList();
		request.setAttribute("listBoard", listBoard);
		request.setAttribute("listBoard2", listBoard2);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("cnt", cnt);
		request.setAttribute("listbDto", listbDto);
		request.setAttribute("memberDto", memberDto);
		RequestDispatcher rd = request.getRequestDispatcher("boardList/BoardMain.jsp");
		rd.forward(request, response);
		
	}
}







