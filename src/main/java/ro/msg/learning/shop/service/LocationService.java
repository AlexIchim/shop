package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.utils.DistanceMatrixGoogleApiService;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class LocationService {

	private final LocationRepository locationRepository;
	private final OrderDetailRepository orderDetailRepository;
	private final DistanceMatrixGoogleApiService distanceMatrixGoogleApiService;

	public Location getAppropriateLocationForOrder(Order order) {
		for (Location location : locationRepository.findAll()
			 ) {
			boolean isLocationOk = true;
			for (OrderDetail orderDetail : orderDetailRepository.findByOrderId(order.getId())
				 ) {
				if (!checkIfProductInStock(orderDetail, location.getStocks())) {
					isLocationOk = false;
					break;
				}
			}
			if (isLocationOk) {
				return location;
			}
		}
		return null;
	}

	public Location getAppropriateLocationForOrderByDistance(Order order) throws IOException {
		Location finalLocation = null;
		double minDistance = 9999999.0;
		for (Location location : locationRepository.findAll()
		) {
			boolean isLocationOk = true;
			for (OrderDetail orderDetail : orderDetailRepository.findByOrderId(order.getId())
			) {
				if (!checkIfProductInStock(orderDetail, location.getStocks())) {
					isLocationOk = false;
				}
			}
			if (isLocationOk) {
				Double distanceValue = distanceMatrixGoogleApiService.calculate(order.getAddress().getCity(), location.getAddress().getCity());
				if (minDistance > distanceValue) {
					minDistance = distanceValue;
					finalLocation = location;
				}
			}
		}
		return finalLocation;
	}


	private boolean checkIfProductInStock(OrderDetail od, List<Stock> stockList) {
		for (Stock stock: stockList
			 ) {
			if (od.getId().getProduct().getId().equals(stock.getId().getProduct().getId())) {
				return od.getQuantity() <= stock.getQuantity();
			}
		}
		return false;
	}

	public Location getById(Integer locationId) {
		return locationRepository.findById(locationId).orElse(null);
	}

	public List<Location> getLocations() {
		return locationRepository.findAll();
	}
}
