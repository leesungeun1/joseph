package net.forumboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ForumAction {
	public ForumActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
