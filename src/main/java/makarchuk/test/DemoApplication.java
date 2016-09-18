package makarchuk.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import makarchuk.test.config.JpaConfig;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run( new Class<?>[] {DemoApplication.class, JpaConfig.class}, args);
	}
}
