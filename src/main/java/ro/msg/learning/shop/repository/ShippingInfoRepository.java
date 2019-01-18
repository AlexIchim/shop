package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.msg.learning.shop.domain.ShippingInfo;

import java.time.LocalDateTime;
import java.util.List;

public interface ShippingInfoRepository extends JpaRepository<ShippingInfo, Integer> {

	@Query("SELECT si FROM ShippingInfo si WHERE si.shippingDate BETWEEN  ?1 AND  ?2")
	List<ShippingInfo> findShippingInfoBetween(LocalDateTime from, LocalDateTime to);
}
