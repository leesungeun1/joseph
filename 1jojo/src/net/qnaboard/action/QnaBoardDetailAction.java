package net.qnaboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.qnaboard.db.QnaBoardBean;
import net.qnaboard.db.QnaBoardDAOImpl;

 public class QnaBoardDetailAction implements QnaAction {
	 public QnaActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
		request.setCharacterEncoding("utf-8");
   		
		QnaBoardDAOImpl boarddao=new QnaBoardDAOImpl();
//		BoardDAO boarddao=new BoardDAO();
		QnaBoardBean boarddata=new QnaBoardBean();
	   	
		int num=Integer.parseInt(request.getParameter("num"));
		String page = request.getParameter("page");

//		boarddao.setReadCountUpdate(num);		
//	   	boarddata=boarddao.getDetail(num);
		boarddao.qnaboardHit(num); //조회?�� 증�?
		boarddata=boarddao.qnagetBoardCont(num);//?��?��보기
	   	
	   	if(boarddata==null){
	   		System.out.println("보기 실패");
	   		return null;
	   	}
	   	System.out.println("보자!");
	   	
	   	request.setAttribute("boarddata", boarddata);
	   	request.setAttribute("page", page);
	   
	   	QnaActionForward forward = new QnaActionForward();
	   	forward.setRedirect(false);
   		forward.setPath("./board/qna_board_view.jsp");
   		return forward;

	 }
}