package env02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	//context란 외부환경설정을 가져오는겉 <context:property-placeholder/> 이렇게 빈에서 단독태그로 사용해서 컨텍스트(외부환경설정)의 프로펄티파일 를가져옴
	public static void main(String[] args) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX02.xml");
		
		AdminConnection connection = ctx.getBean("adminConnection",AdminConnection.class);
		System.out.println("adminID:"+connection.getAdminId());
		System.out.println("adminPW:"+connection.getAdminPw());
		System.out.println("sub_adimnID:"+connection.getSub_adminId());
		System.out.println("sub_adimnPW:"+connection.getAdminPw());
		ctx.close();
		
	}

}
