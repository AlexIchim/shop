package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.service.LocationService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/location")
public class LocationController {
	private final LocationService locationService;

	@GetMapping(value = "/getAll")
	public String getOrders()  {
		return locationService.getLocations().toString();
	}
}
