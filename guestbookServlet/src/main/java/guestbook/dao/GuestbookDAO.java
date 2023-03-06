package guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import guestbook.bean.GuestbookDTO;

public class GuestbookDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username ="c##JAVA" ;
	private String password = "1234";
	
	private static GuestbookDAO guestbookDAO = new GuestbookDAO();
	
	public static GuestbookDAO getInstance() {
		return guestbookDAO; // 주		
	}
	
	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public GuestbookDAO() {
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

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
		public void guestbookWrite(GuestbookDTO guestbookDTO) {
			String sql = "INSERT INTO GUESTBOOK VALUES(SEQ_GUESTBOOK.NEXTVAL, ?, ?, ?, ?, ?, sysdate)";
			
			getConnection();
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, guestbookDTO.getGuest());
				pstmt.setString(2, guestbookDTO.getEmail());
				pstmt.setString(3, guestbookDTO.getHomepage());
				pstmt.setString(4, guestbookDTO.getTitle());
				pstmt.setString(5, guestbookDTO.getContent());
				
				pstmt.executeUpdate(); //실행
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				GuestbookDAO.close(conn, pstmt);
			}
		}
		
	
	
		
		public GuestbookDTO guestbookSearch(String seq) {
			GuestbookDTO guestbookDTO = null;
			String sql ="select seq, name, email, homepage, subject, content, to_char(logtime, 'YYYY.MM.DD')"
					+ "as logtime from guestbook where seq=?";
			getConnection();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(seq));
				rs = pstmt.executeQuery();
				
				
			  if(rs.next()) { 
					guestbookDTO = new GuestbookDTO();
					guestbookDTO.setSeq(rs.getInt("seq"));
					guestbookDTO.setGuest(rs.getString("name"));
					guestbookDTO.setEmail(rs.getString("email"));
					guestbookDTO.setHomepage(rs.getString("homepage"));
					guestbookDTO.setTitle(rs.getString("subject"));
					guestbookDTO.setContent(rs.getString("content"));
					guestbookDTO.setLogtime(rs.getString("logtime"));
			  }
			}
			  catch (SQLException e) {
				e.printStackTrace();
			  } finally {
				GuestbookDAO.close(conn, pstmt, rs);
				}
			  
			return guestbookDTO;
			
		
		}

		public ArrayList<GuestbookDTO> guestbookList(int startNum, int endNum) {
			ArrayList<GuestbookDTO> list = new ArrayList<GuestbookDTO>();
			String sql ="select * from "
					+ "(select rownum rn, aa. * from "
					+ "(select seq, name, email, homepage, subject, content, to_char(logtime, 'YYYY.MM.DD')"
					+ "as logtime from guestbook order by seq desc) aa"
					+ ")where rn>=? and rn<=?";
					
					
			getConnection();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startNum);
				pstmt.setInt(2, endNum);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					GuestbookDTO guestbookDTO = new GuestbookDTO();
					guestbookDTO.setSeq(rs.getInt("seq"));
					guestbookDTO.setGuest(rs.getString("name"));
					guestbookDTO.setEmail(rs.getString("email"));
					guestbookDTO.setHomepage(rs.getString("homepage"));
					guestbookDTO.setTitle(rs.getString("subject"));
					guestbookDTO.setContent(rs.getString("content"));
					guestbookDTO.setLogtime(rs.getString("logtime"));
					
					
					list.add(guestbookDTO);
				}//while문
					
			} catch (SQLException e) {	
				e.printStackTrace();
				list = null; 
			}finally {
				GuestbookDAO.close(conn, pstmt, rs);
				}
			
			return list;
		}

		public int getTotalA() {
			int totalA = 0;
			String sql = "select count(*) from guestbook";
			
			getConnection();
			
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				rs.next();
				totalA = rs.getInt(1);
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				GuestbookDAO.close(conn, pstmt, rs);
			}
			
			return totalA;
		}
}
