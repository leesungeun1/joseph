package dao;

import java.io.Reader;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import model.MemberBean;
import model.ZipcodeBean2;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl {
	
	@Autowired
	private SqlSession sqlsession;

//	Reader reader = null;
//	SqlSessionFactory factory = null;
//	SqlSession sqlsession = null;
//
//	public void getSession() throws Exception {
//		String resource = "util/SqlMapConfig.xml";
//		reader = Resources.getResourceAsReader(resource);
//		factory = new SqlSessionFactoryBuilder().build(reader);
//		sqlsession = factory.openSession(true); // true : auto commit
//	}

	/***** 아이디 중복 체크 *****/
	public int checkMemberId(String id) throws Exception {
//		getSession();
		int re = -1;
		MemberBean mb = (MemberBean) sqlsession.selectOne("login_check", id);
		if (mb != null)
			re = 1; // 중복id
		return re;
	}

	/* 우편 검색 */
	public List<ZipcodeBean2> findZipcode(String dong) throws Exception {
//		getSession();
		List<ZipcodeBean2> list = sqlsession.selectList("zipcodeList", dong);
		// 월말평가:디비로부터 한개 이상 레코드를 검색 할때 사용하는 ibatis 메서드:queryForList()
		// 디비로 부터 한개 레코드 검색할때 사용하는 ibatis 메서드:queryForObject()
		// 레코드 삽입할때 사용하는 ibatis메서드:insert()
		// 레코드 수정할때 사용하는 ibatis메서드:update()
		// 레코드 삭제할때 사용하는 ibatis메서드:delete()
		return list;
	}

	/* 비번 검색 */
	public MemberBean findpwd(Map pm) throws Exception {
//		getSession();
		return (MemberBean) sqlsession.selectOne("pwd_find", pm);
	}

	/* 회원저장 */
	public void insertMember(MemberBean m) throws Exception {
//		getSession();
		sqlsession.insert("member_join", m);
	}

	/* 로그인 인증 체크 */
	public MemberBean userCheck(String id) throws Exception {
//		getSession();
		return (MemberBean) sqlsession.selectOne("login_check", id);
	}

	/* 회원수정 */
	public void updateMember(MemberBean member) throws Exception {
//		getSession();
		sqlsession.update("member_edit", member);
	}

	/* 회원삭제 */
	public void deleteMember(MemberBean delm) throws Exception {
//		getSession();
		sqlsession.update("member_delete", delm);
	}
}
