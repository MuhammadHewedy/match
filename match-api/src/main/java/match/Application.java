package match;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import match.util.Util;

@EnableJpaAuditing
@EnableJpaRepositories
@SpringBootApplication
@EnableTransactionManagement
@EntityScan(basePackageClasses = { Application.class, Jsr310JpaConverters.class })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public AuditorAware<?> auditorProvider() {
		return (() -> Util.currentUser().map(o -> o.getId()).orElse(-1l));
	}
}
