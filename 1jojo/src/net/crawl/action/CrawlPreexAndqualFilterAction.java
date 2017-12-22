package net.crawl.action;

import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.board.action.Action;
import net.board.action.ActionForward;
import net.crawl.db.search_list_Bean;


 public class CrawlPreexAndqualFilterAction implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 System.out.println("CrawlPreexAndqualFilterAction");
		ArrayList<search_list_Bean> filteredList=new ArrayList<search_list_Bean>();
		ArrayList<search_list_Bean> searchList=new ArrayList<search_list_Bean>();
		String words = null; 
		try {
			response.setContentType("text/html; charset=utf-8");//출
			request.setCharacterEncoding("utf-8");//입
			
			if(request.getParameter("word") != null){//word가 없이 넘어온다면... 복잡해지니 일단 따로 기능은 넣지 말자.
				words=(String)(request.getParameter("word"));// 넘어온 단어들 구분자는 word= ... &word= 같은 형식
				System.out.println(words);//check
			}
			String[] temp=words.split("-");
//			for (int j = 0; j < temp.length; j++) {
//				System.out.println(temp[j]);
//			}
			HttpSession session = request.getSession();
			System.out.println(session.getMaxInactiveInterval() / 60);
			System.out.println("getSession"); //check point
			searchList=(ArrayList<search_list_Bean>) session.getAttribute("search_list");
			System.out.println("getsearchList"+searchList.isEmpty());//check point
			ArrayList<Double> matchRatio=new ArrayList<Double>();
			for(search_list_Bean searchData : searchList) {
				int cnt=0;
				for(String word : temp) {
					if( !filteredList.contains(searchData) &&( searchData.getCom_preex().contains(word) || searchData.getCom_qual().contains(word))) {// 하나씩갖고 있는지. 검증.
						filteredList.add(searchData);
						cnt++;
					}else if( searchData.getCom_preex().contains(word) || searchData.getCom_qual().contains(word) ) {
						cnt++;
					}
				}
				if(cnt !=0) {
					matchRatio.add((double)cnt/temp.length );// search_list_Bean객체
				}
			}
			System.out.println(filteredList);
			System.out.println(matchRatio);
			session.setAttribute("filteredList", filteredList); //리스트
			session.setAttribute("matchRatio", matchRatio); //일치도
			session.setAttribute("words", words.replaceAll("-", ", "));
			//추후 DB에 저장할 것도 이거다. 지원자격 또는 우대사항 둘중하나만 저장할지, 어떨지를 지정해야하는데 일단은 그냥 하나만 하게 냅두자.
			
			ActionForward forward= new ActionForward();
		 	forward.setRedirect(false);	
	 		forward.setPath("/cwl_filtered_qualAndpreex_list.cr");
	 		return forward;
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("오류발생!");
			PrintWriter out =response.getWriter();
			out.println("<script>");
			out.println("alert('오류가 발생했습니다."+ex+"' );");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
		}
		return null;
	 }
 }