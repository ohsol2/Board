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

public class SignupAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id= request.getParameter("id");
		String pw= request.getParameter("pw");
		String name= request.getParameter("name");
		String nickname= request.getParameter("nickname");
		MemberDao memberDao = new MemberDao();
		boolean signupCheck = memberDao.signupCheck(id);
		
		request.setAttribute("signupCheck", signupCheck);
		request.setAttribute("id", id);

		if(signupCheck) {
			memberDao.signup(id, pw, name, nickname);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("boardList/Signup.jsp");
		rd.forward(request, response);
	}
}







