package net.qnaboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.qnaboard.db.QnaBoardBean;
import net.qnaboard.db.QnaBoardDAOImpl;

public class QnaBoardModifyView implements QnaAction {
	 public QnaActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 QnaActionForward forward = new QnaActionForward();
		 	request.setCharacterEncoding("utf-8");
	   		
		 	QnaBoardDAOImpl boarddao=new QnaBoardDAOImpl();
//			BoardDAO boarddao=new BoardDAO();
		 	QnaBoardBean boarddata=new QnaBoardBean();
		   	
			int num=Integer.parseInt(request.getParameter("num"));
			String page = request.getParameter("page");
//		   	boarddata=boarddao.getDetail(num);
			boarddata=boarddao.qnagetBoardCont(num);//�? ?��?�� 
			
		   	if(boarddata==null){
		   		System.out.println("수정 보기 실패");
		   		return null;
		   	}
		   	System.out.println("수정 보기 성공");
		   	
		   	request.setAttribute("boarddata", boarddata);
		    request.setAttribute("page", page);
		   	forward.setRedirect(false);
	   		forward.setPath("./board/qna_board_modify.jsp");
	   		return forward;
	 }
}