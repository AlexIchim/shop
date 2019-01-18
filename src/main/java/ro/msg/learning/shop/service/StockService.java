package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.domain.StockId;
import ro.msg.learning.shop.exception.LocationException;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class StockService {
	private final StockRepository stockRepository;
	private final LocationService locationService;

	public void updateStockQuantity(Location location, Product product, int quantity) {
		Stock stock = stockRepository.findById(new StockId(product, location)).orElse(null);
		if (stock != null) {
			stock.setQuantity(stock.getQuantity() - quantity);
			stockRepository.save(stock);
		}
	}

	public List<Stock> exportStocks(Integer locationId) throws LocationException {
		return locationService.getById(locationId).getStocks();
	}

}
