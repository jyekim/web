package member.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import member.bean.MemberDTO;

public class MemberDAO {
//	private Connection conn;
//	private PreparedStatement pstmt;
//	private ResultSet rs;
//	
//	private DataSource ds;
//	private String nameDB="";
//	public String getNameDB() {return nameDB;}
	
//	private String driver = "oracle.jdbc.driver.OracleDriver";
//	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
//	private String username ="c##JAVA" ;
//	private String password = "1234";
	
	private SqlSessionFactory sqlSessionFactory; //이게 필요함 반드시 데이터를 읽어와야하는데 그래서inputstream이나 reader가 필요

	private static MemberDAO memberDAO = new MemberDAO(); //기본생성자가 필요한 이유 
	
	public static MemberDAO getInstance() {
		return memberDAO;	
	}


	
	//JDBC->MyBatis 
	public MemberDAO() {
		InputStream inputStream;
		try {
		Reader reader =  Resources.getResourceAsReader("mybatis-config.xml"); //추상클래스라서 new가 안됨
			//inputStream = Resources.getResourceAsStream("/conf/mybatis-config.xml");//윗줄은 문자단위로 열어오는 것이고 지금 이줄은 바이트단위로 열어오는 것  
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}//기본 생성자
		

	//회원 로그인
	public MemberDTO memberLogin(Map<String,String> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		MemberDTO memberDTO = sqlSession.selectOne("memberSQL.memberLogin", map);
		sqlSession.close();
		return memberDTO;
	}

	
		//회원 가입기능 
	public int memberWrite(MemberDTO dto) {
	
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int su=sqlSession.insert("memberSQL.memberWrite", dto);
		sqlSession.commit();
		sqlSession.close();
		//memberSQL.Write
		return su;
	}
	
	
	
		//회원 리스트 조회 기능 //SqlSession은 Connection + Statement 기능을 합쳐놓은 것 
	public List<MemberDTO> getMemberList() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<MemberDTO> list = sqlSession.selectList("memberSQL.getMemberList");	
		sqlSession.close();
		return list;
		}
	
	
	
	//회원 삭제 기능 
	public void memberDelete(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.delete("memberSQL.memberDelete", id);
		sqlSession.commit();
		sqlSession.close();
	}

	
	// 회원 수정 기능 
	public void memberUpdate(MemberDTO dto) {
		SqlSession session = sqlSessionFactory.openSession();
		session.update("memberSQL.memberUpdate", dto);
		session.commit();
		session.close();
	}
	
	
	//회원아이티체크 기능 
	public MemberDTO getMember(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession(); //생성
		MemberDTO memberDTO = sqlSession.selectOne("memberSQL.getMember", id); 
		sqlSession.close();
		return memberDTO;
	}
	
	
	//비번존재여부 
	public boolean isExistPwd(Map<String, String> map) {
		boolean result = false;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		MemberDTO memberDTO = sqlSession.selectOne("memberSQL.isExistPwd", map); 

		if(memberDTO != null) {result=true;}
				
		sqlSession.close();
		return result;
		
	}
	
//	//비번존재여부 
//		public boolean isExistPwd(String id, String pwd) {
//			SqlSession sqlSession = sqlSessionFactory.openSession();
//			boolean exist =false; 
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("id", id);
//			map.put("pwd", pwd);
//			MemberDTO memberDTO = sqlSession.selectOne("memberSQL.IsExistPwd", map); 
//			sqlSession.close();
//
//			if(memberDTO!=null) exist=true;
//			
//			sqlSession.close();
//			return exist;
//			
//		}
	
	
	
	//회원아이디존재여부
	public boolean isExistId(String id) {
		boolean existId = false;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		MemberDTO memberDTO = sqlSession.selectOne("memberSQL.isExistId", id);
		if(memberDTO != null) existId =true;
		sqlSession.close();
		return existId;
		
	}

	


}
	

//update, insert, delete는 commit 필요
	
	
	
	
	

	
//	public static void close(Connection conn, PreparedStatement pstmt) {
//		try {
//			if(pstmt != null) pstmt.close();
//			if(conn != null) conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	//Overloading
//	private static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
//		try {
//			if(rs != null) rs.close();
//			if(pstmt != null) pstmt.close();
//			if(conn != null) conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}	
//	}
//	
//	//생성자 호출(public memberDAO())를 통한 드라이버 로딩
//	public MemberDAO() {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle"); // tomcat의 경우일때만 java:comp/env꼭 붙어야함 모든 커넥션을 ds가 가지는것 
//			
//			
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	} 
//	
////	public void getConnection() {
////		try {
////			conn = DriverManager.getConnection(url, username, password);
////			System.out.println("connection 성공");
////		} catch (SQLException e) {
////			e.printStackTrace();
////		}
////	}
//	
//	public int memberWrite(MemberDTO memberDTO) {
//		int su = 0;
//		
//		
//		String sql ="insert into member values(?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
//		
//		try {
//			conn = ds.getConnection();
//			
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setString(1, memberDTO.getName());
//			pstmt.setString(2, memberDTO.getId());
//			pstmt.setString(3, memberDTO.getPwd());
//			pstmt.setString(4, memberDTO.getGender());
//			pstmt.setString(5, memberDTO.getEmail1());
//			pstmt.setString(6, memberDTO.getEmail2());
//			pstmt.setString(7, memberDTO.getTel1());
//			pstmt.setString(8, memberDTO.getTel2());
//			pstmt.setString(9, memberDTO.getTel3());
//			pstmt.setString(10, memberDTO.getZipcode());
//			pstmt.setString(11, memberDTO.getAddr1());
//			pstmt.setString(12, memberDTO.getAddr2());
//			
//			su = pstmt.executeUpdate(); //실행 - 개수 리턴 //su 라고 개수를 받아줘야 리턴되어서 감
//			
//		
//			
//		} catch (SQLException e) {
//		
//			e.printStackTrace();
//		} finally {
//			MemberDAO.close(conn, pstmt);
//		}
//		
//		return su;
//	}
//	
//	public int loginTry(MemberDTO memberDTO) {
//		
//		String sql = "select * from member where id=? and pwd=?";
//		
//		String idDB ="";
//		String pwdDB ="";
//		// 전역 변수 설정을 통한 getter 활용 => 외부 class 접근 허용 
//		
//		try {
//			conn = ds.getConnection();
//			pstmt = conn.prepareStatement(sql);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			MemberDAO.close(conn, pstmt, rs);
//		}
//		if(idDB.equals(memberDTO.getId()) && pwdDB.equals(memberDTO.getPwd())) {
//			return 1;
//		} else {return 0;}
//		
//	}//loginTry 
//
//	
//	
//	//for LoginServlet2. java
//	public MemberDTO memberLogin(String id, String pwd) { //DB에서 받은 정보 여기로 넘김 그리고 return 해놓고 그 다음 다시 db로 감 
////		String name = null;
////		String email1 = null;
////		String email2 = null;
//		MemberDTO memberDTO =null;
//		String sql = "select * from member where id=? and pwd=?";
//		 //오라클에 접속하는거임
//		try {
//			conn = ds.getConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, id);
//			pstmt.setString(2, pwd);
//			rs = pstmt.executeQuery();// resultset 리턴
//			
//			
//			if(rs.next()) {
//				memberDTO = new MemberDTO();
//				memberDTO.setName(rs.getString("name"));
//				memberDTO.setEmail1(rs.getString("email1"));
//				memberDTO.setEmail2(rs.getString("email2"));
////				name = rs.getString("name");
////				email1 =rs.getString("email1");
////				email2 =rs.getString("email2");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			MemberDAO.close(conn, pstmt, rs);
//		}
//		
//		return memberDTO; //리턴이 memberDTO로 바뀌었으니 public뒤에도 바꿔줘야함
//		}// memverLogin
//	
//	public MemberDTO membercall(String id) {
//		
//		
//		String sql= "select * from member where id=?";
//		MemberDTO memberDTO = null;
//		
//		try {
//			conn = ds.getConnection();
//			pstmt= conn.prepareStatement(sql);
//			
//			pstmt.setString(1, id);
//			
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				memberDTO = new MemberDTO();
//				//rs.next()= true 일 때 
//				
//				
//				memberDTO.setName(rs.getString("NAME"));
//				memberDTO.setId(rs.getString("ID"));
//				memberDTO.setPwd(rs.getString("PWD"));
//				memberDTO.setGender(rs.getString("GENDER"));
//				memberDTO.setEmail1(rs.getString("EMAIL1"));
//				memberDTO.setEmail2(rs.getString("EMAIL2"));
//				memberDTO.setTel1(rs.getString("TEL1"));
//				memberDTO.setTel2(rs.getString("TEL2"));
//				memberDTO.setTel3(rs.getString("TEL3"));
//				memberDTO.setZipcode(rs.getString("ZIPCODE"));
//				memberDTO.setAddr1(rs.getString("ADDR1"));
//				memberDTO.setAddr2(rs.getString("ADDR2"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			MemberDAO.close(conn, pstmt, rs);
//		}
//		return memberDTO;
//	}
//	
//	public MemberDTO getMember(String id) {
//		MemberDTO dto = null;
//		
//		
//		return dto;
//		
//		
//	}
//	
//	
//	public int memberUpdate(MemberDTO memberDTO) {
//		int rowUpdate = 0;
//		
//		
//		String sql = "update member set name =?"
//									+ ", pwd=?"
//									+ ", gender=?"
//									+ ", email1=?"
//									+ ", email2=?"
//									+ ", tel1=?"
//									+ ", tel2=?"
//									+ ", tel3=?"
//									+ ", zipcode=?"
//									+ ", addr1=?"
//									+ ", addr2=?"
//									+ ", logtime=sysdate "
//									+ " where id=?";
//		
//		
//		try {
//			conn = ds.getConnection();
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setString(1, memberDTO.getName());
//			pstmt.setString(2, memberDTO.getPwd());
//			pstmt.setString(3, memberDTO.getGender());
//			pstmt.setString(4, memberDTO.getEmail1());
//			pstmt.setString(5, memberDTO.getEmail2());
//			pstmt.setString(6, memberDTO.getTel1());
//			pstmt.setString(7, memberDTO.getTel2());
//			pstmt.setString(8, memberDTO.getTel3());
//			pstmt.setString(9, memberDTO.getZipcode());
//			pstmt.setString(10, memberDTO.getAddr1());
//			pstmt.setString(11, memberDTO.getAddr2());
//			pstmt.setString(12, memberDTO.getId()); //조건문에 잇어서 아이디가 제일 밑임 바뀌는게 아니기때문
//		
//			//execute
//			rowUpdate = pstmt.executeUpdate(); //개수 리턴 
//			
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			MemberDAO.close(conn, pstmt);
//		}
//	
//		return rowUpdate; 
//		
//	}
//
//	public boolean isExistPwd(String id, String pwd) {
//		boolean exist = false;
//		String sql = "select * from member where id=? and pwd=?";
//		
//		
//		try {
//			conn = ds.getConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, id);
//			pstmt.setString(2, pwd);
//			
//			rs = pstmt.executeQuery();
//			
//			
//			if(rs.next()) {
//				exist = true;
//			}
//				
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			MemberDAO.close(conn, pstmt, rs);
//		}
//		
//		return exist;
//	}
//		public void memberDelete(String id) {
//			String sql ="delete member where id=?";
//			
//			
//			try {
//				conn = ds.getConnection();
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, id);
//				
//				pstmt.executeUpdate();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} finally {
//				MemberDAO.close(conn, pstmt, rs);
//			}
//		}
//		
//		
////		
//		public boolean isExistId(String id) {
//			boolean existId = false;
//			String sql = "select * from member where id=?";
//			
//			try {
//				conn = ds.getConnection();
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, id);
//				
//				rs = pstmt.executeQuery();
//				
//				if(rs.next()) existId = true;
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} finally {
//				MemberDAO.close(conn, pstmt, rs);
//			}
//			
//			
//			return existId;
//			}
//
//
//}


