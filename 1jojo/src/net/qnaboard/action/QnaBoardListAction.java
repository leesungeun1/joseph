package net.qnaboard.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import net.board.db.BoardDAO1;
import net.qnaboard.db.QnaBoardDAOImpl;

 public class QnaBoardListAction implements QnaAction {
	 public QnaActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

//		BoardDAO boarddao=new BoardDAO();
		QnaBoardDAOImpl boarddao=new QnaBoardDAOImpl();
		@SuppressWarnings("rawtypes")
		List boardlist=new ArrayList();
		
		int page=1; // ?��?�� ?��?���? 번호
		int limit=10; // ?�� ?��면에 출력?�� ?��코드 �??��
		
		if(request.getParameter("page") != null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		int listcount=boarddao.qnagetListCount(); //�? 리스?�� ?���? 받아?��
//		boardlist = boarddao.getBoardList(page,limit); //리스?���? 받아?��
		boardlist = boarddao.qnagetBoardList(page);
		
		//�? ?��?���? ?��
 		int maxpage=(int)((double)listcount/limit+0.95); //0.95�? ?��?��?�� ?���? 처리
 		
 		//?��?�� ?��?���??�� 보여�? ?��?�� ?��?���? ?��(1, 11, 21 ?��...)
 		int startpage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
 		
 		//?��?�� ?��?���??�� 보여�? 마�?�? ?��?���? ?��(10, 20, 30 ?��...)
		int endpage = startpage+10-1;

 		if(endpage> maxpage) endpage= maxpage;
 		
 		@SuppressWarnings("unused")
		int number = listcount-(page-1)*10; 		
 		
 		request.setAttribute("page", page); //?��?�� ?��?���? ?��
 		request.setAttribute("maxpage", maxpage); //최�? ?��?���? ?��
 		request.setAttribute("startpage", startpage); //?��?�� ?��?���??�� ?��?��?�� �? ?��?���? ?��
 		request.setAttribute("endpage", endpage); //?��?�� ?��?���??�� ?��?��?�� ?�� ?��?���? ?��
		request.setAttribute("listcount",listcount); //�? ?��
		request.setAttribute("boardlist", boardlist);		
		
		QnaActionForward forward= new QnaActionForward();
	 	forward.setRedirect(false);
 		forward.setPath("/board/qna_board_list.jsp");
 		return forward;
	 }
 }