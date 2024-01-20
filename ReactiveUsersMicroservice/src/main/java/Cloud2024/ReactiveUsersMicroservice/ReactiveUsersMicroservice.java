package Cloud2024.ReactiveUsersMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@ComponentScan(basePackages = "Cloud2024.ReactiveUsersMicroservice")
@SpringBootApplication
//@Configuration
public class ReactiveUsersMicroservice {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveUsersMicroservice.class, args);
	}

}
