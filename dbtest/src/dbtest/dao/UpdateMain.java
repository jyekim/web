package dbtest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateMain {
	private Connection conn;
	private PreparedStatement pstmt;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username ="c##JAVA" ;
	private String password = "1234";
	
	
	public UpdateMain() {
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
	
	public void UpdateArticle() {
		Scanner scan = new Scanner(System.in);
		System.out.println("검색 할 이름 입력 : "); //-데이터를 입력받는것은 여기까지 
		String name = scan.next();
	
			
			this.getConnection();//오라클의 접속
			String sql = "UPDATE DBTEST SET AGE=AGE+1, HEIGHT=HEIGHT+1 WHERE NAME LIKE ?";
			try {
			pstmt = conn.prepareStatement(sql); //생성
			pstmt.setString(1, "%"+name+"%");
			int su = pstmt.executeUpdate();
			System.out.println(su+ "행이 업데이트 되었습니다");
			}catch(SQLException e) {
				e.printStackTrace();

		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				}
			}
		
		
	}

	public static void main(String[] args) {
		UpdateMain um = new UpdateMain();
		um.UpdateArticle();

	}

}



/*
검색할 이름 입력 : 홍

이름에 홍이 들어간 레코드를 나이를 1증가 , 1증가 하기 

*/