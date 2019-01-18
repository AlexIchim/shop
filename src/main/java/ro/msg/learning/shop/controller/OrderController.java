package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.exception.OrderException;
import ro.msg.learning.shop.exception.OrderProcessingException;
import ro.msg.learning.shop.service.OrderService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/order")
public class OrderController {

	private final OrderService orderService;

	@PostMapping
	public Order createOrder(@RequestBody OrderDTO orderDTO) throws OrderProcessingException {
		return orderService.ceateOrder(orderDTO);
	}
}
