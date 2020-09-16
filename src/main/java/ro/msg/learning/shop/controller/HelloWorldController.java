package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api")
public class HelloWorldController {
	@GetMapping(value = "/helloWorld")
	public String getOrders()  {
		return "Hello world";
	}
}
