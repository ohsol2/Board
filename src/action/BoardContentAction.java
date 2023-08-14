package action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import dao.ReplyDao;
import dto.BoardDto;
import dto.ReplyDto;

public class BoardContentAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao bDao = new BoardDao();
		int bno = Integer.parseInt(request.getParameter("bno"));
		BoardDto content = bDao.select1(bno);
		
		ReplyDao rDao = new ReplyDao();
		ArrayList<ReplyDto> replyDto = rDao.select(bno);
		
		
		request.setAttribute("content", content);
		request.setAttribute("replyDto", replyDto);
		RequestDispatcher rd = request.getRequestDispatcher("boardList/BoardContent.jsp");
		rd.forward(request, response);
		
	}
}







