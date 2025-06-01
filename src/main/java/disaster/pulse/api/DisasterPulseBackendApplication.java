package disaster.pulse.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DisasterPulseBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisasterPulseBackendApplication.class, args);
	}

}
