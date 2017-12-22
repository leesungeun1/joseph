package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import net.board.db.BoardDAO1;
import net.board.db.BoardBean;
import net.board.db.BoardDAOImpl;

public class BoardModifyView implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 	ActionForward forward = new ActionForward();
		 	request.setCharacterEncoding("utf-8");
	   		
		 	BoardDAOImpl boarddao=new BoardDAOImpl();
//			BoardDAO boarddao=new BoardDAO();
		   	BoardBean boarddata=new BoardBean();
		   	
			int num=Integer.parseInt(request.getParameter("num"));
			String page = request.getParameter("page");
//		   	boarddata=boarddao.getDetail(num);
			boarddata=boarddao.getBoardCont(num);//글 내용 
			
		   	if(boarddata==null){
		   		System.out.println("(수정)상세보기 실패");
		   		return null;
		   	}
		   	System.out.println("(수정)상세보기 성공");
		   	
		   	request.setAttribute("boarddata", boarddata);
		    request.setAttribute("page", page);
		   	forward.setRedirect(false);
	   		forward.setPath("./board/qna_board_modify.jsp");
	   		return forward;
	 }
}