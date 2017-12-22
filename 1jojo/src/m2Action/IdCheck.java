package m2Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import m2member.m2memberDAO;

public class IdCheck implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("IdCheck");
		
		String id = request.getParameter("id");
		System.out.println("id"+id);
		
		m2memberDAO dao = m2memberDAO.getInstance();
		int result = dao.IdCheck(id);

		request.setAttribute("result", result);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./member/idcheck.jsp");
		return forward;
	}

}
