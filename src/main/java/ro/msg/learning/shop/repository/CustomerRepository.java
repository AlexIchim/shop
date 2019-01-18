package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	Customer findByUserName(String username);
}
