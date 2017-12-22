package net.crawl.db;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CrawlDAOImpl {
	
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
	
	
	/* search list 검색결과 일단 저장 */
	public void search_list_Insert(search_list_Bean search_list) {
		SqlSession session=null;
		int result = 0;		
		try{
			session = getSession(); 
			System.out.println("insert 1");
			
			System.out.println("search_com_No="+search_list.getSearch_com_No());
			System.out.println("com_qual="+search_list.getCom_qual());
			System.out.println("com_preex="+search_list.getCom_preex());
			System.out.println("com_name="+search_list.getCom_name());
			System.out.println("com_link="+search_list.getCom_link());			
//			private int search_com_No;//번호
//			private String com_qual;//업체 지원자격
//			private String com_preex;//업체 우대사항
//			private String com_name;//지원자격포함 기업명
//			private String com_link;//지원자격포함 기업링크
			
			result=session.insert("crawl.crawl_search_list_insert", search_list);
			System.out.println("result="+result);
		}catch(Exception e){
			System.out.println("result="+result);
			System.out.println(e.getMessage());
		}		
	}	
	
	/* search qual 검색결과 일단 저장 */
	public void search_qual_Insert(search_qual_Bean search_qual) {
		SqlSession session=null;
		int result = 0;		
		try{
			session = getSession(); 
			System.out.println("insert 1");
			
			System.out.println("search_com_No="+search_qual.getSearch_com_No());
			System.out.println("com_No="+search_qual.getNo());
			System.out.println("com_qual="+search_qual.getCom_qual());
			System.out.println("com_preex="+search_qual.getCom_preex());
			System.out.println("com_frequncy="+search_qual.getCom_frequency());			
//			private String search_com_No;//번호
//			private int No;//사용자번호
//			private String com_qual;//업체지원자격
//			private String com_preex;//업체우대사항
//			private int com_frequency;//빈도수
			
			result=session.insert("crawl.crawl_search_qual_insert", search_qual);
			System.out.println("result="+result);
		}catch(Exception e){
			System.out.println("result="+result);
			System.out.println(e.getMessage());
		}		
	}	
	
	/* 검색결과 데이터 목록 열람*/
	public ArrayList<search_list_Bean> getSearch_list(String search_com_No)	throws SQLException {
		SqlSession session=null;
		session = getSession();
		ArrayList<search_list_Bean>  list = (ArrayList<search_list_Bean>) session.selectList("crawl.crawl_search_list_select", search_com_No);
	    return list;
	}	
	/* 분석결과 데이터 목록 열람*/
	public ArrayList<search_qual_Bean> getSearch_qual(String search_com_No)	throws SQLException {
		SqlSession session=null;
		session = getSession();
		ArrayList<search_qual_Bean>  list = (ArrayList<search_qual_Bean>) session.selectList("crawl.crawl_search_qual_select", search_com_No);
	    return list;
	}		
	/* 회원별 No로 search_No를 얻어오기. */
	public String getSearch_com_No(String No) {
		SqlSession session=null;
		session = getSession();
		return (String) session.selectOne("crawl.crawl_com_No_select", No);		
	}
	
	/* 검색결과 삭제 */
	public int search_listDelete(String search_com_No) throws SQLException {
		int result=0;
		try{
			SqlSession session=null;
			session = getSession();
			result=session.delete("crawl.search_list_del", search_com_No);
		}catch(Exception e){
			System.out.println("result="+result);
			System.out.println(e.getMessage());
		}		
		return result;
	}
	
	/* 분석결과 삭제 */
	public int search_qualDelete(String search_com_No) throws SQLException {
		int result=0;
		try{
			SqlSession session=null;
			session = getSession();
			result=session.delete("crawl.search_qual_del", search_com_No);
		}catch(Exception e){
			System.out.println("result="+result);
			System.out.println(e.getMessage());
		}	
		return result;
	}

}
