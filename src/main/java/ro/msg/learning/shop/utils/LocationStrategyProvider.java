package ro.msg.learning.shop.utils;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.strategy.LocationStrategy;
import ro.msg.learning.shop.strategy.MultipleLocationStrategy;
import ro.msg.learning.shop.strategy.SingleLocationStrategy;
import ro.msg.learning.shop.strategy.SingleLocationStrategyByProximity;

@Component
public class LocationStrategyProvider {
	private SingleLocationStrategy singleLocationStrategy;
	private MultipleLocationStrategy multipleLocationStrategy;
	private SingleLocationStrategyByProximity singleLocationStrategyByProximity;

	@Autowired
	public LocationStrategyProvider(SingleLocationStrategy singleLocationStrategy, MultipleLocationStrategy multipleLocationStrategy, SingleLocationStrategyByProximity singleLocationStrategyByProximity) {
		this.singleLocationStrategy = singleLocationStrategy;
		this.multipleLocationStrategy = multipleLocationStrategy;
		this.singleLocationStrategyByProximity = singleLocationStrategyByProximity;
	}

	public LocationStrategy getLocationStrategy(LocationStrategyType locationStrategyType) {
		switch (locationStrategyType) {
			case SINGLE:
				return singleLocationStrategy;
			case MULTIPLE:
				return multipleLocationStrategy;
			case SINGLEBYPROXIMITY:
				return singleLocationStrategyByProximity;
			default:
				return singleLocationStrategy;
		}
	}
}
