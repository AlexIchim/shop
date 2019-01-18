package ro.msg.learning.shop.scheduledjobs;

import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.RevenueService;
import ro.msg.learning.shop.service.ShippingInfoService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component("revenueJob")
public class RevenueJob implements Runnable {

	private final LocationService locationService;
	private final ShippingInfoService shippingInfoService;
	private final RevenueService revenueService;


	public RevenueJob(LocationService locationService, ShippingInfoService shippingInfoService, RevenueService revenueService) {
		this.locationService = locationService;
		this.shippingInfoService = shippingInfoService;
		this.revenueService = revenueService;
	}

	@Override
	public void run() {
		List<Location> locations = locationService.getLocations();
		List<Revenue> revenueList = new ArrayList<>();
		for (Location location: locations
			 ) {
			List<ShippingInfo> shippingInfos = shippingInfoService.getShippingInfoBetween(LocalDateTime.now().with(LocalTime.of(0, 0, 0)), LocalDateTime.now().with(LocalTime.of(23, 59, 59)));
			BigDecimal sum = BigDecimal.ZERO;
			for (ShippingInfo shippingInfo: shippingInfos
			) {
				sum = sum.add(shippingInfo.getProduct().getPrice().multiply(BigDecimal.valueOf(shippingInfo.getQuantity())));
			}
			revenueList.add(new Revenue(location, LocalDateTime.now(), sum));
		}

		revenueService.saveAll(revenueList);
	}
}
