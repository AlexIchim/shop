package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
