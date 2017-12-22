package m2Action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import m2member.m2memberDAO;
import m2member.m2memberDTO;
import m2Action.ActionForward;

public class Update implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Update");
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();	
		
		m2memberDTO member = new m2memberDTO();
		member.setId(request.getParameter("id"));
		member.setPass(request.getParameter("pass"));
		String npass = request.getParameter("npass");
		
		m2memberDAO dao = m2memberDAO.getInstance();
		int result = dao.update(member, npass);
		System.out.println("result="+result);
		
		if(result != 1) {
			out.println("<script>");
			out.println("alert('	鍮꾨�踰덊샇媛� ��由쎈땲�떎.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			
			return null;
		}		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./member/myPage.jsp");
		
		return forward;
		
	}
	

}
