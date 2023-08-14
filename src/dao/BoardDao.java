package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.BoardDto;


public class BoardDao {
	public ArrayList<BoardDto> select(int pageNum) {
		Connection conn = Jdbc.connect();
		ArrayList<BoardDto> listDto = new ArrayList<BoardDto>();
		
		String sql ="SELECT t2.*"
					+" FROM(SELECT rownum rnum, t1.*"
					+" FROM(SELECT b.*,m.* FROM board b, Member m WHERE b.writer=m.id ORDER BY bno DESC)t1)t2"
					+" WHERE t2.rnum>=? AND t2.rnum<=? AND t2.visibility='공개'";
			
			int endNum = 20*pageNum;
			int startNum = endNum-19 ;
			
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum); // pstmt.setInt(물음표번호, 정수 값);-->물음표를 그 값으로 치환. 참고) pstmt.setString(물음표번호, 문자열);
			pstmt.setInt(2, endNum);
			pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int bno = rs.getInt("bno");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer = rs.getString("nickname");
				String writedate = rs.getString("writedate");
				String visibility = rs.getString("visibility");
				String image = rs.getString("image");
				int views = rs.getInt("views");
				int likey = rs.getInt("likey");
				int dislikey = rs.getInt("dislikey");
				BoardDto dto = new BoardDto(bno, title, content, writer, writedate, views, image, visibility, likey, dislikey);
				listDto.add(dto);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listDto;
	}
	public ArrayList<BoardDto> selectAll() {
		Connection conn = Jdbc.connect();
		ArrayList<BoardDto> listDto = new ArrayList<BoardDto>();
		
		String sql ="SELECT * FROM board ORDER BY bno DESC";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int bno = rs.getInt("bno");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer = rs.getString("writer");
				String writedate = rs.getString("writedate");
				String visibility = rs.getString("visibility");
				String image = rs.getString("image");
				int views = rs.getInt("views");
				int likey = rs.getInt("likey");
				int dislikey = rs.getInt("dislikey");
				BoardDto dto = new BoardDto(bno, title, content, writer, writedate, views, image, visibility, likey, dislikey);
				listDto.add(dto);
				//rs.getNString() --> NVARCHAR2 타입이랑 대응!
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listDto;
	}
	public ArrayList<BoardDto> selectMine(int pageNum,String id) {
		Connection conn = Jdbc.connect();
		ArrayList<BoardDto> listDto = new ArrayList<BoardDto>();
		
		String sql ="SELECT t2.*"
				+" FROM(SELECT rownum rnum, t1.*"
				+" FROM(SELECT b.*,m.* FROM board b, Member m WHERE b.writer=m.id ORDER BY bno DESC)t1)t2"
				+" WHERE t2.rnum>=? AND t2.rnum<=? AND t2.id=?";
		
		int endNum = 35*pageNum;
		int startNum = endNum-34 ;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum); // pstmt.setInt(물음표번호, 정수 값);-->물음표를 그 값으로 치환. 참고) pstmt.setString(물음표번호, 문자열);
			pstmt.setInt(2, endNum);
			pstmt.setString(3, id);
			pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int bno = rs.getInt("bno");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer = rs.getString("nickname");
				String writedate = rs.getString("writedate");
				String visibility = rs.getString("visibility");
				String image = rs.getString("image");
				int views = rs.getInt("views");
				int likey = rs.getInt("likey");
				int dislikey = rs.getInt("dislikey");
				BoardDto dto = new BoardDto(bno, title, content, writer, writedate, views, image, visibility, likey, dislikey);
				listDto.add(dto);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listDto;
	}
	public ArrayList<BoardDto> selectImageList() {
		Connection conn = Jdbc.connect();
		ArrayList<BoardDto> listDto = new ArrayList<BoardDto>();
		
		String sql ="SELECT * FROM board WHERE image IS NOT NULL";
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int bno = rs.getInt("bno");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer = rs.getString("writer");
				String writedate = rs.getString("writedate");
				String visibility = rs.getString("visibility");
				String image = rs.getString("image");
				int views = rs.getInt("views");
				int likey = rs.getInt("likey");
				int dislikey = rs.getInt("dislikey");
				BoardDto dto = new BoardDto(bno, title, content, writer, writedate, views, image, visibility, likey, dislikey);
				listDto.add(dto);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listDto;
	}
	public BoardDto select1(int bno) {
		Connection conn = Jdbc.connect();
		BoardDto listDto = null;
		
		String sql ="SELECT * FROM board WHERE bno=?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno); // pstmt.setInt(물음표번호, 정수 값);-->물음표를 그 값으로 치환. 참고) pstmt.setString(물음표번호, 문자열);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int bno1 = rs.getInt("bno");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer = rs.getString("writer");
				String writedate = rs.getString("writedate");
				String visibility = rs.getString("visibility");
				String image = rs.getString("image");
				int views = rs.getInt("views");
				int likey = rs.getInt("likey");
				int dislikey = rs.getInt("dislikey");
				listDto = new BoardDto(bno1, title, content, writer, writedate, views, image, visibility, likey, dislikey);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listDto;
	}
	public void insert(String title,String content,String id, String image, String visibility) {
		Connection conn = Jdbc.connect();
		String sql = "INSERT INTO board(bno,title,content,writer,views,image,visibility,likey,dislikey)"
				+ " VALUES (SEQ_BOARDBNO.nextval,?,?,?,0,?,?,0,0)";
			 PreparedStatement pstmt = null;
		 try {
			 pstmt=conn.prepareStatement(sql);
			 pstmt.setString(1,title);
			 pstmt.setString(2,content);
			 pstmt.setString(3,id);
			 pstmt.setString(4,image);
			 pstmt.setString(5,visibility);
			 pstmt.executeUpdate();
		 } catch(SQLException e) {
				 e.printStackTrace();
		 } finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
	}
	public void insert(String title,String content,String id, String visibility) {
		Connection conn = Jdbc.connect();
		String sql = "INSERT INTO board(bno,title,content,writer,views,visibility,likey,dislikey)"
				+ " VALUES (SEQ_BOARDBNO.nextval,?,?,?,0,?,0,0)";
		PreparedStatement pstmt = null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,title);
			pstmt.setString(2,content);
			pstmt.setString(3,id);
			pstmt.setString(4,visibility);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
	}
	public void update(String title,String content, String image, String visibility, int bno) {
		Connection conn = Jdbc.connect();
		String sql = "UPDATE board SET title=?, content=?,image=? ,visibility=? WHERE bno = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,title);
			pstmt.setString(2,content);
			pstmt.setString(3,image);
			pstmt.setString(4,visibility);
			pstmt.setInt(5,bno);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
	}
	public void update(String title,String content, String visibility, int bno) {
		Connection conn = Jdbc.connect();
		String sql = "UPDATE board SET title=?, content=? ,visibility=? WHERE bno = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,title);
			pstmt.setString(2,content);
			pstmt.setString(3,visibility);
			pstmt.setInt(4,bno);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
	}
	public void updateView(int bno) {
		Connection conn = Jdbc.connect();
		String sql ="SELECT * FROM board WHERE bno=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno); // pstmt.setInt(물음표번호, 정수 값);-->물음표를 그 값으로 치환. 참고) pstmt.setString(물음표번호, 문자열);
			rs = pstmt.executeQuery();
			int view = 0;
			while(rs.next()) {
				view = rs.getInt("views");
			}
			pstmt.close();
			view += 1;
			sql = "UPDATE board SET views=? WHERE bno = ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,view);
			pstmt.setInt(2,bno);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
	}
	public void updateLike(int bno) {
		Connection conn = Jdbc.connect();
		String sql ="SELECT * FROM board WHERE bno=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno); // pstmt.setInt(물음표번호, 정수 값);-->물음표를 그 값으로 치환. 참고) pstmt.setString(물음표번호, 문자열);
			rs = pstmt.executeQuery();
			int view = 0;
			while(rs.next()) {
				view = rs.getInt("likey");
			}
			pstmt.close();
			view += 1;
			sql = "UPDATE board SET likey=? WHERE bno = ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,view);
			pstmt.setInt(2,bno);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
	}
	public void updateDislike(int bno) {
		Connection conn = Jdbc.connect();
		String sql ="SELECT * FROM board WHERE bno=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno); // pstmt.setInt(물음표번호, 정수 값);-->물음표를 그 값으로 치환. 참고) pstmt.setString(물음표번호, 문자열);
			rs = pstmt.executeQuery();
			int view = 0;
			while(rs.next()) {
				view = rs.getInt("dislikey");
			}
			pstmt.close();
			view += 1;
			sql = "UPDATE board SET dislikey=? WHERE bno = ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,view);
			pstmt.setInt(2,bno);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
	}
	public void delete(int bno) {
		Connection conn = Jdbc.connect();
		String sql = "DELETE FROM board WHERE bno = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,bno);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
	}
	public int pageCount() {
		Connection conn = Jdbc.connect();
		String sql ="SELECT count(*) FROM board WHERE visibility='공개'";
		int cnt=0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cnt = rs.getInt("count(*)");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	public int pageSearchCount(String searchContent) {
		Connection conn = Jdbc.connect();
		String sql ="SELECT count(*) FROM board WHERE title LIKE ?";
		int cnt=0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchContent+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cnt = rs.getInt("count(*)");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	public ArrayList<BoardDto> selectSearch(int pageNum, String searchContent) {
		Connection conn = Jdbc.connect();
		ArrayList<BoardDto> listDto = new ArrayList<BoardDto>();
		
		String sql ="SELECT t2.*"
					+" FROM(SELECT rownum rnum, t1.*"
					+" FROM(SELECT b.*,m.* FROM board b, Member m WHERE b.writer=m.id ORDER BY bno DESC)t1)t2"
					+" WHERE t2.rnum>=? AND t2.rnum<=? AND t2.visibility='공개' AND t2.title LIKE ? ";
			
			int endNum = 20*pageNum;
			int startNum = endNum-19 ;
			
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum); // pstmt.setInt(물음표번호, 정수 값);-->물음표를 그 값으로 치환. 참고) pstmt.setString(물음표번호, 문자열);
			pstmt.setInt(2, endNum);
			pstmt.setString(3, "%"+searchContent+"%");
			pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int bno = rs.getInt("bno");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer = rs.getString("nickname");
				String writedate = rs.getString("writedate");
				String visibility = rs.getString("visibility");
				String image = rs.getString("image");
				int views = rs.getInt("views");
				int likey = rs.getInt("likey");
				int dislikey = rs.getInt("dislikey");
				BoardDto dto = new BoardDto(bno, title, content, writer, writedate, views, image, visibility, likey, dislikey);
				listDto.add(dto);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listDto;
	}
}
