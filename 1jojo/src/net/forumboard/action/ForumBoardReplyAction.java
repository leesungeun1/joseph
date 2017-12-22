package net.forumboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.forumboard.db.ForumBoardBean;
import net.forumboard.db.ForumBoardDAOImpl;

public class ForumBoardReplyAction implements ForumAction {
	 public ForumActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 throws Exception{
		 	request.setCharacterEncoding("utf-8");
		 	ForumActionForward forward = new ForumActionForward();
		 	
//			BoardDAO1 boarddao=new BoardDAO1();
		 	ForumBoardDAOImpl boarddao=new ForumBoardDAOImpl();
		 	ForumBoardBean boarddata=new ForumBoardBean();
	   		int result=0;
	   		String page = request.getParameter("page");
	   		
	   		boarddata.setBoard_re_ref(Integer.parseInt(request.getParameter("BOARD_RE_REF")));
	   		boarddata.setBoard_re_seq(Integer.parseInt(request.getParameter("BOARD_RE_SEQ")));
	   		
	   		//기존 ?���??�� board_re_seq값을 1증�? ?��?��
	   		boarddao.forumrefEdit(boarddata);
	   		
	   		//?��로운 ?���??�� board_re_lev값과 board_re_seq값을 �?모보?�� 1증�??�� 값을 ?��?��?��?��
	   		boarddata.setBoard_re_lev(Integer.parseInt(request.getParameter("BOARD_RE_LEV"))+1);
	   		boarddata.setBoard_re_seq(Integer.parseInt(request.getParameter("BOARD_RE_SEQ"))+1);
	   		
	   		boarddata.setBoard_num(Integer.parseInt(request.getParameter("BOARD_NUM")));
	   		boarddata.setBoard_name(request.getParameter("BOARD_NAME"));
	   		boarddata.setBoard_pass(request.getParameter("BOARD_PASS"));
	   		boarddata.setBoard_subject(request.getParameter("BOARD_SUBJECT"));
	   		boarddata.setBoard_content(request.getParameter("BOARD_CONTENT"));	   		
	   		
//	   		result=boarddao.boardReply(boarddata);
	   		boarddao.forumboardReplyOk(boarddata);
//	   		if(result==0){
//	   			System.out.println("?��?�� ?��?��");
//	   			return null;
//	   		}
	   		System.out.println("댓글부대!");
	   		
	   		forward.setRedirect(true);
	   		forward.setPath("./ForumBoardListAction.fo");
//	   		forward.setPath("./ForumBoardDetailAction.fo?num="+boarddata.getBoard_num()+"&page="+page);
//	   		forward.setPath("./BoardDetailAction.bo?num="+result+"&page="+page);
	   		return forward;
	}  	
}