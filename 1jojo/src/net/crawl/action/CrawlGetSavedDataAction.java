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

 public class CrawlGetSavedDataAction implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		System.out.println("CrawlGetSavedDataAction");
		ArrayList<search_list_Bean> listOfResult= new ArrayList<search_list_Bean>();
		ArrayList<search_qual_Bean> listOfsearch_qual_Bean= new ArrayList<search_qual_Bean>();
		response.setContentType("text/html; charset=utf-8");//출
		request.setCharacterEncoding("utf-8");//입
		try {
			CrawlDAOImpl crawldao=new CrawlDAOImpl();
			System.out.println("no="+session.getAttribute("no"));
			int no=(int)session.getAttribute("no");
			String No = Integer.toString(no);
			String search_com_No=crawldao.getSearch_com_No(No);
			if(search_com_No==null){
				throw new Exception("DB에 당신의 데이터가 없습니다.");
			}
			System.out.println("search_com_No="+search_com_No);
			listOfResult=crawldao.getSearch_list(search_com_No);
			listOfsearch_qual_Bean=crawldao.getSearch_qual(search_com_No);
			
	 		session.setAttribute("search_list", listOfResult); //현재 페이지 수
	 		session.setAttribute("cwl_qualAndpreex_analysis_result", listOfsearch_qual_Bean); //최대 페이지 수
	 		session.setAttribute("search_com_No", search_com_No ); 
	 		session.setAttribute("search_list_count", listOfResult.size());
	 		
			ActionForward forward= new ActionForward();
		 	forward.setRedirect(false);
	 		forward.setPath("/cwl_result.cr");
	 		return forward;
	 	} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("문제가 생겼다. alert!");
			PrintWriter out =response.getWriter();
			out.println("<script>");
			out.println("alert('DB에서 데이터를 끌어오는 과정에서 문제가 생겼습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
		}
		return null;
	 }
 }