package net.qnaboard.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QnaBoardFrontController extends javax.servlet.http.HttpServlet
		implements javax.servlet.Servlet {

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		QnaActionForward forward = null;
		QnaAction action = null;

		System.out.println("RequestURI=" + RequestURI);
		System.out.println("contextPath=" + contextPath);
		System.out.println("command=" + command);

		if (command.equals("/QnaBoardWrite.qo")) {
			forward = new QnaActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/qna_board_write.jsp");
			
		} else if (command.equals("/QnaBoardReplyView.qo")) {
			action = new QnaBoardReplyView();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/QnaBoardReplyAction.qo")) {
			action = new QnaBoardReplyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/QnaBoardDelete.qo")) {
			forward = new QnaActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/qna_board_delete.jsp");
			
		} else if (command.equals("/QnaBoardDeleteAction.qo")) {
			action = new QnaBoardDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}			
			
		} else if (command.equals("/QnaBoardModifyView.qo")) {
			action = new QnaBoardModifyView();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/QnaBoardAddAction.qo")) {
			action = new QnaBoardAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/QnaBoardModifyAction.qo")) {
			action = new QnaBoardModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/QnaBoardListAction.qo")) {
			action = new QnaBoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/QnaBoardDetailAction.qo")) {
			action = new QnaBoardDetailAction();
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