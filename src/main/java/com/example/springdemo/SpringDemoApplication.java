package com.example.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringDemoApplication {

	public interface SaySomethingToSergeyService {
		public String sayIt();
	}

	@Component
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
