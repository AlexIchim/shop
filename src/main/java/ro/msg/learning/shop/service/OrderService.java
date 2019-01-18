package ro.msg.learning.shop.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.repository.CustomerRepository;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.strategy.SingleLocationStrategy;
import ro.msg.learning.shop.utils.LocationStrategyProvider;
import ro.msg.learning.shop.utils.LocationStrategyType;
import ro.msg.learning.shop.exception.OrderProcessingException;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

	private final CustomerRepository customerRepository;
	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;
	private final OrderDetailRepository orderDetailRepository;
	private final LocationStrategyProvider locationStrategyProvider;


	@Transactional
	public Order ceateOrder(OrderDTO orderDTO) throws OrderProcessingException {
		try {
			Customer customer = customerRepository.findByUserName("test");
			Order order = new Order(customer, orderDTO.getAddress(), orderDTO.getDate());
			orderRepository.save(order);

			for (ProductDTO productDto: orderDTO.getProducts()
				 ) {
				Product product = productRepository.findById(productDto.getId()).orElse(null);
				OrderDetail orderDetail = new OrderDetail(new OrderDetailId(order, product), productDto.getQuantity());
				orderDetailRepository.save(orderDetail);
			}
			order = orderRepository.findById(order.getId()).orElse(null);
			if (order == null) {
				throw new OrderProcessingException("Order not found");
			}
			locationStrategyProvider.getLocationStrategy(LocationStrategyType.SINGLEBYPROXIMITY).getLocationForOrder(order);
			return orderRepository.findById(order.getId()).orElse(null);
		} catch (Exception e) {
			throw new OrderProcessingException(e.getMessage());
		}
	}
}
