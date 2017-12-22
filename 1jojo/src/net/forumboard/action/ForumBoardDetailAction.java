package net.forumboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.forumboard.db.ForumBoardBean;
import net.forumboard.db.ForumBoardDAOImpl;

 public class ForumBoardDetailAction implements ForumAction {
	 public ForumActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
		request.setCharacterEncoding("utf-8");
   		
		ForumBoardDAOImpl boarddao=new ForumBoardDAOImpl();
//		BoardDAO boarddao=new BoardDAO();
		ForumBoardBean boarddata=new ForumBoardBean();
	   	
		int num=Integer.parseInt(request.getParameter("num"));
		String page = request.getParameter("page");

//		boarddao.setReadCountUpdate(num);		
//	   	boarddata=boarddao.getDetail(num);
		boarddao.forumboardHit(num); //조회?�� 증�?
		boarddata=boarddao.forumgetBoardCont(num);//?��?��보기
	   	
	   	if(boarddata==null){
	   		System.out.println("보기 실패");
	   		return null;
	   	}
	   	System.out.println("보자!");
	   	
	   	request.setAttribute("boarddata", boarddata);
	   	request.setAttribute("page", page);
	   
	   	ForumActionForward forward = new ForumActionForward();
	   	forward.setRedirect(false);
   		forward.setPath("./board/forum_board_view.jsp");
   		return forward;

	 }
}