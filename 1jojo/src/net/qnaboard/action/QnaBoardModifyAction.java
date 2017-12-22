package net.qnaboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.qnaboard.db.QnaBoardBean;
import net.qnaboard.db.QnaBoardDAOImpl;

 public class QnaBoardModifyAction implements QnaAction {
	 public QnaActionForward execute(HttpServletRequest request,HttpServletResponse response)throws Exception{
		 
		 request.setCharacterEncoding("utf-8");
		 QnaActionForward forward = new QnaActionForward();
		 
		 @SuppressWarnings("unused")
		boolean result = false;
		 int num=Integer.parseInt(request.getParameter("BOARD_NUM"));
		 String page = request.getParameter("page");
		 @SuppressWarnings("unused")
		String pass = request.getParameter("BOARD_PASS");
		 
		 QnaBoardDAOImpl boarddao=new QnaBoardDAOImpl();
//		 BoardDAO boarddao=new BoardDAO();
		 QnaBoardBean boarddata=new QnaBoardBean();
		 
		 // 비�?번호 ?���? ?���? ?���?
		 /*boolean usercheck=boarddao.isBoardWriter(num, pass);
		 if(usercheck==false){ // 비�?번호�? ?��치하�? ?��?�� 경우
		   		response.setContentType("text/html;charset=utf-8");
		   		PrintWriter out=response.getWriter();
		   		out.println("<script>");
		   		out.println("alert('?��?��?�� 권한?�� ?��?��?��?��.');");
		   	//	out.println("location.href='./BoardList.bo';");
		   		out.println("history.go(-1)");
		   		out.println("</script>");
		   		out.close();
		   		return null;
		 }*/
		 
		 try{
			 boarddata.setBoard_num(num);
			 boarddata.setBoard_subject(request.getParameter("BOARD_SUBJECT"));
			 boarddata.setBoard_content(request.getParameter("BOARD_CONTENT"));
			 
		//	 result = boarddao.boardModify(boarddata);
			 boarddao.qnaboardEdit(boarddata);
			 /*if(result==false){
		   		System.out.println("게시?�� ?��?�� ?��?��");
		   		return null;
		   	 }*/
		   	 System.out.println("게시물 수정!");
		   	 
		   	 forward.setRedirect(true);
		   	 forward.setPath("./QnaBoardDetailAction.qo?num="+boarddata.getBoard_num()+"&page="+page);
		   	 return forward;
	   	 }catch(Exception ex){
	   			ex.printStackTrace();	 
		 }
		 
		 return null;
	 }
}