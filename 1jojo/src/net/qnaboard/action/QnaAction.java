package net.qnaboard.action;

import javax.servlet.http.*;

public interface QnaAction {
	public QnaActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception;
}
