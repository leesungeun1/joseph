package net.crawl.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import net.board.action.Action;
import net.board.action.ActionForward;
import net.board.db.BoardDAOImpl;
import net.crawl.db.CrawlDAOImpl;
import net.crawl.db.search_list_Bean;
import net.crawl.db.search_qual_Bean;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class CrawlAnalysisAction implements Action {
//	CrawlDAOImpl crawldao = new CrawlDAOImpl();

	public ArrayList<String> removeCategory(String targetText) {
		String[] tempText = targetText.split("-"); // �씪�떒 臾몄옄�뿴�쓣 -湲곗��쑝濡� �굹�늿�떎. 鍮덇납�� 怨좊젮 �븡�뒗�떎.
//		String regex1 = "^[媛�-�옡]*$"; // check category
//		String regex2 = "^-\\s\\S*\\s:\\s$"; // check item
		ArrayList<String> listOfToken= new ArrayList<String>();
		for (int i = 0; i < tempText.length; i++) {
			String s = tempText[i];
			 System.out.println("itme : " + s);
//			if(s.contains(":")) {
//				listOfToken.add(s.substring(s.indexOf(":")+1).trim());
//				continue;
//			}
			 if(s.equals("")) {//鍮� 寃껋씠 媛��걫 �뱾�뼱�삩�떎. -濡� �굹�댋�쓣 �븣 �븵遺�遺꾩쓽 怨듬갚�쓣 �쓽誘명븯�뒗 寃� 媛숇떎.
				 continue;
			 }
			String tokens[]=null;
			if(s.contains(",")) {
				tokens = s.split(",");
				for(String token: tokens) {
					System.out.println(token);
					if(s.contains(":")) {
						listOfToken.add(s.substring(0, s.indexOf(": "))+token.trim());
					}else {
						listOfToken.add(token.trim());
					}
					continue;
				}
			}
			listOfToken.add(s.trim());
		}
		return listOfToken;
	}

	@SuppressWarnings("unchecked")
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CrawlAnalysisAction");
		ActionForward forward = new ActionForward();
//		CrawlDAOImpl crawldao=new CrawlDAOImpl();

		try {
			response.setContentType("text/html; charset=utf-8");//異�
			request.setCharacterEncoding("utf-8");//�엯
			ArrayList<search_list_Bean> listOfsearch_list= new ArrayList<search_list_Bean>();
//			**************load test**************
//			System.out.println( request.getAttribute("search_list")) ;//request.setAttribute濡� �븯硫� request媛� �궡�븘 �엳�뒗 �룞�븞留� 癒뱁엺�떎. session.setAttribute濡� �빐�빞 �떎瑜� �럹�씠吏��뿉�꽌�룄 �뜥癒뱀쓣 �닔 �엳�떎.
//			System.out.println("search_list_count = "+  request.getSession().getAttribute("search_list_count")) ;
			System.out.println(request.getSession().getAttribute("search_list"));//媛앹껜媛� �떞寃� �엳�뒗吏� �뿬遺� �솗�씤.
			HttpSession session = request.getSession();
			listOfsearch_list= (ArrayList<search_list_Bean>) session.getAttribute("search_list");
//			**************load test**************
			
			/**************�떒�뼱異붿텧 諛� 鍮덈룄 �꽭湲�*************/
			String totalQual="";
			String totalPreex="";
			for(search_list_Bean listData : listOfsearch_list) {
				totalQual  +=listData.getCom_qual();
				totalPreex +=listData.getCom_preex();
			}
			ArrayList<String> listOfToken= new ArrayList<String>();
			listOfToken = removeCategory(totalQual);//�쑀�쓽誘명븳 �떒�뼱留� 蹂꾨룄 異붿텧
//			System.out.println(listOfToken);
			HashMap<String, Integer> countPerWord_Qual = new HashMap<String, Integer>();//�떒�뼱, 鍮덈룄
			for (String word : listOfToken) {
				if(countPerWord_Qual.get(word)==null) {
					countPerWord_Qual.put(word, 1);
				}else {
					countPerWord_Qual.put(word, countPerWord_Qual.get(word)+1);
				}				
			}
			listOfToken = removeCategory(totalPreex);//�쑀�쓽誘명븳 �떒�뼱留� 蹂꾨룄 異붿텧. listOfToken�� �옱�솢�슜 �빐�꽌 臾몄젣�엳�뒗吏� �솗�씤 諛붾엺. �뼍�� 蹂듭궗�땲源� 臾몄젣�뒗 �뾾�뼱蹂댁씠吏�留� �샊�떆 紐⑤Ⅸ�떎.
//			System.out.println(listOfToken);
			HashMap<String, Integer> countPerWord_Preex = new HashMap<String, Integer>();//�떒�뼱, 鍮덈룄
			for (String word : listOfToken) {
				if(countPerWord_Preex.get(word)==null) {
					countPerWord_Preex.put(word, 1);
				}else {
					countPerWord_Preex.put(word, countPerWord_Preex.get(word)+1);
				}				
			}
			/**************�떒�뼱異붿텧 諛� 鍮덈룄 �꽭湲�**************/

//			search_com_No NUMBER,
//			No NUMBER, //�씪�떒 �뿰�룞�씠 �뼱�뒓�젙�룄 留덈Т由щ릺硫� �븳�떎.
//			com_qual VARCHAR2(1000),
//			com_preex VARCHAR2(1000),
//			com_frequency NUMBER,
			String search_com_No = listOfsearch_list.get(0).getSearch_com_No();
		    Iterator<String> iterator1 = countPerWord_Qual.keySet().iterator();
			ArrayList<search_qual_Bean> listOfsearch_qual_Bean= new ArrayList<search_qual_Bean>();
			int no=(int) session.getAttribute("no");
			System.out.println(countPerWord_Qual);
		    while (iterator1.hasNext()) {
		    	String key = (String) iterator1.next();
		    	search_qual_Bean qual = new search_qual_Bean();
				qual.setSearch_com_No(search_com_No);
				qual.setNo(no);
				qual.setCom_preex("");
				qual.setCom_qual(key);
				qual.setCom_frequency(countPerWord_Qual.get(key));
				listOfsearch_qual_Bean.add(qual);
		    }
		    System.out.println(countPerWord_Preex);
		    Iterator<String> iterator2 = countPerWord_Preex.keySet().iterator();
		    while (iterator2.hasNext()) {
		    	String key = (String) iterator2.next();
		    	search_qual_Bean preex = new search_qual_Bean();
		    	preex.setSearch_com_No(search_com_No);
		    	preex.setNo(no);
		    	preex.setCom_preex(key);
		    	preex.setCom_qual("");
		    	preex.setCom_frequency(countPerWord_Preex.get(key));
		    	listOfsearch_qual_Bean.add(preex);
		    }
		    
//		    for(search_qual_Bean test : listOfsearch_qual_Bean) {
//		    	System.out.println(test.getCom_preex());
//		    	System.out.println(test.getCom_qual());
//		    }
		    
			session.setAttribute("cwl_qualAndpreex_analysis_result", listOfsearch_qual_Bean); //寃��깋寃곌낵 由ъ뒪�듃瑜� �꽭�뀡�쑝濡� �쟾�넚
//			session.setAttribute("search_qual_preex_count", countPerWord_Preex.size()); //寃��깋寃곌낵 �겕湲곕�� �꽭�뀡�쑝濡� �쟾�넚
//			session.setAttribute("search_qual_qual_count", countPerWord_Qual.size() ); //寃��깋寃곌낵 �겕湲곕�� �꽭�뀡�쑝濡� �쟾�넚
			System.out.println("session updated");

			forward.setRedirect(false);
			forward.setPath("/cwl_qualAndpreex_analysis_result.cr");
			return forward;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("臾몄젣媛� �깮寃쇰떎. alert!");
			PrintWriter out =response.getWriter();
			out.println("<script>");
			out.println("alert('�떒�뼱遺꾩꽍 怨쇱젙�뿉�꽌 臾몄젣媛� �깮寃쇱뒿�땲�떎. �뿉�윭 �궡�슜 : "+ex+"');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
		}
		return null;
	}
}