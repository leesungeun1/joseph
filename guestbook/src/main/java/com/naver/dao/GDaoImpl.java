package com.naver.dao;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.naver.model.ComBean;
import com.naver.model.GBean;

public class GDaoImpl implements GDao {

	Reader reader = null;
	SqlSessionFactory factory = null;
	SqlSession sqlsession = null;

	public void getSession() throws Exception {
		String resource = "com/naver/mybatis-config.xml";
		reader = Resources.getResourceAsReader(resource);
		factory = new SqlSessionFactoryBuilder().build(reader);
		sqlsession = factory.openSession(true); // true : auto commit
	}

	// private SqlSession sqlSession;//mybatis 쿼리문 실행 변수

	// public void setSqlSession(SqlSession sqlSession) {
	// this.sqlSession = sqlSession;
	// }//스프링 setter DI 의존관계를 설정

	/*
	 * mybatis 쿼리문 실행 메서드 1.단 한개의 레코드만 검색: selectOne() 2.하나이상의 레코드를 검색해서 컬렉션 List로
	 * 반환:selectList() 3.레코드 저장:insert() 4.레코드 수정:update() 5.레코드 삭제:delete()
	 */
	public List<GBean> getGList() {
		try {
			getSession();
			// return sqlsession.selectList("Gt.g_list");
		} catch (Exception e) {
		}
		return sqlsession.selectList("Gt.g_list");
		// Gt는 네임 스페이스 이름, g_list는 select 아이디 이름
	}

	/* 방명록 내용 */
	public GBean getCont(int g_no) {
		try {
			getSession();
		} catch (Exception e) {
		}
		return this.sqlsession.selectOne("Gt.g_cont", g_no);
	}

	/* 댓글 저장 */
	public void insertCom(ComBean c) {
		try {
			getSession();
		} catch (Exception e) {
		}
		this.sqlsession.insert("Gt.com_in", c);
	}

	// 댓글 개수
	public int getComCount(int g_no) {
		try {
			getSession();
		} catch (Exception e) {
		}
		return this.sqlsession.selectOne("Gt.c_count", g_no);
	}

	// 방명록 조회수 증가
	public void updatehit(int g_no) {
		try {
			getSession();
		} catch (Exception e) {
		}
		this.sqlsession.update("Gt.g_hit", g_no);
		// Gt는 네임스페이스 이름, g_hit는 update id 이름
	}

	/* 댓글목록 */
	public List<ComBean> getComList(int g_no) {
		try {
			getSession();
		} catch (Exception e) {
		}
		return this.sqlsession.selectList("Gt.c_list", g_no);
	}

	// 댓글 삭제
	public void com_del(int comment_no) {
		try {
			getSession();
		} catch (Exception e) {
		}
		this.sqlsession.delete("Gt.c_del", comment_no);
		// Gt는 네임스페이스 이름,c_del은 delete 아이디이름
	}

	/* 원문 저장 */
	public void insertG(ComBean c) {
		try {
			getSession();
		} catch (Exception e) {
		}
		this.sqlsession.insert("Gt.g_in", c);
	}

	/* 방명록 수정 */
	public void updateG(ComBean ec) {
		try {
			getSession();
		} catch (Exception e) {
		}
		this.sqlsession.update("Gt.g_edit", ec);
	}

	/* 방명록 삭제 */
	public void delG(int g_no) {
		try {
			getSession();
		} catch (Exception e) {
		}
		this.sqlsession.delete("Gt.g_del", g_no);
	}
}
