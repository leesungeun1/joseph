package net.qnaboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import net.board.db.BoardDAO1;
import net.qnaboard.db.QnaBoardBean;
import net.qnaboard.db.QnaBoardDAOImpl;

public class QnaBoardReplyView implements QnaAction {
	public QnaActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		 	QnaActionForward forward = new QnaActionForward();
		 	
//			BoardDAO1 boarddao=new BoardDAO1();
		 	QnaBoardDAOImpl boarddao=new QnaBoardDAOImpl();
		 	QnaBoardBean boarddata=new QnaBoardBean();
	   		
	   		int num=Integer.parseInt(request.getParameter("num"));
	   		String page = request.getParameter("page");
	   		
//	   		boarddata=boarddao.getDetail(num);
	   		boarddata=boarddao.qnagetBoardCont(num);
	   		
	   		if(boarddata==null){
	   			System.out.println("리플 페이지 진입 실패!");
	   			return null;
	   		}
	   		System.out.println("리플 페이지 진입 성공!");
	   		
	   		request.setAttribute("boarddata", boarddata);
	   		request.setAttribute("page", page);
	   		
	   		forward.setRedirect(false);
	   		forward.setPath("./board/qna_board_reply.jsp");
	   		return forward;
	}

}