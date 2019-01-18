package ro.msg.learning.shop.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.ShippingInfo;
import ro.msg.learning.shop.exception.LocationException;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.ShippingInfoService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SingleLocationStrategyByProximity implements LocationStrategy {

	private final LocationService locationService;
	private final OrderDetailRepository orderDetailRepository;
	private ShippingInfoService shippingInfoService;

	@Autowired
	public SingleLocationStrategyByProximity(LocationService locationService, OrderDetailRepository orderDetailRepository, ShippingInfoService shippingInfoService) {
		this.locationService = locationService;
		this.orderDetailRepository = orderDetailRepository;
		this.shippingInfoService = shippingInfoService;
	}


	@Override
	public List<ShippingInfo> getLocationForOrder(Order order) throws LocationException, IOException {
		Location location = locationService.getAppropriateLocationForOrderByDistance(order);

		if (location == null) {
			throw new LocationException("No location found for order with id:" + order.getId());
		}
		List<ShippingInfo> shippingInfos = new ArrayList<>();
		for (OrderDetail orderDetail : orderDetailRepository.findByOrderId(order.getId())
			 ) {
			ShippingInfo shippingInfo = shippingInfoService.createShippingInfo(order, orderDetail.getId().getProduct(), location, orderDetail.getQuantity());
			shippingInfos.add(shippingInfo);
		}

		return shippingInfos;
	}
}
