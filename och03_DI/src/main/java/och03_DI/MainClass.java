package och03_DI;

import javax.swing.AbstractAction;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		//전통적
//		MyCalculator myCalculator = new MyCalculator();
//		myCalculator.setCalcurator(new Calcurator());
//		myCalculator.setFirstNum(10);
//		myCalculator.setSecondNum(2);
//		myCalculator.add();
//		myCalculator.sub();
		//DI setting
		String configLocation = "classpath:applicationCTX.xml";
		AbstractApplicationContext ctx= new GenericXmlApplicationContext(configLocation);
		MyCalculator myCalculator = ctx.getBean("myCalculator",MyCalculator.class);
		myCalculator.add();
		myCalculator.sub();
		myCalculator.mul();
		myCalculator.div();
	}

}
