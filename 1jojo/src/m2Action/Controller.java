package m2Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import m2Action.Action;
import m2Action.ActionForward;
import m2Action.MemberInsert;
import m2Action.UpdateForm;
import m2Action.IdCheck;
import m2Action.Delete;
import m2Action.Update;
import m2Action.Login;

/**
 * Servlet implementation class Controller
 */
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		ActionForward forward = null;
		Action action = null;

		System.out.println("RequestURI=" + RequestURI);
		System.out.println("contextPath=" + contextPath);
		System.out.println("command=" + command);
		 //寃��깋
		if(command.equals("/SearchAction")) {
          try {
	
          }
          catch(Exception e){
        	  e.printStackTrace();	
          }
          //ID 以묐났 寃��궗
		}else if(command.equals("/IdCheck.do")) {
			try {
				action = new IdCheck();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		//회원가입
		}else if(command.equals("/MemberInsert.do")) {
			try {
				action = new MemberInsert();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		//濡쒓렇�씤	
		}else if(command.equals("/Login.do")) {
			try {
				action = new Login();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		//濡쒓렇�븘�썐	
		}else if(command.equals("/Logout.do")) {
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/logOut.jsp");
	
		//�젙蹂댁닔�젙 �뤌
		}else if(command.equals("/UpdateForm.do")) {
			try {
				action = new UpdateForm();
				forward = action.execute(request, response);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			//�쉶�썝�젙蹂댁닔�젙
		}else if(command.equals("/Update.do")) {
		    try {
			action = new Update();
			forward = action.execute(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			}
		    //�쉶�썝�깉�눜�뤌
		}else if(command.equals("/DeleteMember.do")) {
	    	forward = new ActionForward();
	    	forward.setRedirect(false);
	    	forward.setPath("./member/deleteMember.jsp");
	    	
	    //�쉶�썝 �깉�눜	
		}else if(command.equals("/Delete.do")) {
	    	try {
	    		action = new Delete();
	    		forward = action.execute(request, response);
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }
			
		if (forward != null) {
			if (forward.getRedirect()) { // true
				response.sendRedirect(forward.getPath());
			} else { // false 媛믪쟾�떖�씠 媛��뒫�븿
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
	}//doprocess end
    /**
     * @see HttpServlet#HttpServlet()
     */
   	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	System.out.println("doGet");
	doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	System.out.println("doPost");
	doProcess(request, response);
	}

}
