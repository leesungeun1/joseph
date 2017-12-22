import org.springframework.context.support.GenericXmlApplicationContext;

import message.Logic;
import message.Service;

public class Main {

	public static void main(String[] args) {
		
		GenericXmlApplicationContext context = new GenericXmlApplicationContext(
				"classpath:applicationContext.xml");
		Service service = context.getBean("service", Service.class);
		Logic logic = context.getBean("logic", Logic.class);
		service.sayHello();  	//핵심 관심사항 처리 메소드 호출
		logic.calc();			//핵심 관심사항 처리 메소드 호출
		
		context.close();
		
	}

}
