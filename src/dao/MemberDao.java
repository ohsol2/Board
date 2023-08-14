package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.BoardDto;
import dto.MemberDto;

public class MemberDao {
	public boolean loginCheck(String id, String pw) {
		Connection conn = Jdbc.connect();
		String sql ="SELECT count(*) FROM member WHERE id=? AND pw=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id); // pstmt.setInt(물음표번호, 정수 값);-->물음표를 그 값으로 치환. 참고) pstmt.setString(물음표번호, 문자열);
			pstmt.setString(2, pw); // pstmt.setInt(물음표번호, 정수 값);-->물음표를 그 값으로 치환. 참고) pstmt.setString(물음표번호, 문자열);
			pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int cnt = rs.getInt(1);
				if(cnt==1) {
					return true;
				}else {
					return false;
				}
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
		return false;
	}
	public boolean signupCheck(String id) {
		Connection conn = Jdbc.connect();
		String sql ="SELECT count(*) FROM member WHERE id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id); // pstmt.setInt(물음표번호, 정수 값);-->물음표를 그 값으로 치환. 참고) pstmt.setString(물음표번호, 문자열);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int cnt = rs.getInt(1);
				if(cnt==0) {
					return true;
				}
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
		return false;
	}
	public MemberDto select1(String id) {
		Connection conn = Jdbc.connect();
		MemberDto listDto = null;
		
		String sql ="SELECT * FROM member WHERE id=?";
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id); // pstmt.setInt(물음표번호, 정수 값);-->물음표를 그 값으로 치환. 참고) pstmt.setString(물음표번호, 문자열);
			pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String id1 = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String nickname = rs.getString("nickname");
				listDto = new MemberDto(id1,pw,name,nickname);
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
	public void signup(String id,String pw,String name, String nickname) {
		Connection conn = Jdbc.connect();
		String sql = "INSERT INTO member(id,pw,name,nickname)"
				+ " VALUES (?,?,?,?)";
			 PreparedStatement pstmt = null;
		 try {
			 pstmt=conn.prepareStatement(sql);
			 pstmt.setString(1,id);
			 pstmt.setString(2,pw);
			 pstmt.setString(3,name);
			 pstmt.setString(4,nickname);
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
