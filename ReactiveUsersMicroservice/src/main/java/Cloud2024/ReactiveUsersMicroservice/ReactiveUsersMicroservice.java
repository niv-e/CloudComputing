package Cloud2024.ReactiveUsersMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ReactiveUsersMicroservice {
	public static void main(String[] args) {
		SpringApplication.run(ReactiveUsersMicroservice.class, args);
	}
}
