package net.qnaboard.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.qnaboard.db.QnaBoardBean;
import net.qnaboard.db.QnaBoardDAOImpl;

public class QnaBoardDeleteAction implements QnaAction {
	 public QnaActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 	throws Exception{
		 
		response.setContentType("text/html;charset=utf-8");			
		PrintWriter out=response.getWriter();//출력 ?��?���? 객체?��?��
		 
		QnaActionForward forward = new QnaActionForward();
		request.setCharacterEncoding("utf-8");
		
//	   	boolean result=false;
//	   	boolean usercheck=false;
	   	int num=Integer.parseInt(request.getParameter("num"));
	   	String page = request.getParameter("page");
	   	String pass = request.getParameter("BOARD_PASS");
	   	
	   	QnaBoardDAOImpl boarddao=new QnaBoardDAOImpl();
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
	   	
	   	QnaBoardBean board = boarddao.qnagetBoardCont(num);
	   	if(!board.getBoard_pass().equals(pass)){ //비번?�� ?��치하�? ?��?�� 경우
	   		out.println("<script>");
			out.println("alert('비버니다름다')");
			out.println("history.go(-1)");
			out.println("</script>");
	   		out.close();
	   		return null;
	   		
	   	}else{   	
	   	
//	   	    result=boarddao.boardDelete(num);
	   		boarddao.qnaboardDelete(num);
	   	}// else end
	   	
//	   	if(result==false){
//	   		System.out.println("게시?�� ?��?�� ?��?��");
//	   		return null;
//	   	}
	   	
//	   	System.out.println("게시?�� ?��?�� ?���?");
//	   	System.out.println("page="+page);
	   	
	   	forward.setRedirect(true);
	   	forward.setPath("./QnaBoardListAction.qo?page="+page);
	   	return forward;	   	
	   	
	 }
}