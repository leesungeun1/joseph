package controller;

import java.util.Random;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmailController {

	@RequestMapping(value="/send.do")
	public String send(Model m) throws Exception{
		
		Random random = new Random();
		int a = random.nextInt(100);
		
		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.naver.com";		
		String hostSMTPid = "sean0109@naver.com";		
		String hostSMTPpwd = "passwd";// 비밀번호 입력해야함
		
		// 보내는 사람 EMail, 제목, 내용 
		String fromEmail = "sean0109@naver.com";		
		String fromName = "친절한 홍길동씨";
		String subject = "Overflow인증메일입니다.";
		
		// 받는 사람 E-Mail 주소
		String mail = "sean0109@naver.com";		
		
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			// 네이버 이메일의 지정된 포트번호를 쓴다.
			email.setSmtpPort(587);			

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg("<p align = 'center'>Overflow에 오신것을 환영합니다.</p><br><div align='center'>"
					+ "인증번호 : "	+ a + "</div>");
			email.send();	
			
		} catch (Exception e) {
			System.out.println(e);
		}	
		
		m.addAttribute("passwd", a);
		
		return "send";
	}
	
	
}
