package telran.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"telran"})
public class SpringEmployeesApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(SpringEmployeesApplication.class, args);
	}

}
