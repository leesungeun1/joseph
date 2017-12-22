package net.crawl.action;

import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.board.action.Action;
import net.board.action.ActionForward;
//import net.board.db.BoardDAO1;
import net.board.db.BoardDAOImpl;
import net.crawl.db.CrawlDAOImpl;
import net.crawl.db.search_list_Bean;
import net.crawl.db.search_qual_Bean;

 public class CrawlDeleteSavedDataAction implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		System.out.println("CrawlDeleteSavedDataAction");
//		ArrayList<search_list_Bean> listOfResult= new ArrayList<search_list_Bean>();
//		ArrayList<search_qual_Bean> listOfsearch_qual_Bean= new ArrayList<search_qual_Bean>();
		response.setContentType("text/html; charset=utf-8");//출
		request.setCharacterEncoding("utf-8");//입
		try {
		CrawlDAOImpl crawldao=new CrawlDAOImpl();
		System.out.println("no="+session.getAttribute("no"));
		int no=(int)session.getAttribute("no");
		String No = Integer.toString(no);
		String search_com_No=crawldao.getSearch_com_No(No);
		int delete_list_result=0;
		int delete_qual_result=0;
		
		delete_list_result=crawldao.search_listDelete(search_com_No);
		System.out.println("delete_list_result="+delete_list_result);
		delete_qual_result=crawldao.search_qualDelete(search_com_No);
		System.out.println("delete_qual_result="+delete_qual_result);
		
		System.out.println("delete done?"+delete_list_result+","+delete_qual_result);
		ActionForward forward= new ActionForward();
		if(delete_qual_result>0 || delete_list_result>0) {
			forward.setRedirect(false);
			forward.setPath("./member/myPage.jsp");
			System.out.println("지워짐. search_com_No="+search_com_No);
			return forward;
		}else {
			throw new Exception("DB에 지워야할 당신의 데이터가 없습니다.");
		}
		
		}catch(Exception ex){
   			ex.printStackTrace();
			System.out.println("문제가 생겼다. alert!");
			PrintWriter out =response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 과정에서 문제가 생겼습니다. 에러 내용 : "+ex.getMessage()+"');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
   		}
  		return null;
	 }
 }