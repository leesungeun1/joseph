package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.BoardBean;

@Repository
public class BoardDAOImpl {

	// Reader reader = null;
	// SqlSessionFactory factory = null;
	// SqlSession sqlsession = null;
	//
	// public void getSession() throws Exception {
	// String resource = "util/SqlMapConfig.xml";
	// reader = Resources.getResourceAsReader(resource);
	// factory = new SqlSessionFactoryBuilder().build(reader);
	// sqlsession = factory.openSession(true); // true : auto commit
	// }
	
	@Autowired
	private SqlSession sqlSession;// set이 없이도 값 주입이 가능하지만 중간에 내부적으로 잘 들어갔는 지는 알수 없다.

	/*
	 * public void setSqlSession(SqlSession sqlSession) { this.sqlSession =
	 * sqlSession; }
	 */
	// 스프링 setter DI 의존관계를 설정

	/* 게시판 저장 */
	@Transactional
	public void insertBoard(BoardBean b) throws Exception {
		// getSession();
		sqlSession.insert("Test.board_insert", b);
		// sqlsession.commit();
	}

	// 게시물 목록
	@Transactional
	public List<BoardBean> getBoardList(int page) throws Exception {
		// getSession();
		List<BoardBean> list = sqlSession.selectList("Test.board_list", page);

		return list;
	}

	// 게시판 총 게시물 수
	@Transactional
	public int getListCount() throws Exception {
		int count = 0;

		// getSession();
		count = ((Integer) sqlSession.selectOne("Test.board_count")).intValue();

		return count;
	}

	// 게시판 글내용보기
	@Transactional
	public BoardBean getBoardCont(int board_num) throws Exception {
		// getSession();
		return (BoardBean) sqlSession.selectOne("Test.board_cont", board_num);
	}

	// 게시판 조회수 증가
	@Transactional
	public void boardHit(int board_num) throws Exception {
		// getSession();
		sqlSession.update("Test.board_hit", board_num);
		// sqlsession.commit();
	}

	// 게시물 수정
	@Transactional
	public void boardEdit(BoardBean b) throws Exception {
		// getSession();
		sqlSession.update("Test.board_edit", b);
		// sqlsession.commit();
	}

	// 게시물 삭제
	@Transactional
	public void boardDelete(int board_num) throws Exception {
		// getSession();
		sqlSession.delete("Test.board_del", board_num);
		// sqlsession.commit();
	}

	// 답변글 레벨 증가
	@Transactional
	public void refEdit(BoardBean b) throws Exception {
		// getSession();
		sqlSession.update("Test.board_Level", b);
		// sqlsession.commit();
	}

	// 답변글 저장
	@Transactional
	public void boardReplyOk(BoardBean b) throws Exception {
		// getSession();
		sqlSession.insert("Test.board_reply", b);
		// sqlsession.commit();
	}

}
