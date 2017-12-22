package net.forumboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.forumboard.db.ForumBoardBean;
import net.forumboard.db.ForumBoardDAOImpl;

public class ForumBoardAddAction implements ForumAction {
	
	 @SuppressWarnings("deprecation")
	public ForumActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		ForumBoardDAOImpl boarddao=new ForumBoardDAOImpl();
		ForumBoardBean boarddata=new ForumBoardBean();	   	
	   	ForumActionForward forward=new ForumActionForward();
	   	
	   	System.out.println("객체생성");
	   	
		String realFolder="";
   		String saveFolder="boardUpload";   		
   		int fileSize=5*1024*1024;   		
   		realFolder=request.getRealPath(saveFolder);
   		
   		@SuppressWarnings("unused")
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
			 		
			boarddao.forumboardInsert(boarddata);
			//result=boarddao.forumboardInsert(boarddata);					
			   		
	   		
	   		/*if(result==false){
	   			System.out.println("게시 등록 실패");
	   			return null;
	   		}*/
	   		System.out.println("게시 등록 성공");
	   		
	   		forward.setRedirect(true);
	   		forward.setPath("./ForumBoardListAction.fo");
	   		return forward;
	   		
  		}catch(Exception ex){
   			ex.printStackTrace();
   		}
  		return null;
	}  	
}