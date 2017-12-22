package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAOImpl;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardAddAction implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		BoardDAOImpl boarddao=new BoardDAOImpl();
	   	BoardBean boarddata=new BoardBean();
	   	
	   	ActionForward forward=new ActionForward();
		String realFolder="";
   		String saveFolder="boardUpload";   		
   		int fileSize=5*1024*1024;   		
   		realFolder=request.getRealPath(saveFolder);
   		boolean result=false;
   		
   		try{
   			
   			MultipartRequest multi=null;
   			
   			multi=new MultipartRequest(request,
   					realFolder,
   					fileSize,
   					"utf-8",
   					new DefaultFileRenamePolicy());
   			
   			boarddata.setBoard_name(multi.getParameter("BOARD_NAME").trim());
   			boarddata.setBoard_pass(multi.getParameter("BOARD_PASS").trim());
	   		boarddata.setBoard_subject(multi.getParameter("BOARD_SUBJECT").trim());
	   		boarddata.setBoard_content(multi.getParameter("BOARD_CONTENT").trim());
	   		boarddata.setBoard_file(multi.getFilesystemName((String)multi.getFileNames().nextElement()));
				   		
			System.out.println("name="+boarddata.getBoard_name());
			System.out.println("pass="+boarddata.getBoard_pass());
			System.out.println("subject="+boarddata.getBoard_subject());
			System.out.println("content="+boarddata.getBoard_content());
			System.out.println("file="+boarddata.getBoard_file());
			 		
			boarddao.boardInsert(boarddata);
//	   		result=boarddao.boardInsert(boarddata);
	   		
	   		
	   		/*if(result==false){
	   			System.out.println("게시판 등록 실패");
	   			return null;
	   		}
	   		System.out.println("게시판 등록 완료");*/
	   		
	   		forward.setRedirect(true);
	   		forward.setPath("./BoardListAction.bo");
	   		return forward;
	   		
  		}catch(Exception ex){
   			ex.printStackTrace();
   		}
  		return null;
	}  	
}