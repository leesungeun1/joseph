package m2Action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import m2member.m2memberDAO;
import m2member.m2memberDTO;

public class MemberInsert implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MemberInsert");
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");		
		
		PrintWriter out = response.getWriter();
		
		System.out.println("id="+request.getParameter("id"));
		System.out.println("pass="+request.getParameter("pass"));
		
		
		m2memberDTO member = new m2memberDTO();
		member.setId(request.getParameter("id"));
		member.setPass(request.getParameter("pass"));		
		
				
		m2memberDAO dao = m2memberDAO.getInstance();
		int result = dao.insert(member);
		System.out.println("result="+result);
		
		if(result != 1) {
			out.println("<script>");
			out.println("alert('가입할 수 없습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			
			return null;
		}	
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./member/loginForm.jsp");		
		
		return forward;
	}

}
