package net.qnaboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.qnaboard.db.QnaBoardBean;
import net.qnaboard.db.QnaBoardDAOImpl;

public class QnaBoardReplyAction implements QnaAction {
	 public QnaActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 throws Exception{
		 	request.setCharacterEncoding("utf-8");
		 	QnaActionForward forward = new QnaActionForward();
		 	
//			BoardDAO1 boarddao=new BoardDAO1();
		 	QnaBoardDAOImpl boarddao=new QnaBoardDAOImpl();
		 	QnaBoardBean boarddata=new QnaBoardBean();
	   		int result=0;
	   		String page = request.getParameter("page");
	   		
	   		boarddata.setBoard_re_ref(Integer.parseInt(request.getParameter("BOARD_RE_REF")));
	   		boarddata.setBoard_re_seq(Integer.parseInt(request.getParameter("BOARD_RE_SEQ")));
	   		
	   		//기존 ?���??�� board_re_seq값을 1증�? ?��?��
	   		boarddao.qnarefEdit(boarddata);
	   		
	   		//?��로운 ?���??�� board_re_lev값과 board_re_seq값을 �?모보?�� 1증�??�� 값을 ?��?��?��?��
	   		boarddata.setBoard_re_lev(Integer.parseInt(request.getParameter("BOARD_RE_LEV"))+1);
	   		boarddata.setBoard_re_seq(Integer.parseInt(request.getParameter("BOARD_RE_SEQ"))+1);
	   		
	   		boarddata.setBoard_num(Integer.parseInt(request.getParameter("BOARD_NUM")));
	   		boarddata.setBoard_name(request.getParameter("BOARD_NAME"));
	   		boarddata.setBoard_pass(request.getParameter("BOARD_PASS"));
	   		boarddata.setBoard_subject(request.getParameter("BOARD_SUBJECT"));
	   		boarddata.setBoard_content(request.getParameter("BOARD_CONTENT"));	   		
	   		
//	   		result=boarddao.boardReply(boarddata);
	   		boarddao.qnaboardReplyOk(boarddata);
//	   		if(result==0){
//	   			System.out.println("?��?�� ?��?��");
//	   			return null;
//	   		}
	   		System.out.println("댓글부대!");
	   		
	   		forward.setRedirect(true);
	   		forward.setPath("./QnaBoardListAction.qo");
//	   		forward.setPath("./QnaBoardDetailAction.qo?num="+boarddata.getBoard_num()+"&page="+page);
//	   		forward.setPath("./BoardDetailAction.bo?num="+result+"&page="+page);
	   		return forward;
	}  	
}