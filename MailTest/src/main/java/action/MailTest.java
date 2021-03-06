package action;

import java.util.Random;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MailTest {

	@RequestMapping("/send.do")
	public ModelAndView send() {

		Random random = new Random();
		int a = random.nextInt(100);

		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.naver.com";
		String hostSMTPid = "leesungeun1@naver.com";
		String hostSMTPpwd = "dltjddms12!!";// 비밀번호 입력해야함

		// 보내는 사람 EMail, 제목, 내용
		String fromEmail = "leesungeun1@naver.com";
		String fromName = "성은프로그래머님 멋있습니다! 힘내세요^^@@";
		String subject = "인증메일입니다 비번을 확인하세요^^";

		// 받는 사람 E-Mail 주소
		String mail = "leesungeun1@naver.com";

		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(587);

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(
					"<p align = 'center'>Overflow에 오신것을 환영합니다.</p><br><div align='center'>" + "인증번호 : " + a + "</div>");
			email.send();
		} catch (Exception e) {
			System.out.println(e);
		}

		ModelAndView mv = new ModelAndView("result");
		mv.addObject("result", "good~!!\n 등록된 E-Mail 확인"+a);

		return mv;
	}

}
