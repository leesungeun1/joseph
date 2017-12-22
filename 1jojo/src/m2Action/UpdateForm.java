package m2Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import m2member.m2memberDAO;
import m2member.m2memberDTO;
import m2Action.ActionForward;

public class UpdateForm implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("updateForm");

		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		m2memberDAO dao = m2memberDAO.getInstance();
		m2memberDTO member = dao.select(id);
		
		
				
		request.setAttribute("member", member);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./member/UpdateForm.jsp");
		
		return forward;
			
	}

}
