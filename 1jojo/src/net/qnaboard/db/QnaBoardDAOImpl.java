package net.qnaboard.db;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class QnaBoardDAOImpl {
	
	private SqlSession getSession() {
		SqlSession session=null;
		Reader reader=null;
		try {
			reader = Resources.getResourceAsReader("./QnaSqlMapConfig.xml");
			SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(reader);
			session = sf.openSession(true);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return session;
	}	
	
	
	/* ê²Œì‹œ?Œ ???¥ */
	public void qnaboardInsert(QnaBoardBean qnaboard) {
		SqlSession session=null;
		int result = 0;		
		try{
			session = getSession(); 
			System.out.println("insert1");
			
			System.out.println("name1="+qnaboard.getBoard_name());
			System.out.println("pass1="+qnaboard.getBoard_pass());
			System.out.println("subject1="+qnaboard.getBoard_subject());
			System.out.println("content1="+qnaboard.getBoard_content());
			System.out.println("file1="+qnaboard.getBoard_file());			
			
			result=session.insert("qnaboard.board_insert", qnaboard);
			System.out.println("result="+result);
		}catch(Exception e){
			System.out.println("result="+result);
			System.out.println(e.getMessage());
		}		
	}	
	
	/* ê²Œì‹œ?Œ ì´? ê²Œì‹œë¬? ?ˆ˜ */
	public int qnagetListCount() throws SQLException {
		int count = 0;
		SqlSession session=null;
		session = getSession();
		count = ((Integer) session.selectOne("qnaboard.board_count")).intValue();	
		 
		return count;
	}
	
	/* ê²Œì‹œë¬? ëª©ë¡ */
	public List<QnaBoardBean> qnagetBoardList(int  page)	throws SQLException {
		SqlSession session=null;
		session = getSession();
		@SuppressWarnings("unchecked")
		List<QnaBoardBean>  list = session.selectList("qnaboard.board_list", page);
	    return list;
	}	

	/* ê²Œì‹œ?Œ ì¡°íšŒ?ˆ˜ ì¦ê? */
	public void qnaboardHit(int board_num) throws SQLException {
		SqlSession session=null;
		session = getSession();
		session.update("qnaboard.board_hit", board_num);
	}
	
	
	/* ê²Œì‹œ?Œ ê¸??‚´?š©ë³´ê¸° */
	public QnaBoardBean qnagetBoardCont(int board_num) throws SQLException {
		SqlSession session=null;
		session = getSession();
		return (QnaBoardBean) session.selectOne("qnaboard.board_cont", board_num);
	}	

	/* ê²Œì‹œë¬? ?ˆ˜? • */
	public void qnaboardEdit(QnaBoardBean board) throws SQLException {
		SqlSession session=null;
		session = getSession();
		session.update("qnaboard.board_edit", board);
	}

	/* ê²Œì‹œë¬? ?‚­? œ */
	public void qnaboardDelete(int board_num) throws SQLException {
		SqlSession session=null;
		session = getSession();
		session.delete("qnaboard.board_del", board_num);
	}

	/* ?‹µë³?ê¸? ? ˆë²? ì¦ê? */
	public void qnarefEdit(QnaBoardBean board) throws SQLException {
		SqlSession session=null;
		session = getSession();
		session.update("qnaboard.board_Level", board);
	}

	/* ?‹µë³?ê¸? ???¥ */
	public void qnaboardReplyOk(QnaBoardBean board) throws SQLException {
		SqlSession session=null;
		session = getSession();
		session.insert("qnaboard.board_reply", board);
	}
	
}
