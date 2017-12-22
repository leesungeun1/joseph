package net.forumboard.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.forumboard.db.ForumBoardBean;
import net.forumboard.db.ForumBoardDAOImpl;

public class ForumBoardDeleteAction implements ForumAction {
	 public ForumActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 	throws Exception{
		 
		response.setContentType("text/html;charset=utf-8");			
		PrintWriter out=response.getWriter();//출력 ?��?���? 객체?��?��
		 
		ForumActionForward forward = new ForumActionForward();
		request.setCharacterEncoding("utf-8");
		
//	   	boolean result=false;
//	   	boolean usercheck=false;
	   	int num=Integer.parseInt(request.getParameter("num"));
	   	String page = request.getParameter("page");
	   	String pass = request.getParameter("BOARD_PASS");
	   	
	   	ForumBoardDAOImpl boarddao=new ForumBoardDAOImpl();
//	   	BoardDAO1 boarddao=new BoardDAO1();	   	
//	   	usercheck=boarddao.isBoardWriter(num, pass);//	   	
//	   	if(usercheck==false){
//	   		response.setContentType("text/html;charset=utf-8");
//	   		PrintWriter out=response.getWriter();
//	   		out.println("<script>");
//	   		out.println("alert('?��?��?�� 권한?�� ?��?��?��?��.');");
////	   		out.println("location.href='./BoardList.bo';");
//	   		out.println("history.go(-1)");
//	   		out.println("</script>");
//	   		out.close();
//	   		return null;
//	   	}
	   	
	   	ForumBoardBean board = boarddao.forumgetBoardCont(num);
	   	if(!board.getBoard_pass().equals(pass)){ //비번?�� ?��치하�? ?��?�� 경우
	   		out.println("<script>");
			out.println("alert('비버니다름다')");
			out.println("history.go(-1)");
			out.println("</script>");
	   		out.close();
	   		return null;
	   		
	   	}else{   	
	   	
//	   	    result=boarddao.boardDelete(num);
	   		boarddao.forumboardDelete(num);
	   	}// else end
	   	
//	   	if(result==false){
//	   		System.out.println("게시?�� ?��?�� ?��?��");
//	   		return null;
//	   	}
	   	
//	   	System.out.println("게시?�� ?��?�� ?���?");
//	   	System.out.println("page="+page);
	   	
	   	forward.setRedirect(true);
	   	forward.setPath("./ForumBoardListAction.fo?page="+page);
	   	return forward;	   	
	   	
	 }
}