package net.board.db;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BoardDAOImpl {
	
	private SqlSession getSession() {
		SqlSession session=null;
		Reader reader=null;
		try {
			reader = Resources.getResourceAsReader("./SqlMapConfig.xml");
			SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(reader);
			session = sf.openSession(true);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return session;
	}	
	
	
	/* 게시판 저장 */
	public void boardInsert(BoardBean board) {
		SqlSession session=null;
		int result = 0;		
		try{
			session = getSession(); 
			System.out.println("insert1");
			
			System.out.println("name1="+board.getBoard_name());
			System.out.println("pass1="+board.getBoard_pass());
			System.out.println("subject1="+board.getBoard_subject());
			System.out.println("content1="+board.getBoard_content());
			System.out.println("file1="+board.getBoard_file());			
			
			result=session.insert("board.board_insert", board);
			System.out.println("result="+result);
		}catch(Exception e){
			System.out.println("result="+result);
			System.out.println(e.getMessage());
		}		
	}	
	
	/* 게시판 총 게시물 수 */
	public int getListCount() throws SQLException {
		int count = 0;
		SqlSession session=null;
		session = getSession();
		count = ((Integer) session.selectOne("board.board_count")).intValue();	
		 
		return count;
	}
	
	/* 게시물 목록 */
	public List<BoardBean> getBoardList(int  page)	throws SQLException {
		SqlSession session=null;
		session = getSession();
		List<BoardBean>  list = session.selectList("board.board_list", page);
	    return list;
	}	

	/* 게시판 조회수 증가 */
	public void boardHit(int board_num) throws SQLException {
		SqlSession session=null;
		session = getSession();
		session.update("board.board_hit", board_num);
	}
	
	
	/* 게시판 글내용보기 */
	public BoardBean getBoardCont(int board_num) throws SQLException {
		SqlSession session=null;
		session = getSession();
		return (BoardBean) session.selectOne("board.board_cont", board_num);
	}	

	/* 게시물 수정 */
	public void boardEdit(BoardBean board) throws SQLException {
		SqlSession session=null;
		session = getSession();
		session.update("board.board_edit", board);
	}

	/* 게시물 삭제 */
	public void boardDelete(int board_num) throws SQLException {
		SqlSession session=null;
		session = getSession();
		session.delete("board.board_del", board_num);
	}

	/* 답변글 레벨 증가 */
	public void refEdit(BoardBean board) throws SQLException {
		SqlSession session=null;
		session = getSession();
		session.update("board.board_Level", board);
	}

	/* 답변글 저장 */
	public void boardReplyOk(BoardBean board) throws SQLException {
		SqlSession session=null;
		session = getSession();
		session.insert("board_reply", board);
	}
	
}
