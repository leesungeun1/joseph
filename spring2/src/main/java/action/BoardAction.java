package action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import service.BoardServiceImpl;

@Controller
public class BoardAction {

	@Autowired
	private BoardServiceImpl boardService;
}
