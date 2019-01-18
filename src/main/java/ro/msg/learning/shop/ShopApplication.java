package ro.msg.learning.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import ro.msg.learning.shop.domain.Role;
import ro.msg.learning.shop.domain.User;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.UserRepository;
import ro.msg.learning.shop.service.UserService;
import ro.msg.learning.shop.utils.CsvMessageConverter;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ShopApplication {


	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

	/* Save a user to DB */
	/*
	@Autowired
	UserService userService;

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			if
			userService.save(User.builder().username("test").password("test").build());
		};
	}*/
}
