package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import net.board.db.BoardDAO1;
import net.board.db.BoardBean;
import net.board.db.BoardDAOImpl;

public class BoardReplyAction implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 throws Exception{
		 	request.setCharacterEncoding("utf-8");
		 	ActionForward forward = new ActionForward();
		 	
//			BoardDAO1 boarddao=new BoardDAO1();
		 	BoardDAOImpl boarddao=new BoardDAOImpl();
	   		BoardBean boarddata=new BoardBean();
	   		int result=0;
	   		String page = request.getParameter("page");
	   		
	   		boarddata.setBoard_re_ref(Integer.parseInt(request.getParameter("BOARD_RE_REF")));
	   		boarddata.setBoard_re_seq(Integer.parseInt(request.getParameter("BOARD_RE_SEQ")));
	   		
	   		//기존 댓글의 board_re_seq값을 1증가 시킴
	   		boarddao.refEdit(boarddata);
	   		
	   		//새로운 댓글의 board_re_lev값과 board_re_seq값을 부모보다 1증가된 값을 다시할당
	   		boarddata.setBoard_re_lev(Integer.parseInt(request.getParameter("BOARD_RE_LEV"))+1);
	   		boarddata.setBoard_re_seq(Integer.parseInt(request.getParameter("BOARD_RE_SEQ"))+1);
	   		
	   		boarddata.setBoard_num(Integer.parseInt(request.getParameter("BOARD_NUM")));
	   		boarddata.setBoard_name(request.getParameter("BOARD_NAME"));
	   		boarddata.setBoard_pass(request.getParameter("BOARD_PASS"));
	   		boarddata.setBoard_subject(request.getParameter("BOARD_SUBJECT"));
	   		boarddata.setBoard_content(request.getParameter("BOARD_CONTENT"));	   		
	   		
//	   		result=boarddao.boardReply(boarddata);
	   		boarddao.boardReplyOk(boarddata);
//	   		if(result==0){
//	   			System.out.println("답장 실패");
//	   			return null;
//	   		}
	   		System.out.println("답장 완료");
	   		
	   		forward.setRedirect(true);
	   		forward.setPath("./BoardDetailAction.bo?num="+boarddata.getBoard_num()+"&page="+page);
//	   		forward.setPath("./BoardDetailAction.bo?num="+result+"&page="+page);
	   		return forward;
	}  	
}