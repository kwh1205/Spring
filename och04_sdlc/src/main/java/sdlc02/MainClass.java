package sdlc02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX02.xml");
		//빈은 같은 빈 사용시 같은주소 레퍼런스한다(싱글톤) -- 콜오브 레퍼런스임 이걸 해결하기위함 - bean에서 scope에 prototype - 사용시 같은빈을 다른객체로 만들어줌
		//싱글톤이 default타입임  - 선언안할시 싱글톤
		Student student1 = ctx.getBean("student",Student.class);
		System.out.println("stident1의 이름:"+student1.getName());
		System.out.println("student1의 나이:"+student1.getAge());
		
		System.out.println("=============================");
		Student student2 = ctx.getBean("student",Student.class);
		student2.setName("강유");
		student2.setAge(55);
		
		System.out.println("stident2의 이름:"+student2.getName());
		System.out.println("student2의 나이:"+student2.getAge());
		
		System.out.println("=============================");
		System.out.println("stident1의 이름:"+student1.getName());
		System.out.println("student1의 나이:"+student1.getAge());
		
		if(student1.equals(student2)) {
			System.out.println("student1 ==student2");
		}else {
			System.out.println("student1 !=student2");
		}
		ctx.close();
	}

}
