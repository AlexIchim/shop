package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.exception.LocationException;
import ro.msg.learning.shop.service.StockService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/stock")
public class StockController {

	private final StockService stockService;

	@GetMapping(value = "/exportCSV/{locationId}", produces = "text/csv")
	public List<StockDTO> exportStockForLocation(@PathVariable Integer locationId) throws LocationException {
		List<StockDTO> stockDTOList = stockService.exportStocks(locationId).stream()
				.map(stock -> {
					Product stockProduct = stock.getId().getProduct();
					return new StockDTO(stockProduct.getId(), locationId, stockProduct.getName(), stockProduct.getDescription(),
							stockProduct.getPrice(), stockProduct.getWeight(), stock.getQuantity());
				})
				.collect(Collectors.toList());
		return stockDTOList;
	}

	@GetMapping(value = "/test")
	public String returnTestString()  {
		return "test";
	}
}