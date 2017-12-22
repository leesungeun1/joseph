package message;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;

// Advice 클래스 생성 (공통 관심 사항 처리 - log출력)
public class LogginAdvice {

	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {

		System.out.println("메소드 호출 전");
		FileWriter fw = new FileWriter("c:\\data\\log.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("Enter Time:" + (new Date().toString()) + "\n");

		// 실제 메소드 호출 구문 ( sayHello()메소드를 호출함 )
		joinPoint.proceed();

		System.out.println("메소드 호출 후");
		System.out.println();
		bw.write("Exit Time:" + (new Date().toString()) + "\n");
		bw.close();

		return null;
	}
}
