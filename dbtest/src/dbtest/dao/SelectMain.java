package dbtest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectMain {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username ="c##JAVA" ;
	private String password = "1234";
	
	
	public SelectMain() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	

	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void selectArticle() {
		getConnection(); // 접속
		
		String sql = "select * from dbtest";
		
		try {
			pstmt = conn.prepareStatement(sql); //오라클 문장을 처리할 가이드 생성 
			rs = pstmt.executeQuery();          // ResultSet 리턴 
			
			while(rs.next()) {
				System.out.println(rs.getString("name") + "\t"
									+ rs.getInt("age") + "\t"
									+rs.getDouble("height") + "\t" 
									+rs.getString("logtime"));
			}// while 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				}
			}
	}
	

	public static void main(String[] args) {
		SelectMain sm = new SelectMain();
	}

}
