package message;

public class Service {

	// 핵심 관심사항 처리 
	public void sayHello() {
		try {
			Thread.sleep(5000);
			System.out.println("Hello");
		} catch (Exception e) {
		}
	}

}
