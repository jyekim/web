package member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import member.bean.MemberDTO;

public class MemberDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private String nameDB="";
	public String getNameDB() {return nameDB;}
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username ="c##JAVA" ;
	private String password = "1234";
	
	private static MemberDAO memberDAO = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return memberDAO;
		
	}
	
	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//Overloading
	private static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	//생성자 호출(public memberDAO())를 통한 드라이버 로딩
	public MemberDAO() {
		 try {
				Class.forName(driver);//클래스 타입으로 생성 / 풀쿼리네임은 패키지명.클래스명.class
				System.out.println("driver loading 성공");
			 } catch (ClassNotFoundException e) {
				e.printStackTrace();
			} //생성자 안에서 접속하면 안됨 		 
	} // default constructor
	
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("connection 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int memberWrite(MemberDTO memberDTO) {
		int su = 0;
		
		this.getConnection(); //접속 
		String sql ="insert into member values(?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberDTO.getName());
			pstmt.setString(2, memberDTO.getId());
			pstmt.setString(3, memberDTO.getPwd());
			pstmt.setString(4, memberDTO.getGender());
			pstmt.setString(5, memberDTO.getEmail1());
			pstmt.setString(6, memberDTO.getEmail2());
			pstmt.setString(7, memberDTO.getTel1());
			pstmt.setString(8, memberDTO.getTel2());
			pstmt.setString(9, memberDTO.getTel3());
			pstmt.setString(10, memberDTO.getZipcode());
			pstmt.setString(11, memberDTO.getAddr1());
			pstmt.setString(12, memberDTO.getAddr2());
			
			su = pstmt.executeUpdate(); //실행 - 개수 리턴 //su 라고 개수를 받아줘야 리턴되어서 감
			
		
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		} finally {
			MemberDAO.close(conn, pstmt);
		}
		
		return su;
	}
	
	public int loginTry(MemberDTO memberDTO) {
		this.getConnection();
		String sql = "select * from member where id=? and pwd=?";
		
		String idDB ="";
		String pwdDB ="";
		// 전역 변수 설정을 통한 getter 활용 => 외부 class 접근 허용 
		
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MemberDAO.close(conn, pstmt, rs);
		}
		if(idDB.equals(memberDTO.getId()) && pwdDB.equals(memberDTO.getPwd())) {
			return 1;
		} else {return 0;}
		
	}//loginTry 

	
	
	//for LoginServlet2. java
	public String memberLogin(String id, String pwd) { //DB에서 받은 정보 여기로 넘김 그리고 return 해놓고 그 다음 다시 db로 감 
		String name = null;
		String sql = "select * from member where id=? and pwd=?";
		getConnection(); //오라클에 접속하는거임
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();// resultset 리턴
			
			
			if(rs.next()) {
				name = rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MemberDAO.close(conn, pstmt, rs);
		}

		return name;
		}// memverLogin
	
	public MemberDTO membercall(String id) {
		this.getConnection();
		
		String sql= "select * from member where id=?";
		MemberDTO memberDTO = null;
		
		try {
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memberDTO = new MemberDTO();
				//rs.next()= true 일 때 
				
				
				memberDTO.setName(rs.getString("NAME"));
				memberDTO.setId(rs.getString("ID"));
				memberDTO.setPwd(rs.getString("PWD"));
				memberDTO.setGender(rs.getString("GENDER"));
				memberDTO.setEmail1(rs.getString("EMAIL1"));
				memberDTO.setEmail2(rs.getString("EMAIL2"));
				memberDTO.setTel1(rs.getString("TEL1"));
				memberDTO.setTel2(rs.getString("TEL2"));
				memberDTO.setTel3(rs.getString("TEL3"));
				memberDTO.setZipcode(rs.getString("ZIPCODE"));
				memberDTO.setAddr1(rs.getString("ADDR1"));
				memberDTO.setAddr2(rs.getString("ADDR2"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MemberDAO.close(conn, pstmt, rs);
		}
		return memberDTO;
	}
	
	public MemberDTO getMember(String id) {
		MemberDTO dto = null;
		
		
		return dto;
		
		
	}
	
	
	public int memberUpdate(MemberDTO memberDTO) {
		int rowUpdate = 0;
		
		this.getConnection();
		String sql = "update member set name =?"
									+ ", pwd=?"
									+ ", gender=?"
									+ ", email1=?"
									+ ", email2=?"
									+ ", tel1=?"
									+ ", tel2=?"
									+ ", tel3=?"
									+ ", zipcode=?"
									+ ", addr1=?"
									+ ", addr2=?"
									+ ", logtime=sysdate "
									+ " where id=?";
		
		getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberDTO.getName());
			pstmt.setString(2, memberDTO.getPwd());
			pstmt.setString(3, memberDTO.getGender());
			pstmt.setString(4, memberDTO.getEmail1());
			pstmt.setString(5, memberDTO.getEmail2());
			pstmt.setString(6, memberDTO.getTel1());
			pstmt.setString(7, memberDTO.getTel2());
			pstmt.setString(8, memberDTO.getTel3());
			pstmt.setString(9, memberDTO.getZipcode());
			pstmt.setString(10, memberDTO.getAddr1());
			pstmt.setString(11, memberDTO.getAddr2());
			pstmt.setString(12, memberDTO.getId()); //조건문에 잇어서 아이디가 제일 밑임 바뀌는게 아니기때문
		
			//execute
			rowUpdate = pstmt.executeUpdate(); //개수 리턴 
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MemberDAO.close(conn, pstmt);
		}
	
		return rowUpdate; 
		
	}

	public boolean isExistPwd(String id, String pwd) {
		boolean exist = false;
		String sql = "select * from member where id=? and pwd=?";
		
		getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) exist = true;
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MemberDAO.close(conn, pstmt, rs);
		}
		
		return exist;
	}
		public void memberDelete(String id) {
			String sql ="delete member where id=?";
			
			getConnection();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				MemberDAO.close(conn, pstmt, rs);
			}
		}
}
