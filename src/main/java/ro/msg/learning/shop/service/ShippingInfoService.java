package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.repository.ShippingInfoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ShippingInfoService {

	private final ShippingInfoRepository shippingInfoRepository;
	private final StockService stockService;

	public ShippingInfo createShippingInfo(Order order, Product product, Location location, int quantity) {
		stockService.updateStockQuantity(location, product, quantity);
		ShippingInfo shippingInfo = new ShippingInfo(order, product, location, quantity, LocalDateTime.now());
		return shippingInfoRepository.save(shippingInfo);
	}

	public List<ShippingInfo> getShippingInfoBetween(LocalDateTime from, LocalDateTime to) {
		return shippingInfoRepository.findShippingInfoBetween(from, to);
	}
}
