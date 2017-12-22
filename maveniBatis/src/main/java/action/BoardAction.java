package action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.BoardDAOImpl;
import model.BoardBean;

//import model.BbsBean;

@Controller
public class BoardAction {

	private BoardDAOImpl boardService;

	public void setBoardService(BoardDAOImpl boardService) {
		this.boardService = boardService;
	}// setter DI설정

	
	
	/* 게시판 글쓰기 폼 */
	@RequestMapping(value = "/board_write.nhn")
	public String board_write() {
		return "board/board_write";
	}

	/* 게시판 저장 */
	@RequestMapping(value = "/board_write_ok.nhn", method = RequestMethod.POST)
	public String board_write_ok(@ModelAttribute BoardBean board) throws Exception {
		
		//네임피라미터 이름과 빈클래스의 변수명이 같으면 스프링 @ModelAttribute
		//어노테이션으로 쉽게 값을 가져와 setter()메서드를 호출해서 저장
		
//		String board_name = request.getParameter("board_name").trim();
//		String board_pass = request.getParameter("board_pass").trim();
//		String board_subject = request.getParameter("board_subject").trim();
//		String board_content = request.getParameter("board_content").trim();

//		BoardBean board = new BoardBean();
//		board.setBoard_name(board_name);
//		board.setBoard_pass(board_pass);
//		board.setBoard_subject(board_subject);
//		board.setBoard_content(board_content);

		// 생성자 호출을 통해서 데이터 베이스 접속
		// iBatis로 변경 할것
		// BoardDAOImpl bd=new BoardDAOImpl();

		boardService.insertBoard(board);// 저장 메서드 호출

//		response.sendRedirect("board_list.nhn");
		// 게시물 목록으로 이동
		
		return "redirect:/board_list.nhn";
		
//		return null;
	}

	/* 게시판 목록 */
	@RequestMapping(value = "/board_list.nhn")
	public String board_list(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<BoardBean> boardlist = new ArrayList<BoardBean>();
		// BoardDAOImpl bd=new BoardDAOImpl();

		int page = 1;
		int limit = 10; // 한 화면에 출력할 레코드수

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		/* int listcount=this.boardService.getListCount(); */// 총 리스트 수를 받아옴.
		int listcount = boardService.getListCount();

		// int startRow = (page - 1) * limit + 1;
		// int endRow = page * limit ;
		// Map<String,Integer> m = new HashMap<String,Integer>();
		// m.put("startRow", startRow);
		// m.put("endRow", endRow);
		// m.put("page", page);
		// m.put("limit", limit);

		// 페이지 번호(page)를 DAO클래스에게 전달한다.
		boardlist = boardService.getBoardList(page); // 리스트를 받아옴.

		// 총 페이지 수.
		int maxpage = (int) ((double) listcount / limit + 0.95); // 0.95를 더해서 올림
																	// 처리.
		// 현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
		int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		// 현재 페이지에 보여줄 마지막 페이지 수.(10, 20, 30 등...)
		int endpage = maxpage;

		if (endpage > startpage + 10 - 1)
			endpage = startpage + 10 - 1;

//		request.setAttribute("page", page); // 현재 페이지 수.
//		request.setAttribute("maxpage", maxpage); // 최대 페이지 수.
//		request.setAttribute("startpage", startpage); // 현재 페이지에 표시할 첫 페이지 수.
//		request.setAttribute("endpage", endpage); // 현재 페이지에 표시할 끝 페이지 수.
//		request.setAttribute("listcount", listcount); // 글 수.
//		request.setAttribute("boardlist", boardlist);

//		ModelAndView boardListM = new ModelAndView("board/board_list");
//		boardListM.addObject("page", page);
//		boardListM.addObject("maxpage", maxpage);
//		boardListM.addObject("startpage", startpage);
//		boardListM.addObject("endpage", endpage);
//		boardListM.addObject("listcount", listcount);
//		boardListM.addObject("boardlist", boardlist);		
		
		model.addAttribute("page", page);
		model.addAttribute("maxpage", maxpage);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("listcount", listcount);
		model.addAttribute("boardlist", boardlist);			
		
//		return boardListM;
		return "board/board_list";
	}

	/* 게시판 내용보기,삭제폼,수정폼,답변글폼 */
	@RequestMapping(value = "/board_cont.nhn")
	public String board_cont(Model model, @RequestParam("board_num") int board_num,
								   		  @RequestParam("page") String page,
								   		  @RequestParam("state") String state,
			HttpServletResponse response) throws Exception {

//		int board_num = Integer.parseInt(request.getParameter("board_num"));
		// 글번호
//		int page = Integer.parseInt(request.getParameter("page"));
		// 쪽번호
//		String state = request.getParameter("state");
		// 구분값 저장. 내용보기 cont,수정폼 edit,삭제폼 del,답변글폼 reply

		// BoardDAOImpl bd=new BoardDAOImpl();

		if (state.equals("cont")) {// 내용보기일때만
			/* this.boardService.boardHit(board_num); */// 조회수 증가
			boardService.boardHit(board_num);
		}

		/* BoardBean board=this.boardService.getBoardCont(board_num); */
		BoardBean board = boardService.getBoardCont(board_num);

//		ModelAndView contM = new ModelAndView();
//		contM.addObject("bcont", board);
//		contM.addObject("page", page);
		model.addAttribute("bcont", board);
		model.addAttribute("page", page);
		

		if (state.equals("cont")) {// 내용보기일때
//			contM.setViewName("board/board_cont");// 내용보기 페이지 설정
	//		String board_cont = board.getBoard_content().replace("\n", "<br/>");
			// 글내용중 엔터키 친부분을 웹상에 보이게 할때 다음줄로 개행
	//		contM.addObject("board_cont", board_cont);
			return "board/board_cont";
		} else if (state.equals("edit")) {// 수정폼
//			contM.setViewName("board/board_edit");
			return "board/board_edit";
		} else if (state.equals("del")) {// 삭제폼
//			contM.setViewName("board/board_del");
			return "board/board_del";
		} else if (state.equals("reply")) {// 답변달기 폼
//			contM.setViewName("board/board_reply");
			return "board/board_reply";
		}
		return null;
	}

	/* 게시판 수정 */
	@RequestMapping(value = "/board_edit_ok.nhn", method = RequestMethod.POST)
	public String board_edit_ok(@ModelAttribute BoardBean b,
							    @RequestParam("page") String page,
			HttpServletResponse response, HttpSession session) throws Exception {

		response.setContentType("text/html;chaset=UTF-8");
		PrintWriter out = response.getWriter();// 출력스트림 생성

		session.setAttribute("id", "test");
//		int board_num = Integer.parseInt(request.getParameter("board_num"));
//		int page = Integer.parseInt(request.getParameter("page"));
//		String board_name = request.getParameter("board_name").trim();
//		String board_pass = request.getParameter("board_pass").trim();
//		String board_subject = request.getParameter("board_subject").trim();
//		String board_content = request.getParameter("board_content").trim();

		// BoardDAOImpl bd=new BoardDAOImpl();

		/* BoardBean board=this.boardService.getBoardCont(board_num); */
		BoardBean board = boardService.getBoardCont(b.getBoard_num());
		// 번호를 기준으로 디비 내용을 가져옴.

		if (!board.getBoard_pass().equals(b.getBoard_pass())) {// 비번이 같다면
			out.println("<script>");
			out.println("alert('비번이 다릅니다!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
//			BoardBean b = new BoardBean();
//			b.setBoard_num(board_num);
//			b.setBoard_name(board_name);
//			b.setBoard_subject(board_subject);
//			b.setBoard_content(board_content);

			/* this.boardService.boardEdit(b); */// 수정 메서드 호출
			boardService.boardEdit(b);

//			response.sendRedirect("board_cont.nhn?board_num=" + board_num
//					+ "&page=" + page + "&state=cont");
			
			return "redirect:/board_cont.nhn?board_num="+b.getBoard_num()+"&page=" + page+
					"&state=cont";
		}
		return null;
	}

	/* 게시판 삭제 */
	@RequestMapping(value = "/board_del_ok.nhn", method = RequestMethod.POST)
	public String board_del_ok(@RequestParam("board_num") int board_num,
							   @RequestParam("page") int page,
							   @RequestParam("pwd") String board_pass,
 			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		// 웹상에 보이는 언어코딩 타입을 지정
		PrintWriter out = response.getWriter();// 출력 스트림 객체생성

//		int board_num = Integer.parseInt(request.getParameter("board_num"));
		// 글번호 저장
//		int page = Integer.parseInt(request.getParameter("page"));
		// 페이지 번호 저장

//		String board_pass = request.getParameter("pwd").trim();
		// trim() 메서드로 양쪽 공백을 제거

		// BoardDAOImpl bd=new BoardDAOImpl();

		/* BoardBean board=this.boardService.getBoardCont(board_num); */
		BoardBean board = boardService.getBoardCont(board_num);
		// 번호에 해당하는 디비 정보를 가져온다.

		if (!board.getBoard_pass().equals(board_pass)) {
			out.println("<script>");
			out.println("alert('비번이 다릅니다!')");
			out.println("history.go(-1)");
			out.println("</script>");
		} else {
			/* this.boardService.boardDelete(board_num); */
			boardService.boardDelete(board_num);
			// 글번호를 기준으로 삭제
//			response.sendRedirect("board_list.nhn?page=" + page);
			// 삭제후 게시물 목록으로 이동
			
			return "redirect:/board_list.nhn?page=" + page; 
			
		}
		return null;
	}

	/* 게시판 답변달기 저장 */
	@RequestMapping(value = "/board_reply_ok.nhn", method = RequestMethod.POST)
	public String board_reply_ok(@ModelAttribute BoardBean b,
								 @RequestParam("page") String page) throws Exception {

//		int board_num = Integer.parseInt(request.getParameter("board_num"));
//		int board_re_ref = Integer.parseInt(request.getParameter("board_re_ref"));
//		int board_re_lev = Integer.parseInt(request.getParameter("board_re_lev"));
//		int board_re_seq = Integer.parseInt(request.getParameter("board_re_seq"));
//		int page = Integer.parseInt(request.getParameter("page"));
//
//		String board_name = request.getParameter("board_name").trim();
//		String board_subject = request.getParameter("board_subject").trim();
//		String board_content = request.getParameter("board_content").trim();
//		String board_pass = request.getParameter("board_pass").trim();

//		BoardBean b = new BoardBean();
//		b.setBoard_re_ref(board_re_ref);
//		b.setBoard_re_seq(board_re_seq);		

		// BoardDAOImpl bd=new BoardDAOImpl();
		/* this.boardService.refEdit(b); */
		boardService.refEdit(b);
		
		
		/*
		
		* 답변형 게시판
						
						ref : 관련글   
						      원문 : num컬럼과 같은값
						      답글 : 부모의 ref값과 같은값
						
						re_level : 답글의 깊이
						            원문 : 0  
						            1단계답글 : 1
						            2단계답글 : 2
					
						re_seq : 답글의 출력 순서
						            원문 : 0
						            답글 : 오름 차순 정렬
						            
		원문의 경우에는 num값과 ref 값이 동일 

		댓글의 경우에는 자신의 부모 원문의 값과 ref 값이 동일

		댓글의 댓글일 경우에도 자신의 부모 원문의 값과  ref 값이 동일하다.


		ex)
								|	ref		|	re_level	|	re_seq
		---------------------------------------------------------------------
		첫번째 원문 					|	  1		|		0		|		0
		---------------------------------------------------------------------
		세번째 원문					|	  3		|		0		|		0
			- 세번째 댓글			|	  3		|		1		|		1
			- 두번째 댓글			|	  3		|		1		|		2
				- 댓글의 두번째 댓글	|	  3		|		2		|		3
				- 댓글의 첫번째 댓글	|	  3		|		2		|       4
			- 첫번째 댓글			|	  3		|		1		|		5
		
		*/

//		board_re_lev = board_re_lev + 1;
//		board_re_seq = board_re_seq + 1; // 1씩증가		

//		b.setBoard_name(board_name);
//		b.setBoard_subject(board_subject);
//		b.setBoard_content(board_content);
//		b.setBoard_pass(board_pass);
		b.setBoard_re_lev(b.getBoard_re_lev()+1); //부모보다 1증가된 값을 저장함
		b.setBoard_re_seq(b.getBoard_re_seq()+1);

		/* this.boardService.boardReplyOk(b); */// 저장 메서드 호출
		boardService.boardReplyOk(b);

//		response.sendRedirect("board_list.nhn?page=" + page); // 해당 쪽번호 목록으로 이동
//		return null;
		
		return "redirect:/board_list.nhn?page=" + page;
	}
}
