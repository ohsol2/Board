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

public class ViewsUpdateAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		BoardDao boardDao = new BoardDao();
		String id = (String) request.getSession().getAttribute("id");
		if(id!=null) {
			boardDao.updateView(bno);
			response.sendRedirect("Controller?command=board_content&bno="+bno);
		}else {
			response.sendRedirect("Controller?command=board_content&bno="+bno);
		}
	}
}







