package dbtest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertMain {
	private Connection conn;
	private PreparedStatement pstmt;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username ="c##JAVA" ;
	private String password = "1234";
	
	
	 public InsertMain() {
		// driverloading 한번만 처리하면 됨 
		 try {
			Class.forName(driver);//클래스 타입으로 생성 / 풀쿼리네임은 패키지명.클래스명.class
			System.out.println("driver loading 성공");
		 } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} //생성자 안에서 접속하면 안됨 
		 
	}
	
	 
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("connection 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertArticle() {

		this.getConnection(); // 접속 

		Scanner scan = new Scanner(System.in);
		System.out.println("이름 입력 : ");
		String name = scan.next();
		System.out.println("나이 입력 : ");
		int age = scan.nextInt();
		System.out.println("키 입력 : ");
		double height = scan.nextDouble();
		//---------------
		
		
		String sql = "insert INTO dbtest values(?, ?, ?, sysdate)";
		try {
			pstmt = conn.prepareStatement(sql); //생성
			//?에 데이터를 대입 보안상 ?를 쓰는것을 추천 
			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.setDouble(3, height);
			
			int su = pstmt.executeUpdate();  //실행 - 개수 리턴 
			System.out.println(su + "행이 삽입되었습니다");
			
			
		} catch (SQLException e) {
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
		InsertMain im = new InsertMain();
		im.insertArticle();
	}

}
