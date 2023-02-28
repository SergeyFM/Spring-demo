package com.example.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
	@Qualifier("Hi-Sergey-Service")
	public class SayHiToSergeyService implements SaySomethingToSergeyService {
		@Override
		public String sayIt() {
			return "Hi, Sergey!!";
		}
	}

	@RestController
	public class SaySometingController {
		@Autowired
		SaySomethingToSergeyService saySomethingToSergeyService;
		@Qualifier("Hi-Sergey-Service")
		@GetMapping("/")
		public String home() {
			return saySomethingToSergeyService.sayIt();
		}
	}

	@RestController
	public class CityController {
		@Autowired
		CityRepository cityRepository;

		@GetMapping("/moscow")
		public City moscow() {
			return cityRepository.findByName("Moscow");
		}
		@GetMapping("/duesseldorf")
		public City duesseldorf() {
			return cityRepository.findByName("Düsseldorf");
		}

	}

	public static void main(String[] args) {

		ConfigurableApplicationContext appContext = SpringApplication.run(SpringDemoApplication.class, args);
		SaySomethingToSergeyService saySomethingToSergeyService = appContext.getBean(SaySomethingToSergeyService.class);
		System.out.println(saySomethingToSergeyService.sayIt());


		City moscow = new City();
		moscow.setName("Moscow");
		moscow.setCapital(true);

		City duesseldorf = new City();
		duesseldorf.setName("Düsseldorf");
		duesseldorf.setCapital(false);

		CityRepository cityRepository = appContext.getBean(CityRepository.class);
		cityRepository.save(moscow);
		cityRepository.save(duesseldorf);
	}

}
