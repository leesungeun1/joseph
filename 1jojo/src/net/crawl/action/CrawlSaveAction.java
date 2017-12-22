package net.crawl.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.board.action.Action;
import net.board.action.ActionForward;
import net.crawl.db.CrawlDAOImpl;
import net.crawl.db.search_list_Bean;
import net.crawl.db.search_qual_Bean;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class CrawlSaveAction implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		System.out.println("CrawlSaveAction");
		 CrawlDAOImpl crawldao=new CrawlDAOImpl();
		search_list_Bean crawldata=new search_list_Bean();
		
	   	ActionForward forward=new ActionForward();
   		boolean result=false;
   		
   		try{
   			HttpSession session = request.getSession();
   			ArrayList<search_list_Bean> listOfResult= new ArrayList<search_list_Bean>();
   			listOfResult = (ArrayList<search_list_Bean>) session.getAttribute("search_list");
   			for(search_list_Bean listData : listOfResult) {
				crawldao.search_list_Insert(listData);
   			}
   			
			ArrayList<search_qual_Bean> listOfsearch_qual_Bean= new ArrayList<search_qual_Bean>();
			listOfsearch_qual_Bean = (ArrayList<search_qual_Bean>) session.getAttribute("cwl_qualAndpreex_analysis_result");
   			for(search_qual_Bean listData : listOfsearch_qual_Bean) {
				crawldao.search_qual_Insert(listData);
   			}
//	   		result=crawldao.boardInsert(boarddata);
	   		/*if(result==false){
	   			System.out.println("게시판 등록 실패");
	   			return null;
	   		}
	   		System.out.println("게시판 등록 완료");*/
	   		
	   		forward.setRedirect(true);
	   		forward.setPath("./member/myPage.jsp");
	   		return forward;
	   		
  		}catch(Exception ex){
   			ex.printStackTrace();
			System.out.println("문제가 생겼다. alert!");
			PrintWriter out =response.getWriter();
			out.println("<script>");
			out.println("alert('저장 과정에서 문제가 생겼습니다. 에러 내용 : \n"+ex+"');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
   		}
  		return null;
	}  	
}