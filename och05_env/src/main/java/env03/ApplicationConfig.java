package env03;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration //클래스위에 Configuration
public class ApplicationConfig {
	
	@Value("${admin.id}")
	private String adminId;
	@Value("${admin.pw}")
	private String adminPw;
	@Value("${sub_admin.id}")
	private String sub_adminId;
	@Value("${sub_admin.pw}")
	private String sub_adminPw;
	
	
	
	//환경파일 잡을때 사용되는 객체 PropertySourcesPlaceholderConfigurer
	//스프링 프레임워크 좋은점 - 자동맵핑이됌 외부환경설정파일을 자동으로 맵핑해서 값가져옴
	@Bean //각각의 메소드에 빈 -- 이래야 컨피규레이션,빈이 컨테이너에 차곡차곡 쌓임
	public static PropertySourcesPlaceholderConfigurer Properties() {
	
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		
		System.out.println("2. Properties Run");
		Resource[] locations = new Resource[2];
		locations[0] = new ClassPathResource("admin3.properties");
		locations[1] = new ClassPathResource("sub_admin3.properties");
		
		configurer.setLocations(locations);
		
		return configurer;
		
	}
	@Bean
	public AdminConnection adminConfig() {
		AdminConnection adminConnection = new AdminConnection();
		System.out.println("3. adminConfig Run");
		adminConnection.setAdminId(adminId);
		adminConnection.setAdminPw(adminPw);
		adminConnection.setSub_adminId(sub_adminId);
		adminConnection.setSub_adminPw(sub_adminPw);
		
		
		return adminConnection;
	}
	
	
}
