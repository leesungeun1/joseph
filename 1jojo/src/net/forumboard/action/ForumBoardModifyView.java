package net.forumboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.forumboard.db.ForumBoardBean;
import net.forumboard.db.ForumBoardDAOImpl;

public class ForumBoardModifyView implements ForumAction {
	 public ForumActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 ForumActionForward forward = new ForumActionForward();
		 	request.setCharacterEncoding("utf-8");
	   		
		 	ForumBoardDAOImpl boarddao=new ForumBoardDAOImpl();
//			BoardDAO boarddao=new BoardDAO();
		 	ForumBoardBean boarddata=new ForumBoardBean();
		   	
			int num=Integer.parseInt(request.getParameter("num"));
			String page = request.getParameter("page");
//		   	boarddata=boarddao.getDetail(num);
			boarddata=boarddao.forumgetBoardCont(num);//�? ?��?�� 
			
		   	if(boarddata==null){
		   		System.out.println("수정 보기 실패");
		   		return null;
		   	}
		   	System.out.println("수정 보기 성공");
		   	
		   	request.setAttribute("boarddata", boarddata);
		    request.setAttribute("page", page);
		   	forward.setRedirect(false);
	   		forward.setPath("./board/forum_board_modify.jsp");
	   		return forward;
	 }
}