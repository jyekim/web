package board.dao;


import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import board.bean.BoardDTO;


public class BoardDAO {
	private SqlSessionFactory sqlSessionFactory;

	
	private static BoardDAO boardDAO = new BoardDAO();
	
	public static  BoardDAO getInstance() {
		return boardDAO;	
	}
	
	
	public BoardDAO() {//inputStream은 결국 데이터를 byte단위로 읽어들이는 통로이며(읽어들인 데이터를 byte로 돌려줌)
	InputStream inputStream; 
	Reader reader;
	try {
		reader = Resources.getResourceAsReader("mybatis-config.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
	} catch (IOException e) {
		e.printStackTrace();
		}
	
	}
	
	//글작성
	public void boardWrite(Map<String, String> map) { 
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.insert("boardSQL.boardWrite", map);
		sqlSession.commit();
		sqlSession.close();
	}
	
	
	//글 목록 
	public List<BoardDTO> boardList(Map<String, Integer> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<BoardDTO> list = sqlSession.selectList("boardSQL.boardList", map);
		sqlSession.close();
		return list;
	}
	
	
	
	
	//게시글 보기 기능
	public BoardDTO getBoard(int seq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BoardDTO boardDTO = sqlSession.selectOne("boardSQL.getBoard", seq);
		sqlSession.close();
		return boardDTO;
		
	}
	
	
	
	//글 수 총 몇 글자인지
	public int getTotalA() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int totalA = sqlSession.selectOne("boardSQL.getTotalA");
		sqlSession.close();
		return totalA;
		
	}
	
	
	
	
}

	
	
	
	
	
//	
//	
//		
//		public BoardDAO() {
//			InputStream inputStream;
//			try {
//			Reader reader =  Resources.getResourceAsReader("conf/mybatis-config.xml"); //추상클래스라서 new가 안됨
//				//inputStream = Resources.getResourceAsStream("/conf/mybatis-config.xml");//윗줄은 문자단위로 열어오는 것이고 지금 이줄은 바이트단위로 열어오는 것  
//				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//			
//			} catch (IOException e) {
//				e.printStackTrace();
//			}  
//		}//기본 생성자 
//		
//		
//		//글 작성
//		public void boardWrite(Map<String,String> map) {
//			SqlSession sqlSession = sqlSessionFactory.openSession();
//			sqlSession.insert("boardSQL.boardWrite", map);
//			sqlSession.commit();
//			sqlSession.close();
//		}	
//			
//			//글 목록
//			public List<BoardDTO> boardList(Map<String, Integer> map) {
//				SqlSession sqlSession = sqlSessionFactory.openSession();
//				List<BoardDTO> list = sqlSession.selectList("boardSQL.boardList", map);
//				sqlSession.close();
//				return list;
//			}					
//	
//			
//			///총 글 수 구하는 로직 getTotalA()메서드를 추가해준다. 
//			public int getTotalA() {
//				SqlSession sqlSession = sqlSessionFactory.openSession();
//				int totalA = sqlSession.selectOne("boardSQL.getTotalA");
//				sqlSession.close();
//				return totalA;
//			}
//		
//		
//	
//			
//			
//			//게시글 보기 기능 
//			public BoardDTO getBoard(int seq) {
//				SqlSession sqlSession = sqlSessionFactory.openSession();
//				BoardDTO boardDTO = sqlSession.selectOne("boardSQL.getBoard",seq);
//				sqlSession.close();
//				return boardDTO;
//			}
//				
//				
//}		
//			
//			



	
