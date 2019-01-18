package ro.msg.learning.shop.strategy;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.ShippingInfo;
import ro.msg.learning.shop.dto.OrderShippingDetailDTO;
import ro.msg.learning.shop.exception.LocationException;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.ShippingInfoService;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class MultipleLocationStrategy implements LocationStrategy {

	@Override
	public List<ShippingInfo> getLocationForOrder(Order order) {
		return null;
	}
}
