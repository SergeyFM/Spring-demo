package com.example.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringDemoApplication {

	public interface SaySomethingToSergeyService {
		public String sayIt();
	}

	@Configuration
	public class SaySomethingConfiguration {
		@Bean

		public SaySomethingConfiguragleService saySomethingConfiguragleService() {
			SaySomethingConfiguragleService saySomethingConfiguragleService = new SaySomethingConfiguragleService();
			saySomethingConfiguragleService.setWhatToSay("Goodbye");
			return saySomethingConfiguragleService;
		}
	}

	public class SaySomethingConfiguragleService implements SaySomethingToSergeyService {
		private String whatToSay = "";

		@Override
		public String sayIt() {
			return whatToSay;
		}

		public String getWhatToSay() {
			return whatToSay;
		}

		public void setWhatToSay(String whatToSay) {
			this.whatToSay = whatToSay;
		}
	}
	@Component
	@Primary
	public class SayHiToSergeyService implements SaySomethingToSergeyService {
		@Override
		public String sayIt() {
			return "Hi, Sergey!!";
		}
	}
	public static void main(String[] args) {

		ConfigurableApplicationContext appContext = SpringApplication.run(SpringDemoApplication.class, args);
		SaySomethingToSergeyService saySomethingToSergeyService = appContext.getBean(SaySomethingToSergeyService.class);
		System.out.println(saySomethingToSergeyService.sayIt());
	}

}
