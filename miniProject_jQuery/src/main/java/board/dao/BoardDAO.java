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
	
	
	
	//글 수 총 몇 글자인지
	public int getTotalA() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int totalA = sqlSession.selectOne("boardSQL.getTotalA");
		sqlSession.close();
		return totalA;
		
	}
	
	//게시글 보기 기능
	public BoardDTO getBoard(int seq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BoardDTO boardDTO = sqlSession.selectOne("boardSQL.getBoard", seq);
		sqlSession.close();
		return boardDTO;
		
	}
	
	//답글기능
	public void boardReply(Map<String, String> map) {
		//원글 먼저 잡아오기 원글은 boardDTO가 갖고 있고 달아야할 답글은 map이 가지고 있다.
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BoardDTO boardDTO = sqlSession.selectOne("boardSQL.getBoard", Integer.parseInt(map.get("pseq")));
		
		
		
		
		
		//step update
		//update board set step=step+1 where ref=원글ref and step>원글step
		sqlSession.update("boardSQL.boardReply1", boardDTO);
		
		
		
		
		
		//insert
		//답글ref = 원글ref
		//답글lev = 원글lev + 1
		//답글step = 원글step + 1  map 받아오는게
		//Map에는 id, name, email, subject, content, pseq를 담아왔다. 추가로 ref, lev, step넣었다. 
		map.put("ref", boardDTO.getRef()+"");
		map.put("lev", boardDTO.getLev()+1+"");
		map.put("step", boardDTO.getStep()+1+"");
		sqlSession.insert("boardSQL.boardReply2", map);
		
		
		
		
		//reply update
		//update board set reply=reply+1 where seq=원글번호
		sqlSession.update("boardSQL.boardReply3", boardDTO.getSeq());
		
		sqlSession.commit();
		sqlSession.close();
	}
	
	
	
}

	
	
	



	
