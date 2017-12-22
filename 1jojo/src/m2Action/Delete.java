package m2Action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import m2member.m2memberDAO;
import m2member.m2memberDTO;
import m2Action.ActionForward;

public class Delete implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("delete");

		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		m2memberDTO member = new m2memberDTO();
		member.setId(request.getParameter("email"));
		member.setPass(request.getParameter("pass"));
		
		m2memberDAO dao = m2memberDAO.getInstance();
		int result = dao.delete(member.getId());
		
		if(result != 1) {
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			
			return null;
		}		
		
		session.invalidate();
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./member/main.jsp");
		
		return forward;
	
	
	}

}
