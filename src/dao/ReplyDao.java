package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.BoardDto;
import dto.ReplyDto;


public class ReplyDao {
	//댓글들 불러오기
	public ArrayList<ReplyDto> select(int boardbno) {
		Connection conn = Jdbc.connect();
		ArrayList<ReplyDto> listDto = new ArrayList<ReplyDto>();
		
		String sql ="SELECT * FROM reply WHERE boardbno = ? AND rebno IS NULL ORDER BY writedate DESC";
			
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardbno); // pstmt.setInt(물음표번호, 정수 값);-->물음표를 그 값으로 치환. 참고) pstmt.setString(물음표번호, 문자열);
			pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int boardbno1 = rs.getInt("boardbno");
				int bno = rs.getInt("bno");
				String id = rs.getString("id");
				String recontent = rs.getString("recontent");
				String writedate = rs.getString("writedate");
				int rebno = rs.getInt("rebno");
				ReplyDto dto = new ReplyDto(boardbno1, bno, id, recontent, writedate, rebno);
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
	//대댓글 불러오기
	public ArrayList<ReplyDto> reselect(int rebno) {
		Connection conn = Jdbc.connect();
		ArrayList<ReplyDto> listDto = new ArrayList<ReplyDto>();
		
		String sql ="SELECT * FROM reply WHERE rebno = ? ORDER BY writedate";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rebno); // pstmt.setInt(물음표번호, 정수 값);-->물음표를 그 값으로 치환. 참고) pstmt.setString(물음표번호, 문자열);
			pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int boardbno1 = rs.getInt("boardbno");
				int bno = rs.getInt("bno");
				String id = rs.getString("id");
				String recontent = rs.getString("recontent");
				String writedate = rs.getString("writedate");
				int rebno1 = rs.getInt("rebno");
				ReplyDto dto = new ReplyDto(boardbno1, bno, id, recontent, writedate, rebno1);
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
	//댓글달기
	public void insert(int boardbno, String id, String recontent) {
		Connection conn = Jdbc.connect();
		String sql = "INSERT INTO reply(boardbno,bno,id,recontent)"
				+ " VALUES(?,SEQ_REPLYBNO.nextval,?,?)";
			 PreparedStatement pstmt = null;
		 try {
			 pstmt=conn.prepareStatement(sql);
			 pstmt.setInt(1,boardbno);
			 pstmt.setString(2,id);
			 pstmt.setString(3,recontent);
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
	//대댓글달기
	public void reinsert(int boardbno, String id, String recontent, int rebno) {
		Connection conn = Jdbc.connect();
		String sql = "INSERT INTO reply(boardbno,bno,id,recontent,rebno)"
				+ " VALUES(?,SEQ_REPLYBNO.nextval,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,boardbno);
			pstmt.setString(2,id);
			pstmt.setString(3,recontent);
			pstmt.setInt(4,rebno);
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
}
