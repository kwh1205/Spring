package sdlc01;
	//빈의 생명주기 -- load할때는 아직 만들어지기전임 refresh - 할때 생성됨  close - 삭제 
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		System.out.println("1 ctx.load Before");
		ctx.load("classpath:applicationCTX01.xml");
		System.out.println("2 ctx.load After");
		//실제빈 생성
		ctx.refresh();
		System.out.println("3 ctx.refresh After");
		ctx.close();
		System.out.println("4 ctx.close After");
	
	}

}
