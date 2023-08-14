package servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.BoardDao;
import dao.MemberDao;
import dto.MemberDto;

@WebServlet("/WriteServlet")
public class WriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		int maxSize = 1024 * 1024 * 5; // 키로바이트 * 메가바이트 * 기가바이트   
		String location =  getServletContext().getRealPath("/Image");
		MultipartRequest multi = new MultipartRequest(request,
												location,
												  maxSize,
												  "utf-8",
												  new DefaultFileRenamePolicy());


		Enumeration<?> files = multi.getFileNames(); 
			
		String element = "";
		String filesystemName = "";
		String originalFileName = "";
		String contentType = "";
		long length = 0;		
				
		if (files.hasMoreElements()) { 
			element = (String)files.nextElement(); 
			filesystemName 			= multi.getFilesystemName(element); 
			originalFileName 		= multi.getOriginalFileName(element); 
			contentType 			= multi.getContentType(element);	
			length 					= (multi.getFile(element)==null?0L:multi.getFile(element).length()); 
			
		}

		String image = filesystemName;
		
		String id= multi.getParameter("id");
		String title= multi.getParameter("title");
		String content= multi.getParameter("content");
		String visibility= multi.getParameter("visibility");
		boolean success = false;
		BoardDao boardDao = new BoardDao();
		
		if (image != null && !image.isEmpty()) {
		    boardDao.insert(title, content, id, image, visibility);
		    success = true;
		} else {
		    boardDao.insert(title, content, id, visibility);
		    success = true;
		}
		MemberDao memberDao = new MemberDao();
		MemberDto memberDto = memberDao.select1(id);
		request.setAttribute("memberDto", memberDto);
		request.setAttribute("id", id);
		request.setAttribute("success", success);
		RequestDispatcher rd = request.getRequestDispatcher("boardList/BoardWrite.jsp");
		rd.forward(request, response);
	}

}
