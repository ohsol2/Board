package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dao.BoardDao;
import dao.MemberDao;
import dto.BoardDto;
import dto.MemberDto;


@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchContent = request.getParameter("searchContent");
		String id = (String)request.getSession().getAttribute("id");
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		response.setContentType("application/json");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(getJSON(id,pageNum,searchContent));
	} 
	 public String getJSON(String id,int pageNum, String searchContent) { 
	        StringBuilder result = new StringBuilder();
	        result.append("{\"result\":[");
	        MemberDao mDao = new MemberDao();
	        MemberDto mDto = mDao.select1(id);
	        BoardDao bDao = new BoardDao();
			ArrayList<BoardDto> searchListBoard = bDao.selectSearch(pageNum, searchContent);
	        int size = searchListBoard.size();
	        for (int i = 0; i < size; i++) {
	        	BoardDto dto = searchListBoard.get(i);
	            result.append("[");
	            result.append("{\"value\": \"" + "<input type='checkbox'>" + "\"},");
	            result.append("{\"value\": \"" + dto.getBno() + "\"},");
	            result.append("{\"value\": \"" + (dto.getWriter()==null?"":dto.getWriter()) + "\"},");
	            result.append("{\"value\": \"" + (dto.getTitle()==null?"":dto.getTitle()) + "\"},");
	            result.append("{\"value\": \"" + (dto.getWritedate()==null?"":dto.getWritedate().substring(0, 10)) + "\"},");
	            result.append("{\"value\": \"" + dto.getViews()+  "\"},");
	            result.append("{\"value\": \"" + dto.getLikey() + "\"},");
	            result.append("{\"value\": \"" + dto.getDislikey() + "\"},");
	            if(mDto.getNickname().equals(dto.getWriter())) {
	            	result.append("{\"value\": \"" +  "<button type='button' class='boardDelete'>삭제</button>" + "\"}");
	            }else {
	            	result.append("{\"value\": \"" +  "" + "\"}");
	            }
	            result.append("]");
	            if (i < size - 1) {
	                result.append(",");
	            }
	        }
	        result.append("]}");
	        return result.toString();
	    }
}
