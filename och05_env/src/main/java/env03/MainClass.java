package env03;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		System.out.println("1 run");
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(ApplicationConfig.class);
		System.out.println("4 adminConfig Before Run");
		AdminConnection connection = ctx.getBean("adminConfig",AdminConnection.class);
		System.out.println("5 adminConfig After Run");
		
		System.out.println("connection.getAdminId adminId="+connection.getAdminId());
		System.out.println("connection.getAdminPw adminPw="+connection.getAdminPw());
		System.out.println("connection.getSub_AdminId adminId="+connection.getSub_adminId());
		System.out.println("connection.getSub_AdminPw adminPw="+connection.getAdminPw());;
		ctx.close();
	}

}
