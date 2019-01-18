package ro.msg.learning.shop.strategy;

import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.domain.ShippingInfo;
import ro.msg.learning.shop.exception.LocationException;

import java.io.IOException;
import java.util.List;

public interface LocationStrategy {
	List<ShippingInfo> getLocationForOrder(Order order) throws LocationException, IOException;
}
