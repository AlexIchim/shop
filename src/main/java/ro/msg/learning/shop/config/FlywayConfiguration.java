package ro.msg.learning.shop.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class FlywayConfiguration {

	@Bean
	@Profile("prod")
	public Flyway flyway(DataSource theDataSource){
		Flyway flyway=new Flyway();
		flyway.setDataSource(theDataSource);
		flyway.setLocations("classpath:db/migration");
		flyway.clean();
		flyway.migrate();

		return flyway;
	}
}
