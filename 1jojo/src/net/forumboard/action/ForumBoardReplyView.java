package net.forumboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import net.board.db.BoardDAO1;
import net.forumboard.db.ForumBoardBean;
import net.forumboard.db.ForumBoardDAOImpl;

public class ForumBoardReplyView implements ForumAction {
	public ForumActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		 	ForumActionForward forward = new ForumActionForward();
		 	
//			BoardDAO1 boarddao=new BoardDAO1();
		 	ForumBoardDAOImpl boarddao=new ForumBoardDAOImpl();
		 	ForumBoardBean boarddata=new ForumBoardBean();
	   		
	   		int num=Integer.parseInt(request.getParameter("num"));
	   		String page = request.getParameter("page");
	   		
//	   		boarddata=boarddao.getDetail(num);
	   		boarddata=boarddao.forumgetBoardCont(num);
	   		
	   		if(boarddata==null){
	   			System.out.println("리플 페이지 진입 실패!");
	   			return null;
	   		}
	   		System.out.println("리플 페이지 진입 성공!");
	   		
	   		request.setAttribute("boarddata", boarddata);
	   		request.setAttribute("page", page);
	   		
	   		forward.setRedirect(false);
	   		forward.setPath("./board/forum_board_reply.jsp");
	   		return forward;
	}

}