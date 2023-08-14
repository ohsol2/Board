package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.BoardDao;
import dto.BoardDto;


@WebServlet("/MyBoardServlet")
public class MyBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		BoardDao bDao = new BoardDao();
		String id = (String)request.getSession().getAttribute("id");
		ArrayList<BoardDto> listBoard = bDao.selectMine(pageNum, id);
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		for(BoardDto dto : listBoard) {
			JSONObject obj = new JSONObject();
			obj.put("bno", dto.getBno());
			obj.put("writer", dto.getWriter());
			obj.put("image", dto.getImage());
			obj.put("title", dto.getTitle());
			obj.put("writedate", dto.getWritedate());
			obj.put("views", dto.getViews());
			obj.put("likey", dto.getLikey());
			obj.put("dislikey", dto.getDislikey());
			array.add(obj);
		}
		out.println(array);
	}


}
