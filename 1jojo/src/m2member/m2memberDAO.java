package m2member;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class m2memberDAO {

private static m2memberDAO instance = new m2memberDAO();
	
	public static m2memberDAO getInstance() {
		return instance;
	}
	
	private SqlSession getSession() {
		SqlSession session = null;
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("SqlMapConfig.xml");
			SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(reader);
			session = sf.openSession(true);// auto commit
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return session;
	}
	
	//	�쉶�썝 媛��엯
	public int insert(m2memberDTO member) {
		int result = 0;
		SqlSession session = null;
		try {
			session = getSession();
			result = (int) session.insert("insert", member);
			// return 占쏙옙占쏙옙占� object -> integer占쏙옙 占쏙옙환
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	

	public int IdCheck(String id) {
		int result = 0;
		SqlSession session = null;
		try {
			session = getSession();
			m2memberDTO mem = (m2memberDTO) session.selectOne("select", id);
			if (mem.getId().equals(id)) {
				result = 1;
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public int chk(m2memberDTO member) {
		int result = 0;
		SqlSession session = null;
		try {
			session = getSession();
			m2memberDTO mem = (m2memberDTO) session.selectOne("select", member.getId());
			if (mem.getId().equals(member.getId())) {
				result = -1;
				if (mem.getPass().equals(member.getPass())) {
					result = 1;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public int chk_write(m2memberDTO member) {
		int result = 0;
		SqlSession session = null;
		try {session = getSession();
		m2memberDTO mem = (m2memberDTO) session.selectOne("select", member.getId());
		if(mem.getId().equals(member.getId())) {
		result = 1;	
		}		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public m2memberDTO select(String id) throws SQLException {
		m2memberDTO mem = null;
		SqlSession session = null;
		try {
			session = getSession();
			mem = (m2memberDTO) session.selectOne("select", id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return mem;
	}

	public List<m2memberDTO> list() {
		List<m2memberDTO> list = new ArrayList<m2memberDTO>();
		SqlSession session = null;
		try {
			session = getSession();
			list = session.selectList("list");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

	public int delete(String id) {
		int result = 0;
		SqlSession session = null;
		try {
			session = getSession();
			result = session.delete("delete", id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public int update(m2memberDTO mem, String npass) {
		int result = 0;
		SqlSession session = null;
		try {
			session = getSession();
			
			m2memberDTO member = (m2memberDTO) session.selectOne("select", mem.getId());
			if(member != null) {
				if(member.getPass().equals(mem.getPass())) {		// 鍮꾨쾲 �씪移�
					Map m = new HashMap();
					m.put("id", mem.getId() );
					m.put("npass", npass);
					
					result = session.update("update", m);
				}else {		// 鍮꾨쾲 遺덉씪移�
					result = -1;
				}
				
			}			
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
}
