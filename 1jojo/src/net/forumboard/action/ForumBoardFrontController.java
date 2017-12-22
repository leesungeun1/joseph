package net.forumboard.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForumBoardFrontController extends javax.servlet.http.HttpServlet
		implements javax.servlet.Servlet {

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ForumActionForward forward = null;
		ForumAction action = null;

		System.out.println("RequestURI=" + RequestURI);
		System.out.println("contextPath=" + contextPath);
		System.out.println("command=" + command);

		if (command.equals("/ForumBoardWrite.fo")) {
			forward = new ForumActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/forum_board_write.jsp");
					
		} else if (command.equals("/ForumBoardReplyView.fo")) {
			action = new ForumBoardReplyView();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/ForumBoardReplyAction.fo")) {
			action = new ForumBoardReplyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/ForumBoardDelete.fo")) {
			forward = new ForumActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/forum_board_delete.jsp");
			
		} else if (command.equals("/ForumBoardDeleteAction.fo")) {
			action = new ForumBoardDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}			
			
		} else if (command.equals("/ForumBoardModifyView.fo")) {
			action = new ForumBoardModifyView();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/ForumBoardAddAction.fo")) {
			action = new ForumBoardAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/ForumBoardModifyAction.fo")) {
			action = new ForumBoardModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/ForumBoardListAction.fo")) {
			action = new ForumBoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/ForumBoardDetailAction.fo")) {
			action = new ForumBoardDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (forward != null) {
			if (forward.isRedirect()) { // true
				response.sendRedirect(forward.getPath());
			} else { // false 媛�?�쟾�떖�씠 媛��뒫�븿
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}// doprocess() end

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}