package com.naver.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.naver.dao.GDao;
import com.naver.model.ComBean;
import com.naver.model.GBean;

@Controller
public class GAction {

	private GDao gService;

	public void setgService(GDao gService) {
		this.gService = gService;
	}

	// 방명록 목록
	@RequestMapping(value = "/g_list.do")
	public String g_list(Model m) {

		List<GBean> list = this.gService.getGList();
		// 방명록 목록을 받아옴.

		// ModelAndView gm=new ModelAndView();
		// gm.addObject("glist",list);
		// gm.setViewName("g/g_list");

		m.addAttribute("glist", list);

		return "g/g_list";
	}

	/* 방명록 내용+댓글 입력폼+댓글 목록 */
	@RequestMapping(value = "/g_cont.do")
	public String g_cont(Model m, @RequestParam("g_no") int g_no, @RequestParam("state") String state) {

		int com_count = 0;// 댓글 개수를 저장하기위한 변수
		List<ComBean> clist = null;

		if (state.equals("cont")) {
			/*
			 * 6번째 과제물 1.조회수가 증가되는 updateHit(g_no); 메서드를 작성하세요! 첨부파일을
			 * GAction.java,GDao.java,GDaoImpl.java, guest.xml 입니다.
			 */
			this.gService.updatehit(g_no);// 조회수증가

			com_count = this.gService.getComCount(g_no);
			// 댓글 개수를 저장

			clist = this.gService.getComList(g_no);
			// 해당 방명록 번호에 해당하는 댓글 목록을 가져온다.
		}

		GBean g = this.gService.getCont(g_no);
		// 번호를 기준으로 디비로 부터 내용을 가져온다.
		String g_cont = g.getG_cont().replace("\n", "<br/>");
		// 1. pre 태그로 감싸주는 방법,
		// 2. replace 함수로 바꿔주는 방법
		// 		첫번째 인수값을, 두번째 인수값으로 바꾸라는 의미
		// 		결과적으로 줄이 바뀐 상태로 g_cont 변수에 저장이 된다.
		// textarea박스에 엔터키 친부분을 다음줄로 개행

		// ModelAndView cm=new ModelAndView();
		// cm.addObject("g",g);
		// cm.addObject("g_cont",g_cont);
		// cm.addObject("com_count",com_count);
		// 댓글 개수를 저장
		// cm.addObject("clist",clist);//댓글목록을 저장

		m.addAttribute("g", g);
		m.addAttribute("g_cont", g_cont);
		m.addAttribute("com_count", com_count);
		m.addAttribute("clist", clist);

		if (state.equals("cont")) {// 내용보기일때만
			// cm.setViewName("g/g_cont");
			return "g/g_cont";
		} else if (state.equals("edit")) {// 수정폼
			// cm.setViewName("g/g_edit");
			return "g/g_edit";
		} else if (state.equals("del")) {// 삭제폼
			// cm.setViewName("g/g_del");
			return "g/g_del";
		}
		return null;
	}

	/* 댓글 저장 */
	@RequestMapping(value = "/comment_ok.do")
	public String com_ok(@ModelAttribute ComBean c, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (c.getComment_cont().length() > 200) {
			out.println("<script>");
			out.println("alert('댓글을 200자 초과를 못합니다!')");
			out.println("history.go(-1)");
			out.println("</script>");
		} else {
			this.gService.insertCom(c);// 댓글 저장

			return "redirect:/g_cont.do?g_no=" + c.getG_no() + "&state=cont";
			// get 방식으로 *.do?g_no=번호값&state=cont 2개의 피라미터
			// 값을 넘김
		}
		return null;
	}

	/* 댓글 삭제 */
	@RequestMapping(value = "/comment_del_ok.do")
	public String com_del_ok(@RequestParam("comment_no") int comment_no, @RequestParam("g_no") int g_no,
			@RequestParam("state") String state) {

		this.gService.com_del(comment_no);// 댓글 삭제

		return "redirect:/g_cont.do?g_no=" + g_no + "&state=" + state;
	}

	/* 방명록 글쓰기 */
	@RequestMapping(value = "/g_write.do")
	public String g_write() {
		return "g/g_write";
	}

	/* 방명록 저장 */
	@RequestMapping(value = "/g_write_ok.do")
	public String g_write_ok(@ModelAttribute ComBean c) {
		// 네임피라미터 이름과 빈클래스의 변수명이 같으면 스프링 @ModelAttribute
		// 어노테이션으로 쉽게 값을 가져와 setter()메서드를 호출해서 저장
		this.gService.insertG(c);// 저장메서드
		return "redirect:/g_list.do";
	}

	/* 방명록수정 */
	@RequestMapping(value = "/g_edit_ok.do")
	public String g_edit_ok(@ModelAttribute ComBean ec, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		GBean db_pwd = this.gService.getCont(ec.getG_no());
		// 디비에 저장된 비번을 가져옴.

		if (!db_pwd.getG_pwd().equals(ec.getG_pwd())) {
			// 비번이 같지 않을때
			out.println("<script>");
			out.println("alert('비번이 다릅니다!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			this.gService.updateG(ec);// 수정

			return "redirect:/g_cont.do?g_no=" + ec.getG_no() + "&state=cont";
		}
		return null;
	}

	/* 방명록 삭제 */
	@RequestMapping(value = "/g_del_ok.do")
	public String g_del_ok(@RequestParam("g_no") int g_no, @RequestParam("del_pwd") String del_pwd,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		GBean db_pwd = this.gService.getCont(g_no);
		if (!db_pwd.getG_pwd().equals(del_pwd)) {
			out.println("<script>");
			out.println("alert('비번이 다릅니다!')");
			out.println("history.go(-1)");
			out.println("</script>");
		} else {
			List<ComBean> clist = this.gService.getComList(g_no);

			if (clist.size() > 0) {// size()메서드는 컬렉션 요소개수를 반환
				out.println("<script>");
				out.println("alert('댓글목록이 있어서 삭제 할수 없습니다!')");
				out.println("location='g_cont.do?g_no=" + g_no + "&state=cont'");
				out.println("</script>");
			} else {
				this.gService.delG(g_no);
				return "redirect:/g_list.do";
			}
		}
		return null;
	}
}
