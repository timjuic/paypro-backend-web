package air.found.payprowebbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class PayproWebBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayproWebBackendApplication.class, args);
	}

}
