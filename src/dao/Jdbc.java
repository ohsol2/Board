package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Jdbc {
	public static Connection connect() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbId = "boardlist";
		String dbPw = "1234";
		
		Connection conn = null;
	      try {
	         Class.forName(driver);
	         conn = DriverManager.getConnection(url, dbId, dbPw);
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	      return conn;
	}
}
