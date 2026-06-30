package ar.edu.utn.frba.dds.climalert;

import ar.edu.utn.frba.dds.climalert.config.ClimalertProperties;
import ar.edu.utn.frba.dds.climalert.config.WeatherApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({WeatherApiProperties.class, ClimalertProperties.class})
public class ClimalertApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClimalertApplication.class, args);
	}

}
