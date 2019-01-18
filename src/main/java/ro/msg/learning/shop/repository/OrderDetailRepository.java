package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.msg.learning.shop.domain.OrderDetail;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
	@Query("SELECT od FROM OrderDetail od WHERE od.id.order.id = ?1")
	List<OrderDetail> findByOrderId(Integer orderId);
}
